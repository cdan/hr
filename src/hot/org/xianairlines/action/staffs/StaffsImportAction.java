package org.xianairlines.action.staffs;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.xianairlines.model.EduExperience;
import org.xianairlines.model.Relatives;
import org.xianairlines.model.Staffs;
import org.xianairlines.model.WorkExperience;

import javax.persistence.EntityManager;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Name("staffsImportAction")
@Scope(ScopeType.EVENT)
public class StaffsImportAction {

	private InputStream uploadFileStream;

	private String uploadFileName;
	
	@In
	private EntityManager entityManager;

	public InputStream getUploadFileStream() {
		return uploadFileStream;
	}

	public void setUploadFileStream(InputStream uploadFileStream) {
		this.uploadFileStream = uploadFileStream;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public String importStaffs(){
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(uploadFileStream);
			HSSFSheet sheet =  workBook.getSheetAt(0);
			Staffs staffs = this.getStaffs(sheet);
			entityManager.persist(staffs);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "suc";
	}
	
	private Staffs getStaffs(HSSFSheet sheet) throws ParseException{

        DateUtil du = new DateUtil();
		
		HSSFRow row = 	sheet.getRow(3);
		String name = row.getCell(1).getStringCellValue();
		String gender = row.getCell(3).getStringCellValue();
		Date birthdate = parse(row.getCell(5));
        System.out.println("生日类型"+row.getCell(5).getCellType());
		
		row = 	sheet.getRow(4);
		String nativePlace = row.getCell(1).getStringCellValue();
		String nation = row.getCell(3).getStringCellValue();
		String maritalStatus =   row.getCell(5).getStringCellValue();
		
		row = 	sheet.getRow(5);
		String politicsStatus = row.getCell(1).getStringCellValue();

		Date partyDate = parse(row.getCell(3));
        System.out.println("入党"+row.getCell(3).getCellType());
		Date workDate =  parse(row.getCell(5));
		
		row = 	sheet.getRow(6);
		String educationBackground = row.getCell(1).getStringCellValue();
		String degree = row.getCell(3).getStringCellValue();
		String graduateSchool =   row.getCell(5).getStringCellValue();
		
		
		row = 	sheet.getRow(7);
		String professionalTitle = row.getCell(1).getStringCellValue();
		Date professionalTitleDate = parse(row.getCell(3));
		String specialty =   row.getCell(5).getStringCellValue();
		String identityNo =   row.getCell(7).getStringCellValue();
		
		row = 	sheet.getRow(8);
		String tel = row.getCell(1).getStringCellValue();
		String mobile = row.getCell(5).getStringCellValue();
		String drivingLicenseLevel =   row.getCell(7).getStringCellValue();
		row = 	sheet.getRow(9);
		String homeAddress = row.getCell(1).getStringCellValue();
		row = 	sheet.getRow(10);
		String nativeAddress = row.getCell(1).getStringCellValue();
		row = 	sheet.getRow(11);
		String email = row.getCell(1).getStringCellValue();

        Date startDate = parse(sheet.getRow(1).getCell(7));
        SimpleDateFormat formate = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat ymformate = new SimpleDateFormat("yyyy年MM月");

        //edu experiences
        List eduList = new ArrayList();
        for(int i=14;i<19;i++) {
            EduExperience eduExperience = new EduExperience();
            row = sheet.getRow(i);
            String tempDate = row.getCell(0).getStringCellValue();
            //System.out.println(i);
            if(!tempDate.substring(0, tempDate.indexOf('-')).equals("")) {
            eduExperience.setStartDate(du.parse(tempDate.substring(0, tempDate.indexOf('-')), "yyyy年MM月"));
            eduExperience.setEndDate(du.parse(tempDate.substring(tempDate.indexOf('-')+1,tempDate.length()), "yyyy年MM月"));

            String tempSchool = row.getCell(2).getStringCellValue();
            eduExperience.setSchool(tempSchool.substring(0,tempSchool.indexOf('/')));
            eduExperience.setSpecialty(tempSchool.substring(tempSchool.indexOf('/')+1, tempSchool.length()));

            eduExperience.setEducationBackground(row.getCell(7).getStringCellValue());

            eduList.add(eduExperience);
            }
        }

        //work experience
        List workList = new ArrayList();
        for(int i=21;i<30;i++) {
            WorkExperience workExperience = new WorkExperience();
            row = sheet.getRow(i);
            String tempDate = row.getCell(0).getStringCellValue();
            if(!tempDate.substring(0, tempDate.indexOf('-')).equals("")) {
            workExperience.setStartDate(du.parse(tempDate.substring(0, tempDate.indexOf('-')), "yyyy年MM月"));
            workExperience.setEndDate(du.parse(tempDate.substring(tempDate.indexOf('-') + 1, tempDate.length()), "yyyy年MM月"));

            String tempWork = row.getCell(2).getStringCellValue();
            workExperience.setWorkUnit(tempWork.substring(0, tempWork.indexOf('/')));
            workExperience.setDepartment(tempWork.substring(tempWork.indexOf('/') + 1, tempWork.length()));

            workExperience.setWorkName(row.getCell(7).getStringCellValue());

            workList.add(workExperience);
            }
        }

        //relatives
        List relativeList = new ArrayList();
        for(int i=35;i<40;i++) {
            Relatives r = new   Relatives();
            row = sheet.getRow(i);
            String tname = row.getCell(1).getStringCellValue();
            if(!tname.equals("")) {
                r.setName(row.getCell(1).getStringCellValue());
                r.setRelative(row.getCell(2).getStringCellValue());
                r.setBirthdate(parse(row.getCell(3)));
                r.setWorkUnit(row.getCell(4).getStringCellValue());
                r.setTel(row.getCell(7).getStringCellValue());
                relativeList.add(r);
            }
        }

        //spouse info
        String spouseName = sheet.getRow(31).getCell(2).getStringCellValue();
        Date spouseBirthdate = parse(sheet.getRow(31).getCell(4));
        String spousePoliticsStatus  = sheet.getRow(31).getCell(6).getStringCellValue();
        String spouseNativePlace = sheet.getRow(32).getCell(2).getStringCellValue();
        String spouseWorkName = sheet.getRow(32).getCell(4).getStringCellValue();
        String spouseTel = sheet.getRow(32).getCell(6).getStringCellValue();
        String spouseWorkUnit = sheet.getRow(33).getCell(2).getStringCellValue();


		Staffs staffs = new Staffs(name, gender, birthdate ,
				nativePlace, nation, maritalStatus,
				politicsStatus, partyDate,  workDate,
				educationBackground, degree,  graduateSchool,
				professionalTitle, professionalTitleDate,
				specialty,  identityNo,  tel,  mobile,
				drivingLicenseLevel,  homeAddress,
				nativeAddress,  email, startDate,null, workList, relativeList, eduList, null,
                spouseName, spouseBirthdate, spousePoliticsStatus,spouseNativePlace,spouseWorkName,
                spouseTel, spouseWorkUnit);
		return staffs;
		
	}


    private Date parse(HSSFCell cell) {
        DateUtil du = new DateUtil();
        if (cell.getCellType() == 0) return cell.getDateCellValue();
        if (cell.getCellType() == 1) return du.parse(cell.getStringCellValue(), "yyyy年MM月dd日");
        return null;
    }
		
	}

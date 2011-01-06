package org.xianairlines.action.staffs;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.xianairlines.model.Staffs;

import javax.persistence.EntityManager;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		
		HSSFRow row = 	sheet.getRow(3);
		String name = row.getCell(1).getStringCellValue();
		String gender = row.getCell(3).getStringCellValue();
		String birthdate =   row.getCell(5).getStringCellValue();
		
		row = 	sheet.getRow(4);
		String nativePlace = row.getCell(1).getStringCellValue();
		String nation = row.getCell(3).getStringCellValue();
		String maritalStatus =   row.getCell(5).getStringCellValue();
		
		row = 	sheet.getRow(5);
		String politicsStatus = row.getCell(1).getStringCellValue();
		String partyDate = row.getCell(3).getStringCellValue();
		String workDate =   row.getCell(5).getStringCellValue();
		
		row = 	sheet.getRow(6);
		String educationBackground = row.getCell(1).getStringCellValue();
		String degree = row.getCell(3).getStringCellValue();
		String graduateSchool =   row.getCell(5).getStringCellValue();
		
		
		row = 	sheet.getRow(7);
		String professionalTitle = row.getCell(1).getStringCellValue();
		String professionalTitleDate = row.getCell(3).getStringCellValue();
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



        String startDate = sheet.getRow(1).getCell(7).getStringCellValue();
		
		SimpleDateFormat formate = new SimpleDateFormat("yyyy年MM月dd日");
		Staffs staffs = new Staffs(name, gender, formate.parse(birthdate) ,
				nativePlace, nation, maritalStatus,
				politicsStatus, formate.parse(partyDate),  formate.parse( workDate),
				educationBackground, degree,  graduateSchool,
				 professionalTitle, formate.parse( professionalTitleDate),
				 specialty,  identityNo,  tel,  mobile,
				 drivingLicenseLevel,  homeAddress,
				 nativeAddress,  email, formate.parse(startDate), null, null, null, null);
		return staffs;
		
	}
		
	}

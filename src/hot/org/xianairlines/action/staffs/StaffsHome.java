package org.xianairlines.action.staffs;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.xianairlines.model.*;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Name("staffsHome")
@Scope(ScopeType.CONVERSATION)
public class StaffsHome {
	@Out(required = false)
	private Staffs staff;

	private Long staffsId;

	@Out(required = false)
	private Spouse spouse;
	
	public Spouse getSpouse() {
		if(spouse==null){
			spouse = new Spouse();
		}
		return spouse;
//		Set<Spouse> set = staff.getSpouses();
//		if (set.size() > 0) {
//			return set.iterator().next();
//		}
//		return new Spouse();
	}
	
	public void spouseInit(){
//		Set<Spouse> set = staff.getSpouses();
//		if(set.size()>0){
//			spouse = set.iterator().next();
//		}
	}
	
	public void setSpouse(Spouse spouse) {
		this.spouse = spouse;
	}
	
	@In(value = "#{facesContext.externalContext}")
	private ExternalContext extCtx;

	@In(value = "#{facesContext}")
	FacesContext facesContext;

	public Long getStaffsId() {
		return staffsId;
	}

	public void setStaffsId(Long staffsId) {
		this.staffsId = staffsId;
	}

	public Staffs getStaff() {
		if (staff == null) {
			staff = new Staffs();
		}
		return staff;
	}

	public void setStaff(Staffs staff) {
		this.staff = staff;
	}

	@In
	private EntityManager entityManager;

	public void editInit() {
		if (staffsId != null) {
			staff = entityManager.find(Staffs.class, staffsId);
		}
		if (staff == null) staff = new Staffs();
//		Set<Spouse> set = staff.getSpouses();
//		if(set.size()>0){
//			spouse = set.iterator().next();
//		}
//		if (spouse == null) spouse = new Spouse();
	}

	public Spouse getSpouses() {
		Set<Spouse> set = staff.getSpouses();
		if (set.size() > 0) {
			return set.iterator().next();
		}
		return new Spouse();
	}

	public List<Relatives> getRelatives() {
		return this.staff.getRelatives();
	}

	public List<WorkExperience> getWorkExperiences() {
		return this.staff.getWorkExperiences();
	}

	public List<EduExperience> getEduExperiences() {
		return this.staff.getEduExperiences();
	}

	public String persist() {
		entityManager.persist(staff);
//		spouse.setStaffs(staff);
//		staff.getSpouses().add(spouse);
//		entityManager.persist(spouse);
		entityManager.flush();
		return "persisted";
	}

	public String persistAndNext() {
		entityManager.persist(staff);
//		spouse.setStaffs(staff);
//		staff.getSpouses().add(spouse);
//		entityManager.persist(spouse);
		entityManager.flush();
		return "next";
	}

	public void export() throws UnsupportedEncodingException {
		this.editInit();
		final HttpServletResponse response = (HttpServletResponse) extCtx
				.getResponse();
		response.setContentType("application/x-download");
        //response.setContentType("application/msexcel");

		final String newFileName = encodeFileName(this.staff.getName()+".xls");
		response.addHeader("Content-disposition", "attachment;filename="
				+ newFileName + ";charset=UTF-8");
		
		 Map<String, Object> beans = new HashMap<String, Object>();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
         SimpleDateFormat ymdateFormat = new SimpleDateFormat("yyyy年MM月");
         beans.put("ymdateFormat", ymdateFormat);
	     beans.put("dateFormat", dateFormat);
		 beans.put("staff", this.staff);
		 //beans.put("spouse", this.getSpouse());
		 beans.put("workMap", this.getWorkExperiences());
		 beans.put("eduMap", this.getEduExperiences());
		 beans.put("rl", this.getRelatives());
		InputStream in = null;
		Workbook workBook = null;
		ServletOutputStream os = null;
		try{
		in = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("/").getPath()+"template.xls");
		os = response.getOutputStream();
		XLSTransformer transformer = new XLSTransformer();
		workBook = transformer.transformXLS(in, beans);
		workBook.write(os);
		os.flush();
		facesContext.responseComplete();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	
	private List<Map<String, Object>> eduMap(){
		List<EduExperience> list = this.getEduExperiences();
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		for (EduExperience eduExperience : list) {
			Map<String, Object>  map = new HashMap<String, Object>();
			map.put("times", dateFormat.format(eduExperience.getStartDate())+"--"+dateFormat.format(eduExperience.getEndDate()));
			map.put("school", eduExperience.getSchool()+"/"+eduExperience.getSpecialty());
			map.put("eduBg", eduExperience.getEducationBackground());
			mapList.add(map);
		}
		return mapList;
	}
	
	private List<Map<String, Object>> workMap(){
		List<WorkExperience> list = this.getWorkExperiences();
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		for (WorkExperience workExperience : list) {
			Map<String, Object>  map = new HashMap<String, Object>();
			map.put("times", dateFormat.format(workExperience.getStartDate())+"--"+dateFormat.format(workExperience.getEndDate()));
			map.put("workUnit", workExperience.getWorkUnit()+"/"+workExperience.getDepartment());
			map.put("workName", workExperience.getWorkName());
			mapList.add(map);
		}
		return mapList;
	}

	private String encodeFileName(String fileName)
			throws UnsupportedEncodingException {
		final HttpServletRequest request = (HttpServletRequest) extCtx
				.getRequest();
		String newFileName = null;
		final String s = request.getHeader("user-agent");
		if (s != null) {
			if (s.indexOf("MSIE") > -1) {
				newFileName = URLEncoder.encode(fileName, "UTF-8");
			} else if (s.indexOf("Mozilla") > -1) {
				newFileName = new String(fileName.getBytes("UTF-8"),
						"iso-8859-1");
			} else {
				newFileName = URLEncoder.encode(fileName, "UTF-8");
			}
		} else {
			newFileName = URLEncoder.encode(fileName, "UTF-8");
		}

		return newFileName;
	}

}

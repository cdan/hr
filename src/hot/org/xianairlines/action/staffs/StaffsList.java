package org.xianairlines.action.staffs;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import org.xianairlines.model.Staffs;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Name("staffsList")
public class StaffsList extends EntityQuery<Staffs> {

	private static final String EJBQL = "select staffs from Staffs staffs";

	private static final String[] RESTRICTIONS = {
			"lower(staffs.degree) like lower(concat(#{staffsList.staffs.degree},'%'))",
			"lower(staffs.drivingLicenseLevel) like lower(concat(#{staffsList.staffs.drivingLicenseLevel},'%'))",
			"lower(staffs.educationBackground) like lower(concat(#{staffsList.staffs.educationBackground},'%'))",
			"lower(staffs.email) like lower(concat(#{staffsList.staffs.email},'%'))",
			"lower(staffs.gender) like lower(concat(#{staffsList.staffs.gender},'%'))",
			"lower(staffs.graduateSchool) like lower(concat(#{staffsList.staffs.graduateSchool},'%'))",
			"lower(staffs.homeAddress) like lower(concat(#{staffsList.staffs.homeAddress},'%'))",
			"lower(staffs.identityNo) like lower(concat(#{staffsList.staffs.identityNo},'%'))",
			"lower(staffs.maritalStatus) like lower(concat(#{staffsList.staffs.maritalStatus},'%'))",
			"lower(staffs.mobile) like lower(concat(#{staffsList.staffs.mobile},'%'))",
			"lower(staffs.name) like lower(concat(#{staffsList.staffs.name},'%'))",
			"lower(staffs.nation) like lower(concat(#{staffsList.staffs.nation},'%'))",
			"lower(staffs.nativeAddress) like lower(concat(#{staffsList.staffs.nativeAddress},'%'))",
			"lower(staffs.nativePlace) like lower(concat(#{staffsList.staffs.nativePlace},'%'))",
			"lower(staffs.politicsStatus) like lower(concat(#{staffsList.staffs.politicsStatus},'%'))",
			"lower(staffs.professionalTitle) like lower(concat(#{staffsList.staffs.professionalTitle},'%'))",
			"lower(staffs.specialty) like lower(concat(#{staffsList.staffs.specialty},'%'))",
			"lower(staffs.tel) like lower(concat(#{staffsList.staffs.tel},'%'))",
            "lower(staffs.ssdw) like lower(concat(#{staffsList.staffs.ssdw},'%'))",
            "lower(staffs.bumen) like lower(concat(#{staffsList.staffs.bumen},'%'))",
            "lower(staffs.zhiwu) like lower(concat(#{staffsList.staffs.zhiwu},'%'))",
            "lower(staffs.zhiji) like lower(concat(#{staffsList.staffs.zhiji},'%'))",
            "lower(staffs.bianzhi) like lower(concat(#{staffsList.staffs.bianzhi},'%'))",
            "lower(staffs.dingjijibie) like lower(concat(#{staffsList.staffs.dingjijibie},'%'))",
			"staffs.birthdate >= #{staffsList.birthdateFrom}",
			"staffs.birthdate <= #{staffsList.birthdateTo}",};

	private Date birthdateFrom;
	private Date birthdateTo;
	
	@In(value = "#{facesContext.externalContext}")
	private ExternalContext extCtx;

	@In(value = "#{facesContext}")
	FacesContext facesContext;
	
	private Staffs staffs = new Staffs();

	public StaffsList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Staffs getStaffs() {
		return staffs;
	}
	
	private String[] columNames;

	public String[] getColumNames() {
		return columNames;
	}

	public void setColumNames(String[] columNames) {
		this.columNames = columNames;
	}
	
	public void exportStaffsByColumNames() throws UnsupportedEncodingException {
		ServletOutputStream os = null;
		try {
			final HttpServletResponse response = (HttpServletResponse) extCtx
					.getResponse();
			os = response.getOutputStream();
			response.setContentType("application/x-download");
			final String newFileName = encodeFileName("人事信息栏目.xls");
			response.addHeader("Content-disposition", "attachment;filename="
					+ newFileName + ";charset=UTF-8");
			Workbook wb = new HSSFWorkbook();
			Sheet sheet1 = wb.createSheet("sheet1");
			Row row = null;
			Cell cell = null;
			CellStyle cellStyle = wb.createCellStyle();
			// 设置一个单元格边框颜色
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			// 设置一个单元格边框颜色
			cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			// 创建Font对象
			Font font = wb.createFont();
			font.setFontName("宋体");
			font.setColor(HSSFColor.BLUE.index);
			font.setItalic(true);
			font.setFontHeight((short) 300);
			row = sheet1.createRow(0);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
			for (int i = 0; i < columNames.length; i++) {
				sheet1.setColumnWidth(i, (short)6000);
				String[] colums = columNames[i].split(",");
				cell = row.createCell(i);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(colums[1]);
			}
			List<Staffs> list = this.getResultList();
			for (int i = 1; i <= list.size(); i = i + 1) {
				row = sheet1.createRow(i);
				row.setHeightInPoints(20);
				for (int j = 0; j < columNames.length; j++) {
					String[] colums = columNames[j].split(",");
					cell = row.createCell(j);
					cell.setCellStyle(cellStyle);
					Object value = this.getStaffsFieldValue((Staffs) list
							.get(i - 1), colums[0]);
					if (value == null) {
						cell.setCellValue("");
					} else if (value instanceof java.util.Date) {
						String cellValue = dateFormat
								.format((java.util.Date) value);
						cell.setCellValue(cellValue);
					} else {
						cell.setCellValue(value.toString());
					}

				}
			}
			wb.write(os);
			os.flush();
		} catch (Exception e) {
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
			facesContext.responseComplete();
		}

	}

	private Object getStaffsFieldValue(Staffs staffs, String fieldName)
			throws Exception {
		Field field = Staffs.class.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(staffs);
	}

	public void exportStaffs() throws UnsupportedEncodingException {
		final HttpServletResponse response = (HttpServletResponse) extCtx
				.getResponse();
         long start = System.currentTimeMillis();
        System.out.println("start" + start);
		response.setContentType("application/x-download");
		final String newFileName = encodeFileName("人事信息栏目.xls");
		response.addHeader("Content-disposition", "attachment;filename="
				+ newFileName + ";charset=UTF-8");
		Map<String, Object> beans = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		beans.put("staffs", this.getResultList());
		beans.put("dateFormat", dateFormat);
		InputStream in = null;
		Workbook workBook = null;
		ServletOutputStream os = null;
		try {
			in = new FileInputStream(Thread.currentThread()
					.getContextClassLoader().getResource("/").getPath()
					+ "export.xls");
			os = response.getOutputStream();
            System.out.println("3----------" + (System.currentTimeMillis() - start));
			XLSTransformer transformer = new XLSTransformer();
			workBook = transformer.transformXLS(in, beans);
			workBook.write(os);
			os.flush();
            System.out.println("4----------" + (System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
			facesContext.responseComplete();
		}
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

	public void setBirthdateFrom(Date birthdateFrom) {
		this.birthdateFrom = birthdateFrom;
	}

	public Date getBirthdateFrom() {
		return birthdateFrom;
	}

	public void setBirthdateTo(Date birthdateTo) {
		this.birthdateTo = birthdateTo;
	}

	public Date getBirthdateTo() {
		return birthdateTo;
	}
}

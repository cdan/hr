package org.xianairlines.action.staffs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.framework.EntityQuery;
import org.xianairlines.model.Staffs;


//入职提醒列表

@Name("newhirealertList")
public class NewHireAlertList extends EntityQuery<Staffs> {

	private static final String EJBQL = "select staffs from Staffs staffs";

	private static final String[] RESTRICTIONS = {
			//"staffs.startDate >= #{currentDatetime}",
			"staffs.startDate <= #{currentDatetime}",};
	
	@In
	private EntityManager entityManager;
	
	@Override
    public List getResultList() {
		Calendar c = Calendar.getInstance();
		Calendar sixmonth = (Calendar)c.clone();
		sixmonth.add(Calendar.MONTH, -6);
		Calendar fivemonth = (Calendar)c.clone();
		fivemonth.add(Calendar.MONTH, -5);
		
		List<Object[]> result =
		      entityManager.createQuery("select staffs from Staffs staffs where staffs.startDate >= :sixmonth and staffs.startDate <= :fivemonth")
		      .setParameter("sixmonth", sixmonth.getTime())
		      .setParameter("fivemonth", fivemonth.getTime())
		      .getResultList();		
		return result;
	}

	
	@In(value = "#{facesContext.externalContext}")
	private ExternalContext extCtx;

	@In(value = "#{facesContext}")
	FacesContext facesContext;
	
	private Staffs staffs = new Staffs();

	public NewHireAlertList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Staffs getStaffs() {
		return staffs;
	}
	

}

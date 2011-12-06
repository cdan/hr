package org.xianairlines.action.staffs;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import org.xianairlines.model.Staffs;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


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
		Calendar onemonth = (Calendar)c.clone();
		onemonth.add(Calendar.MONTH, +1);
		
		List<Object[]> result =
		      entityManager.createQuery("select staffs from Staffs staffs where staffs.zhuanzhengdate <= :onemonth and staffs.zhuanzhengdate >= :now")
		      .setParameter("onemonth", onemonth.getTime())
              .setParameter("now", Calendar.getInstance().getTime())
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

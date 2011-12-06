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

@Name("contractExpireAlertList")
public class ContractExpireAlertList extends EntityQuery<Staffs> {

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
		sixmonth.add(Calendar.MONTH, +6);

		List<Object[]> result =
		      entityManager.createQuery("select staffs from Staffs staffs where staffs.contactExpireDate <= :sixmonth and staffs.contactExpireDate >= :now")
		      .setParameter("sixmonth", sixmonth.getTime())
              .setParameter("now", Calendar.getInstance().getTime())
		      .getResultList();
		return result;
	}


	@In(value = "#{facesContext.externalContext}")
	private ExternalContext extCtx;

	@In(value = "#{facesContext}")
	FacesContext facesContext;

	private Staffs staffs = new Staffs();

	public ContractExpireAlertList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Staffs getStaffs() {
		return staffs;
	}
	

}

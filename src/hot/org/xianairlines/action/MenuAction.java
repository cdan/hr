package org.xianairlines.action;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.hibernate.collection.PersistentCollection;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.xianairlines.model.Menus;

import java.util.ArrayList;
import java.util.List;

@Name("menuAction")
@Scope(ScopeType.EVENT)
public class MenuAction {
	
	public String getTreeMenus() {
		List<Menus> list = new ArrayList<Menus>();
		Menus menu = new Menus();
		menu.setId(1l);
		menu.setLeaf(true);
		menu.setText("人员管理");
		menu.setUrl("");
		list.add(menu);
			Menus subMenu = new Menus();
			subMenu.setId(2l);
			subMenu.setLeaf(true);
			subMenu.setText("人员维护");
			subMenu.setUrl("staffs/StaffsList.seam");
			menu.addChildren(subMenu);
			
			subMenu = new Menus();
			subMenu.setId(3l);
			subMenu.setLeaf(true);
			subMenu.setText("导入人员信息");
			subMenu.setUrl("staffs/ImportStaffs.seam");
			menu.addChildren(subMenu);
			
			subMenu = new Menus();
			subMenu.setId(8l);
			subMenu.setLeaf(true);
			subMenu.setText("转正提醒");
			subMenu.setUrl("staffs/NewHireAlertList.seam");
			menu.addChildren(subMenu);

            subMenu = new Menus();
			subMenu.setId(9l);
			subMenu.setLeaf(true);
			subMenu.setText("合同到期提醒");
			subMenu.setUrl("staffs/ContractExpireAlertList.seam");
			menu.addChildren(subMenu);

            subMenu = new Menus();
			subMenu.setId(11l);
			subMenu.setLeaf(true);
			subMenu.setText("退休提醒");
			subMenu.setUrl("staffs/RetireAlertList.seam");
			menu.addChildren(subMenu);
			
		menu = new Menus();
		menu.setId(4l);
		menu.setLeaf(true);
		menu.setText("权限管理");
		menu.setUrl("");
		list.add(menu);
			subMenu = new Menus();
			subMenu.setId(5l);
			subMenu.setLeaf(true);
			subMenu.setText("用户管理");
			subMenu.setUrl("UserList.seam");
			menu.addChildren(subMenu);

            subMenu = new Menus();
			subMenu.setId(10l);
			subMenu.setLeaf(true);
			subMenu.setText("修改密码");
			subMenu.setUrl("password.seam");
			menu.addChildren(subMenu);
			
		menu = new Menus();
		menu.setId(6l);
		menu.setLeaf(true);
		menu.setText("技术支持");
		menu.setUrl("");
		list.add(menu);
			subMenu = new Menus();
			subMenu.setId(7l);
			subMenu.setLeaf(true);
			subMenu.setText("联系方式");
			subMenu.setUrl("contactus.seam");
			menu.addChildren(subMenu);
 		return this.objectToJson(list);
	}
	
	private String objectToJson(Object obj){
		PropertyFilter p = new PropertyFilter() {
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if (arg2 instanceof PersistentCollection
						|| arg2 instanceof org.hibernate.proxy.HibernateProxy) {
					return true;
				}
				return false;
			}
		};
		JsonConfig j = new JsonConfig();
		j.setSkipJavaIdentifierTransformationInMapKeys(true);
		j.setJsonPropertyFilter(p);
		JSONArray json = JSONArray.fromObject(obj, j);
		return json.toString();
	}
	

}

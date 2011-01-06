package org.xianairlines.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

@Name("meta")
@Scope(ScopeType.APPLICATION)
@Startup
public class ApplicationMeta {

	private List<SelectItem> genderMeta = null;
	
	private List<SelectItem> nationMeta = null;
	
	private List<SelectItem> degreeMata = null;
	
	private List<SelectItem> eduBgMata = null;
	
	private List<SelectItem> maritalMata = null;
	
	private List<SelectItem> drivingmeta = null;
	
	private List<SelectItem> politicsMeta = null;
	
	private List<SelectItem> roleMeta = null;
	

	public List<SelectItem> getGenderMeta() {
		return genderMeta;
	}

	public List<SelectItem> getNationMeta() {
		return nationMeta;
	}

	public List<SelectItem> getDegreeMata() {
		return degreeMata;
	}

	public List<SelectItem> getEduBgMata() {
		return eduBgMata;
	}

	public List<SelectItem> getMaritalMata() {
		return maritalMata;
	}

	public List<SelectItem> getDrivingmeta() {
		return drivingmeta;
	}
	
	
	
	public List<SelectItem> getPoliticsMeta() {
		return politicsMeta;
	}
	
	public List<SelectItem> getRoleMeta() {
		return roleMeta;
	}

	@Create
	public void init(){
		genderMeta = new ArrayList<SelectItem>();
		genderMeta.add(new SelectItem("男", "男"));
		genderMeta.add(new SelectItem("女", "女"));
		
		nationMeta = new ArrayList<SelectItem>();
		nationMeta.add(new SelectItem("汉族", "汉族"));
		nationMeta.add(new SelectItem("蒙古族", "蒙古族"));
		nationMeta.add(new SelectItem("满族", "满族"));
		
		
		eduBgMata = new ArrayList<SelectItem>();
		eduBgMata.add(new SelectItem("专科", "专科"));
		eduBgMata.add(new SelectItem("本科", "本科"));
		eduBgMata.add(new SelectItem("硕士研究生", "硕士研究生"));
		eduBgMata.add(new SelectItem("博士研究生", "博士研究生"));
		eduBgMata.add(new SelectItem("其他", "其他"));
		
		
		degreeMata = new ArrayList<SelectItem>();
		degreeMata.add(new SelectItem("学士", "学士"));
		degreeMata.add(new SelectItem("硕士", "硕士"));
		degreeMata.add(new SelectItem("博士", "博士"));
		
		
		
		maritalMata = new ArrayList<SelectItem>();
		maritalMata.add(new SelectItem("已婚", "已婚"));
		maritalMata.add(new SelectItem("未婚", "未婚"));
		
		drivingmeta = new ArrayList<SelectItem>();
		drivingmeta.add(new SelectItem("A1", "A1"));
		drivingmeta.add(new SelectItem("A2", "A2"));
		drivingmeta.add(new SelectItem("A3", "A3"));
		drivingmeta.add(new SelectItem("B1", "B1"));
		drivingmeta.add(new SelectItem("C1", "C1"));
		
		
		politicsMeta  = new ArrayList<SelectItem>(); 
		politicsMeta.add(new SelectItem("群众", "群众"));
		politicsMeta.add(new SelectItem("团员", "团员"));
		politicsMeta.add(new SelectItem("党员", "党员"));
		politicsMeta.add(new SelectItem("其他", "其他"));
		
		roleMeta  = new ArrayList<SelectItem>(); 
		roleMeta.add(new SelectItem("admin", "管理员"));
		roleMeta.add(new SelectItem("user", "普通用户"));
		
	}
	
	
}

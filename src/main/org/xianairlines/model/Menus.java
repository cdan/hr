package org.xianairlines.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menus implements Serializable {
	private static final long serialVersionUID = 2642513133585974055L;
	private Long id;
	private String text;
	private Boolean leaf;
	private String url;
	
	private List<Menus> children = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public void addChildren(Menus node) {
		if (children == null) {
			children = new ArrayList<Menus>();
		}
		children.add(node);
	}

	public List<Menus> getChildren() {
		return children;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}

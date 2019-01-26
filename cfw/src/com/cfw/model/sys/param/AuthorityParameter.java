package com.cfw.model.sys.param;

import core.support.ExtJSBaseParameter;

import java.util.List;

/**
 * @author Ray
 * @
 */
public class AuthorityParameter extends ExtJSBaseParameter {

	private static final long serialVersionUID = 2903229213249813463L;
	private String $eq_menuCode;
	private String $like_menuName;
	private List<com.cfw.model.sys.Authority> subAuthorityList;

	public String get$eq_menuCode() {
		return $eq_menuCode;
	}

	public void set$eq_menuCode(String $eq_menuCode) {
		this.$eq_menuCode = $eq_menuCode;
	}

	public String get$like_menuName() {
		return $like_menuName;
	}

	public void set$like_menuName(String $like_menuName) {
		this.$like_menuName = $like_menuName;
	}

	public List<com.cfw.model.sys.Authority> getSubAuthorityList() {
		return subAuthorityList;
	}

	public void setSubAuthorityList(List<com.cfw.model.sys.Authority> subAuthorityList) {
		this.subAuthorityList = subAuthorityList;
	}

}

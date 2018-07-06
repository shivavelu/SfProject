package com.sfbay.pages;


public class LocatorHelper {

	public static String setLocatorEdit(String searchItem) {

		String editxpath = "//a[contains(text(),'" + searchItem
				+ "')]/parent::td/following-sibling::td//button[normalize-space()='edit']";
		return editxpath;

	}

	
}
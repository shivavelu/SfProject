package com.sfbay.customexception;

@SuppressWarnings("serial")
public class ElementNotFoundException extends RuntimeException {

	public ElementNotFoundException() {

	}

	public ElementNotFoundException(String s) {
		super(s);
	}

}

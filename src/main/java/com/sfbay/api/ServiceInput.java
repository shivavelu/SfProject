package com.sfbay.api;

import java.util.HashMap;
import java.util.Map;

public class ServiceInput extends PropertyFileUtil {
	protected Map<String, String> header;
	protected String cl_session_value;
	protected String cl_b_value;
	protected String csrf_value;
	protected String clskey = "cl_session=";
	protected String clbkey = "cl_b=";
	protected String csrfkey = "csrf=";
	protected String cookieCL;
	protected String cookieCB;
	protected String headercsrf;
	protected String ccr = "cl_def_hp=sfbay";
	protected final String loginURL = PropertyFileUtil.propapi.getProperty("loginURL");
	protected String v1URL = "https://sfbay.craigslist.org/session/?v=1";

	public ServiceInput() {
		header = new HashMap<String, String>();
		header.put("Cookie", "cl_def_hp=sfbay; cl_b=Oks2jIYu6BGiE5CadTbqaAvEA4k; cl_tocmode=sss%3Agrid");
		header.put("Referer", "https://accounts.craigslist.org/login?");

	}

	protected String getSaveRedirectUrl(String searchWord) {
		return "https://accounts.craigslist.org/savesearch/save?URL=https%3A%2F%2Fsfbay%2Ecraigslist%2Eorg%2Fsearch%2Fsss%3Fquery%3D"
				+ searchWord + "%26sort%3Drel&_csrf=";

	}

	protected Map<String, String> getParams(SearchData searchData) {

		Map<String, String> params = new HashMap<String, String>();
		String userName = searchData.getUserName();
		String passWord = searchData.getPassword();
		params.put("inputEmailHandle", userName);
		params.put("inputPassword", passWord);
		return params;
	}

	protected String getLoginSaveUrl(String searchWord) {
		return "https://accounts.craigslist.org/login?rt=L&rp=%2Fsavesearch%2Fsave%3FURL%3Dhttps%253A%252F%252Fsfbay%252Ecraigslist%252Eorg%252Fsearch%252Fsss%253Fquery%253D"
				+ searchWord + "%2526sort%253Drel%26_csrf%3D";
	}

	protected String getSearchUrl(SearchData searchData) {

		return "https://sfbay.craigslist.org/search/sss?query=" + searchData.getSearchWord() + "&sort=rel";
	}

    protected String getSearchFilterPageUrl(SearchData searchData) {

		return "https://sfbay.craigslist.org/search/sss?s=" + searchData.getPageToPrint() + "&query="
				+ searchData.getSearchWord() + "&sort=rel";

	}

}
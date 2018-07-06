package com.sfbay.api;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RedirectConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ServicesAPI extends ServiceInput {

	public boolean searchValue;

	public Response loginService(SearchData searchData) throws Exception {

		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.addParams(getParams(searchData));
		builder.addHeaders(header);
		RequestSpecification requestSpec = builder.build();
		Response lg = given().spec(requestSpec).when().post(loginURL).thenReturn();
		cl_session_value = lg.getCookie("cl_session");
		cookieCL = "cl_session=" + cl_session_value;
		return lg;

	}

	/*
	 * Method for search
	 */

	public Response searchService(SearchData searchData) throws Exception {

		Response searchResponse = given().when().get(getSearchUrl(searchData)).thenReturn();
		cl_b_value = searchResponse.getCookie("cl_b");
		cookieCB = "cl_b=" + cl_b_value;
		ccr = ccr + "; " + cookieCB;
		ccr = ccr + "; " + "cl_tocmode=sss%3Agrid%2Cggg%3Alist%2Cjjj%3Alist%2Cfav%3Alis";
		ccr = ccr + "; " + cookieCL;
		return searchResponse;
	}

	public Response searchFilterService(SearchData searchData) throws Exception {
		searchValue = true;
		ArrayList al = new ArrayList();
		Response filterPage = given().when().get(getSearchFilterPageUrl(searchData)).thenReturn();
		String htmlString = filterPage.asString();
		Document html = Jsoup.parse(htmlString);
		Elements h1 = html.body().getElementsByTag("a");
		for (Element ht : h1) {
			String st = ht.text();
			if (!st.contains("restore") && !st.contains("$")) {
				al.add(st);
			}
		}
		int sind = al.indexOf("make and model: " + searchData.getSearchWord());
		int eind = al.indexOf("^ back to top");
		try{
		for (int i = sind; i < eind; i++) {
			System.out.println(al.get(i));			
		}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.getMessage();
		}

		if (al.size() < 1) {
			searchValue = false;
		}

		return filterPage;

	}
	/*
	 * Method for parse CSRF tokean
	 */

	String getV1Service(SearchData searchData) throws Exception {
		Map<String, String> headerv1 = new HashMap<String, String>();
		headerv1.put("Referer", getSearchUrl(searchData));
		headerv1.put("X-Requested-With", "XMLHttpRequest");
		headerv1.put("Accept-Encoding", "gzip, deflate, br");
		headerv1.put("Accept-Language", "en-US,en;q=0.9");
		headerv1.put("Cookie", ccr);
		RequestSpecBuilder searchBuilder = new RequestSpecBuilder();
		searchBuilder.addHeaders(headerv1);
		RequestSpecification requestSpecSt = searchBuilder.build();
		Response s = given().spec(requestSpecSt).when().get(v1URL).thenReturn();
		Object t = s.jsonPath().getJsonObject("csrf");
		csrf_value = t.toString();
		String loginsaveURL = getLoginSaveUrl(searchData.getSearchWord()) + csrf_value;
		String savedirectURL = getSaveRedirectUrl(searchData.getSearchWord()) + csrf_value;
		return savedirectURL;
	}

	public Response searchSaveService(SearchData searchData) throws Exception {

		String savedirectURL = getV1Service(searchData);
		RequestSpecBuilder searchBuilder2 = new RequestSpecBuilder();

		Map<String, String> sb2param = new HashMap<String, String>();
		sb2param.put("URL",searchData.getUrl()
				+ searchData.getSearchWord() + searchData.getUrlFilter());
		sb2param.put("_csrf", csrf_value);
		searchBuilder2.addParams(sb2param);

		Map<String, String> st = new HashMap<String, String>();
		st.put("Referer", getSearchUrl(searchData));
		st.put("Cookie", ccr);
		searchBuilder2.addHeaders(st);

		RequestSpecification requestSpecSt2 = searchBuilder2
				.setConfig(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false)))
				.build();

		Response saveRslt2 = given().spec(requestSpecSt2).when().get(savedirectURL).thenReturn();
		return saveRslt2;

	}
}

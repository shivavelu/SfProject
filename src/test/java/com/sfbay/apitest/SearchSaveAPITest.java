package com.sfbay.apitest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.sfbay.api.SearchData;
import com.sfbay.api.PropertyFileUtil;
import com.sfbay.api.ServicesAPI;
import io.restassured.response.Response;

public class SearchSaveAPITest {

	private ServicesAPI sAPI;

	@BeforeClass
	public void Init() {
		sAPI = new ServicesAPI();

	}

	SearchData getTestData() {

		SearchData searchData = new SearchData();
		searchData.setUserName(PropertyFileUtil.readProperty("username"));
		searchData.setPassword(PropertyFileUtil.readProperty("password"));
		searchData.setSearchWord(PropertyFileUtil.readProperty("searchitem"));
		searchData.setPageToPrint(PropertyFileUtil.readProperty("pageSearchItem"));
		searchData.setPageToPrint(PropertyFileUtil.readProperty("URL"));
		searchData.setPageToPrint(PropertyFileUtil.readProperty("URLfilter"));

		return searchData;
	}

	@Test
	public void loginTest() {
		try {
			Response loginTestResponse = sAPI.loginService(getTestData());
			if (loginTestResponse.getStatusCode() == Integer
					.parseInt(PropertyFileUtil.propapi.getProperty("redirect"))) {
				Assert.assertEquals(loginTestResponse.header("location"),
						PropertyFileUtil.propapi.getProperty("loginRedirect"));
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Test
	public void searchTest() {

		try {
			Response searchTestResponse = sAPI.searchService(getTestData());
			Assert.assertEquals(searchTestResponse.getStatusCode(),
					Integer.parseInt(PropertyFileUtil.propapi.getProperty("success")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void searchFilterTest() {

		try {

			Assert.assertEquals(sAPI.searchFilterService(getTestData()).getStatusCode(),
					Integer.parseInt(PropertyFileUtil.propapi.getProperty("success")));
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	public void searchSaveTest() {

		try {
			sAPI.searchSaveService(getTestData());
			Assert.assertEquals(sAPI.searchSaveService(getTestData()).statusCode(),
					Integer.parseInt(PropertyFileUtil.propapi.getProperty("redirect")));

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}

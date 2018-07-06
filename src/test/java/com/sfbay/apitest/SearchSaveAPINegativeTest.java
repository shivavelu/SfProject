package com.sfbay.apitest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.sfbay.api.SearchData;
import com.sfbay.api.PropertyFileUtil;
import com.sfbay.api.ServicesAPI;
import io.restassured.response.Response;

public class SearchSaveAPINegativeTest {

	public ServicesAPI sAPI;
	String loginRedirect;
	String searchRedirect;
	String searchSaveRedirect;
	Response loginTestResponse;
	Response searchTestResponse;
	Response searchSaveResponse;

	@BeforeClass
	public void Init() {
		sAPI = new ServicesAPI();

	}

	SearchData getTestData() {

		SearchData searchData = new SearchData();
		searchData.setUserName(PropertyFileUtil.readProperty("invalidUsername"));
		searchData.setPassword(PropertyFileUtil.readProperty("invalidPassword"));
		searchData.setSearchWord(PropertyFileUtil.readProperty("invalidSearchitem"));
		searchData.setPageToPrint(PropertyFileUtil.readProperty("pageSearchItem"));
		return searchData;
	}

	@Test
	public void loginTestNegative() {
		try {
			loginTestResponse = sAPI.loginService(getTestData());

			Assert.assertNotEquals(loginTestResponse.statusCode(),
					Integer.parseInt(PropertyFileUtil.propapi.getProperty("redirect")));

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Test
	public void searchTestNegative() {

		try {
			searchTestResponse = sAPI.searchService(getTestData());
			Assert.assertNotEquals(loginTestResponse.statusCode(),
					Integer.parseInt(PropertyFileUtil.propapi.getProperty("redirect")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void searchFilterTestNegative() {

		try {

			Assert.assertFalse(sAPI.searchValue);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	public void searchSaveTestNegative() {

		try {
			sAPI.searchSaveService(getTestData());
			Assert.assertNotEquals(sAPI.searchSaveService(getTestData()).statusCode(),
					Integer.parseInt(PropertyFileUtil.propapi.getProperty("success")));

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}

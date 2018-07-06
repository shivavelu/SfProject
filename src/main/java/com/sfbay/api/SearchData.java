package com.sfbay.api;

public class SearchData {

	private String userName;
	private String password;
	private String searchWord;
	private String pageToPrint;
    private String url;
    private String urlFilter;
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlFilter() {
		return urlFilter;
	}

	public void setUrlFilter(String urlFilter) {
		this.urlFilter = urlFilter;
	}
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getPageToPrint() {
		return pageToPrint;
	}

	public void setPageToPrint(String pageToPrint) {
		this.pageToPrint = pageToPrint;
	}

}

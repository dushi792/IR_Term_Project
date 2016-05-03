package edu.pitt.IRWebProject.searchRecord.bo;

import java.util.Date;

public class SearchRecord {
	private int searchID;
	private String query;
	private Date datetime;
	private int userID = -1;

	/**
	 * define default constructor for mybatis
	 */
	public SearchRecord() {

	}

	/**
	 * constructor for only query and datetime
	 * 
	 * @param query
	 * @param datetime
	 */
	public SearchRecord(String query, Date datetime) {
		this.setQuery(query);
		this.setDatetime(datetime);
	}

	/**
	 * constructor for query, datatime and userID
	 * 
	 * @param query
	 * @param datetime
	 * @param userID
	 */
	public SearchRecord(String query, Date datetime, int userID) {
		this.setQuery(query);
		this.setDatetime(datetime);
		this.setUserID(userID);
	}

	public int getSearchID() {
		return searchID;
	}

	public void setSearchID(int searchID) {
		this.searchID = searchID;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
}

package edu.pitt.IRWebProject.searchRecord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pitt.IRWebProject.searchRecord.bo.SearchRecord;
import edu.pitt.IRWebProject.searchRecord.dao.SearchRecordDAO;

@Service
public class SearchRecordServices {
	@Autowired
	private SearchRecordDAO searchRecordDAO;

	/**
	 * get SearchRecord by query (like)
	 * 
	 * @param query
	 * @return
	 */
	public List<SearchRecord> selectSearchByQueryLike(String query) {
		return searchRecordDAO.selectSearchRecordByQueryLike(query);
	}

	/**
	 * insert SearchRecord when search
	 * 
	 * @param searchRecord
	 * @return
	 */
	public int insertSearchRecord(SearchRecord searchRecord) {
		return searchRecordDAO.insertSearchRecord(searchRecord);
	}
}

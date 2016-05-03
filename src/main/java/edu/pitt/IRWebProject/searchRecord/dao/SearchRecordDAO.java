package edu.pitt.IRWebProject.searchRecord.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import edu.pitt.IRWebProject.searchRecord.bo.SearchRecord;

@Repository
public interface SearchRecordDAO {

	public List<SearchRecord> selectSearchRecordByQueryLike(@Param("query") String query);

	public int insertSearchRecord(SearchRecord searchRecord);
}

package edu.pitt.IRWebProject.searchRecord.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pitt.IRWebProject.searchRecord.bo.SearchRecord;
import edu.pitt.IRWebProject.searchRecord.service.SearchRecordServices;

@Controller
@RequestMapping("/searchRecord/")
public class SearchRecordController {
	@Autowired
	private SearchRecordServices searchRecordServices;

	/**
	 * get search tip by input query
	 * 
	 * @param response
	 * @param query
	 * @return
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "searchTip.json", produces = "application/json")
	@ResponseBody
	public String getSearchTip(HttpServletResponse response,
			@RequestParam(value = "query", required = true) String query)
					throws JSONException, UnsupportedEncodingException {
		response.setContentType("application/json");

		// decode query with correct encoding
		String queryEncode = URLEncoder.encode(query, "ISO-8859-1");
		query = URLDecoder.decode(queryEncode, "UTF-8");

		List<SearchRecord> list = searchRecordServices.selectSearchByQueryLike(query);
		JSONArray result = new JSONArray();
		if (result != null) {
			for (SearchRecord search : list) {
				JSONObject jo = new JSONObject();
				jo.put("query", URLEncoder.encode(search.getQuery(), "UTF-8"));
				result.put(jo);
			}
		}
		JSONObject jo = new JSONObject();
		jo.put("list", list);
		return jo.toString();
	}
}

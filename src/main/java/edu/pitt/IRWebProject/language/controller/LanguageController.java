package edu.pitt.IRWebProject.language.controller;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * language controller to handler language setting requests
 * 
 * @author Ian
 *
 */
@Controller
@RequestMapping("/language/")
public class LanguageController {

	/**
	 * set language controller, add language cookie
	 * 
	 * @param httpResponse
	 * @param languageShortName
	 * @return
	 */
	@RequestMapping("setLanguage.json")
	@ResponseBody
	public String setLanguage(HttpServletResponse httpResponse,
			@RequestParam(value = "language", required = true) String languageShortName) {
		JSONObject result = new JSONObject();
		if ("en".equals(languageShortName) || "zh-CHS".equals(languageShortName)) {
			Cookie cookie = new Cookie("language", languageShortName);
			cookie.setPath("/");
			cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(365));
			httpResponse.addCookie(cookie);
			result.put("errorCode", 0);
		} else {
			result.put("errorCode", 2);
			result.put("errorMessage", "invalid language short name");
		}
		return result.toString();
	}
}

package edu.pitt.IRWebProject.language.bo;

import com.memetix.mst.language.Language;

public class LanguageBO {
	private String displayName;
	private String shortName;
	private Language language;
	
	public LanguageBO(String shortName) throws Exception{
		setShortName(shortName);
		setLanguage(Language.fromString(shortName));
		setDisplayName(language.getName(language));
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
}

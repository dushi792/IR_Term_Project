package edu.pitt.IRWebProject.stackoverflow.parser;

import java.util.List;

class Question {
	Owner owner;
	String link;
	long last_activity_date;
	long creation_date;
	int answer_count;
	String title;
	int question_id;
	List<String> tags;
	int score;
	int accepted_answer_id;
	long protected_date;
	boolean is_answered;
	int view_count;	
}
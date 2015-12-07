package com.bingtranslator.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;


/**
* @JavaVersion 1.8
* @ClassName: bingTranslator
* @Description: TODO
* @author Siwei Liu
* @date Dec 6, 2015 12:46:04 PM
*
*/
public class bingTranslator {
	Language originLan;
	Language destLan;
	final int MAXBYTES = 10240;
	int bytesPerChar;
	String delimeter;
	
	public bingTranslator(Language from, Language to){
		Translate.setClientId("siweiliu42");
        Translate.setClientSecret("DmPmwMFLun2qPQL7JoZ3YjocpK0REDnnnJ81oOTW1s8=");
		originLan = from;
		destLan = to;
		if(originLan == Language.CHINESE_SIMPLIFIED){
			bytesPerChar = 6;
			delimeter = "。";
		}
		else{
			bytesPerChar = 1;
			delimeter = ".";
		}
	}
	public String processLargeText(String line) throws Exception{
		StringBuilder output = new StringBuilder();
		while(line != null){
			int pos = line.lastIndexOf(delimeter, MAXBYTES/bytesPerChar);
			String ttt = line.substring(0,pos+1);
			String tmp = Translate.execute(line.substring(0,pos+1), originLan, destLan);
			output.append(tmp);
			if(pos+2 < line.length()){
				line = line.substring(pos+2);
			}	
			else{
				line = null;
			}
		}
		return output.toString(); 
	}
	
	public void translate() throws Exception{
		String inputFile = "data//questions.txt";
		String outputFile = "data//questions_trans.txt";
        File file = new File(inputFile);
        File dest = new File(outputFile);
	    try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
			BufferedWriter writer  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest),"utf-8"));
			String line = "";
			while((line = reader.readLine()) !=null){
				String string = "";
				if(line.getBytes().length < MAXBYTES){
					string = Translate.execute(line, originLan, destLan);
				}
				else{
					System.out.println("dddd");
					string = processLargeText(line);
				}
				writer.write(string+"\r\n");
				writer.flush();	
			}
			writer.flush();
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		bingTranslator trans = new bingTranslator(Language.CHINESE_SIMPLIFIED, Language.ENGLISH);
		trans.translate();
	}

}
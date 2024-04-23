package com.organicfoods.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public HttpUtil(String value) {
		this.value = value;
	}
	
	//Convert JSON to String JSON
	public static HttpUtil toStrJSON(BufferedReader br) {
		try {
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			return new HttpUtil(sb.toString());
		} catch (IOException e) {
			return null;
		}
	}
	
	//Convert To Model
	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (IOException e) {
			return null;
		}
	}
	
	//Convert To JSON
	public static void toJSON(OutputStream out, Object object) {
		try {
			new ObjectMapper().writeValue(out, object);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
}

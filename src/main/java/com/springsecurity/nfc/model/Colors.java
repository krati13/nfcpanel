package com.springsecurity.nfc.model;

import java.util.HashMap;
import java.util.Map;

public class Colors {
	private final static Map<Integer, String> COLOR_MAP=new HashMap<Integer, String>();
	
	static{
		COLOR_MAP.put(1, "#fdabc7");
		COLOR_MAP.put(2, "#f5f8fa");
		COLOR_MAP.put(3, "#aed5fc");
		COLOR_MAP.put(4, "#cde2bd");
		COLOR_MAP.put(5, "#a33636");
		COLOR_MAP.put(6, "#c9fcf3");
		COLOR_MAP.put(7, "#ba1a1a");
		COLOR_MAP.put(8, "#000080");
		COLOR_MAP.put(9, "#728308");
		COLOR_MAP.put(10, "#228b22");
		COLOR_MAP.put(11, "#787777");
		COLOR_MAP.put(12, "#d5bee0");
		COLOR_MAP.put(13, "#787777");
		COLOR_MAP.put(14, "#cdb79e");
		COLOR_MAP.put(15, "#eee8aa");
		COLOR_MAP.put(16, "#c3848e");
		COLOR_MAP.put(17, "#c38cd4");
		COLOR_MAP.put(18, "#4a4a4a");
		COLOR_MAP.put(19, "#c38cd4");
	}
	
	
	public static String getColor(int i) {
		return COLOR_MAP.get(i);
	}

}

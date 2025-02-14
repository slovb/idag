package com.github.slovb.idag.day;

import java.util.LinkedHashMap;
import java.util.Map;

public class InformationEntry extends Entry {

	public Map<String, Boolean> data = new LinkedHashMap<String, Boolean>();

	public InformationEntry() {
	}

	@Override
	public String getType() {
		return "information";
	}
}

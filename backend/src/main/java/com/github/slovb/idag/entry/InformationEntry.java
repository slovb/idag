package com.github.slovb.idag.entry;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("information")
public class InformationEntry extends Entry {

	public Map<String, Boolean> data = new LinkedHashMap<String, Boolean>();

	public InformationEntry() {
	}

	@Override
	public String getType() {
		return "information";
	}
}

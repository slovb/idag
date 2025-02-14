package com.github.slovb.idag.day;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.slovb.idag.entry.InformationEntry;
import com.github.slovb.idag.entry.OperationEntry;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Day {

	public String date;
	public List<OperationEntry> operations;
	public List<InformationEntry> information;
	public Form formBefore;
	public Form formAfter;

	public Day() {

	}

	/**
	 * Union together all information into one cohesive snapshot
	 *
	 * @return the union of information in list order
	 */
	public Map<String, Boolean> getSnapshot() {
		Map<String, Boolean> snapshot = new LinkedHashMap<String, Boolean>();
		for (InformationEntry information: information) {
			snapshot.putAll(information.data);
		}
		return snapshot;
	}
}

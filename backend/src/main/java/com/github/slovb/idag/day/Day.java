package com.github.slovb.idag.day;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Day {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date date;
	public List<OperationEntry> actions;
	public List<InformationEntry> information;
	public List<Form> formBefore;
	public List<Form> formAfter;

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

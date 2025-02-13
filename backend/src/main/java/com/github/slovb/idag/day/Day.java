package com.github.slovb.idag.day;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Day {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date date;
	public List<Action> actions;
	public List<Information> information;
	public List<Form> formBefore;
	public List<Form> formAfter;

	public Day() {

	}

	/**
	 * Union together all information into one cohesive snapshot
	 *
	 * @return the union of information in list order
	 */
	public Information getSnapshot() {
		Information snapshot = new Information();
		for (Information info: information) {
			snapshot.putAll(info);
		}
		return snapshot;
	}
}

package com.github.slovb.idag.day;

import java.util.ArrayList;
import java.util.List;

public class Form {

	public List<Row> rows = new ArrayList<Row>();

	public Form() {
	}

	public void add(String key, String title) {
		for (Row row: rows) {
			if (key.equals(row.key)) {
				// Do not add duplicate rows
				return;
			}
		}
		Row row = new Row();
		row.key = key;
		row.title = title;
		rows.add(row);
	}

	public void remove(String key) {
		rows.removeIf(row -> key.equals(row.key));
	}

	public void changeTitle(String key, String title) {
		for (Row row: rows) {
			if (key.equals(row.key)) {
				row.title = title;
			}
		}
	}
}

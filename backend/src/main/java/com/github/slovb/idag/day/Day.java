package com.github.slovb.idag.day;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.slovb.idag.entry.Entry;
import com.github.slovb.idag.entry.EntryStorage;
import com.github.slovb.idag.entry.InformationEntry;
import com.github.slovb.idag.entry.OperationEntry;
import com.github.slovb.idag.entry.UnknownEntryException;
import com.github.slovb.idag.form.Form;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Day {

	public String date;
	public List<OperationEntry> operations;
	public List<InformationEntry> information;
	public Form formBefore;
	public Form formAfter;

	private Day() {
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

	/**
	 * Static factory
	 *
	 * @param key key for the Day
	 * @param entries sorted iterable of entries
	 * @return Day for the given key
	 */
	public static Day of(String key, Iterable<Entry> entries) throws UnknownEntryException {
		Day day = new Day();
		day.date = key;
		day.operations = new ArrayList<OperationEntry>();
		day.information = new ArrayList<InformationEntry>();
		day.formBefore = new Form();

		for (Entry entry: entries) {
			if (entry.isStrictlyAfter(key)) {
				continue;
			}
			else if (entry.isStrictlyBefore(key)) {
				if (entry instanceof OperationEntry) {
					((OperationEntry) entry).operate(day.formBefore);
				}
			}
			else {
				if (entry instanceof OperationEntry) {
					day.operations.add((OperationEntry) entry);
				}
				else if (entry instanceof InformationEntry) {
					day.information.add((InformationEntry) entry);
				}
				else {
					Log.error("Unknown entry");
					throw new UnknownEntryException();
				}
			}
		}

		day.formAfter = new Form(day.formBefore);
		for (OperationEntry entry: day.operations) {
			entry.operate(day.formAfter);
		}
		return day;
	}
}

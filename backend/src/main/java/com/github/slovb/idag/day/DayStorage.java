package com.github.slovb.idag.day;

import java.util.ArrayList;

import com.github.slovb.idag.entry.Entry;
import com.github.slovb.idag.entry.EntryStorage;
import com.github.slovb.idag.entry.InformationEntry;
import com.github.slovb.idag.entry.OperationEntry;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DayStorage {

	// TODO Restructure this as it is mostly an instanced Factory now, which seems wonky

	@Inject
	EntryStorage entryStorage;

	public DayStorage() {
	}

	public Day get(String key) {
		// TODO: Rewrite this in a neater way, probably in a Factory somewhere
		Day day = new Day();
		day.date = key;
		day.operations = new ArrayList<OperationEntry>();
		day.information = new ArrayList<InformationEntry>();
		day.formBefore = new Form();

		for (Entry entry: entryStorage.list()) {
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
					// TODO!!! throw proper exception
				}
			}
		}

		day.formAfter = new Form();
		for (Row row: day.formBefore.rows) {
			Row hardCopy = new Row();
			hardCopy.key = row.key;
			hardCopy.title = row.title;
			day.formAfter.rows.add(hardCopy);
		}
		for (OperationEntry entry: day.operations) {
			entry.operate(day.formAfter);
		}
		return day;
	}
}

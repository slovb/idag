package com.github.slovb.idag.day;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.slovb.idag.entry.EntryStorage;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DayStorage {

	// Set to package-private to avoid reflection on injection (TODO read up on the details of this)
	Map<String, Day> days = new LinkedHashMap<String, Day>();

	@Inject
	EntryStorage entryStorage;

	public DayStorage() {
		try (InputStream in = getClass().getResourceAsStream("/initial.json")) {
			Log.debug("Reading initial.json");
			ObjectMapper objectMapper = new ObjectMapper();
			Day[] init = objectMapper.readValue(in, Day[].class);
			for (Day day: init) {
				put(day);
			}
			Log.info(String.format("%s days added from initial.json", init.length));
		}
		catch (IOException e) {
			Log.error("Problem reading initial.json", e);
		}
	}

	public Day get(String key) {
		return days.get(key);
	}

	public void put(Day day) {
		String key = String.format("%1$tY-%1$tm-%1$td", day.date);
		days.put(key, day);
	}
}

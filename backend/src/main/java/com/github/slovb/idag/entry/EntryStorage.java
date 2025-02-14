package com.github.slovb.idag.entry;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.logging.Log;
import jakarta.inject.Singleton;

@Singleton
public class EntryStorage {

	// Set to package-private to avoid reflection on injection (TODO read up on the details of this)
	List<Entry> entries = new ArrayList<Entry>();

	public EntryStorage() {
		try (InputStream in = getClass().getResourceAsStream("/test_data.json")) {
			Log.debug("Reading test_data.json");
			ObjectMapper objectMapper = new ObjectMapper();
			Entry[] init = objectMapper.readValue(in, Entry[].class);
			for (Entry entry: init) {
				add(entry);
			}
			Log.info(String.format("%s entries added from test_data.json", init.length));
		}
		catch (IOException e) {
			Log.error("Problem reading initial.json", e);
		}
	}

	/**
	 * List all the entries ordered by <code>(key, index)</code> ascending
	 *
	 * @return
	 */
	public List<Entry> list() {
		return entries;
	}

	/**
	 * Add Entry as late in the list as possible without putting it ahead of one with a greater key
	 *
	 * This ordering is needed so that operations are iterated through in the intended order
	 *
	 * @param entry
	 */
	public void add(Entry entry) {
		// TODO write tests for this ordering
		int i = entries.size();
		while (i > 0 && entry.isStrictlyBefore(entries.get(i-1))) {
			i -= 1;
		}

		entries.add(i, entry);
	}
}

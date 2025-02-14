package com.github.slovb.idag.entry;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = As.PROPERTY,
	property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = InformationEntry.class, name = "information"),
	@JsonSubTypes.Type(value = OperationEntry.class, name = "operation")
})
abstract public class Entry {

	public String key;

	public Entry() {
	}

	abstract public String getType();

	public boolean isStrictlyAfter(String key) {
		return this.key.compareTo(key) > 0;
	}

	public boolean isStrictlyAfter(Entry entry) {
		return isStrictlyAfter(entry.key);
	}

	public boolean isStrictlyBefore(String key) {
		return this.key.compareTo(key) < 0;
	}

	public boolean isStrictlyBefore(Entry entry) {
		return isStrictlyBefore(entry.key);
	}
}

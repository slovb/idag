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
}

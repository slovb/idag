package com.github.slovb.idag.form.operation;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.github.slovb.idag.form.Form;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = As.PROPERTY,
	property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = AddInputOperation.class, name = "ADD_INPUT"),
	@JsonSubTypes.Type(value = RemoveInputOperation.class, name = "REMOVE_INPUT"),
	@JsonSubTypes.Type(value = SetLabelOperation.class, name = "SET_LABEL")
})
abstract public class Operation {
	public String key;

	abstract public void operate(Form form);

	abstract public String getType();
}

package com.github.slovb.idag.entry;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.github.slovb.idag.form.Form;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeName("operation")
public class OperationEntry extends Entry {
	public Op data;

	public OperationEntry() {
	}

	public OperationEntry(Op op) {
		this();
		this.data = op;
	}

	public void operate(Form form) {
		data.operate(form);
	}

	@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = As.PROPERTY,
		property = "type")
	@JsonSubTypes({
		@JsonSubTypes.Type(value = AddInput.class, name = "ADD_INPUT"),
		@JsonSubTypes.Type(value = RemoveInput.class, name = "REMOVE_INPUT"),
		@JsonSubTypes.Type(value = SetLabel.class, name = "SET_LABEL")
	})
	abstract public static class Op {
		public String type;
		public String key;

		abstract public void operate(Form form);
	}

	@JsonTypeName("ADD_INPUT")
	public static class AddInput extends Op {
		public String label;

		// TODO: Having to do this seems wrong and redundant, read the friendly manual!
		public AddInput() {
			super();
			this.type = "ADD_INPUT";
		}

		public void operate(Form form) {
			form.add(key, label);
		}
	}

	@JsonTypeName("REMOVE_INPUT")
	public static class RemoveInput extends Op {
		// TODO: Having to do this seems wrong and redundant, read the friendly manual!
		public RemoveInput() {
			super();
			this.type = "REMOVE_INPUT";
		}

		public void operate(Form form) {
			form.remove(key);
		}
	}

	@JsonTypeName("SET_LABEL")
	public static class SetLabel extends Op {
		public String label;

		// TODO: Having to do this seems wrong and redundant, read the friendly manual!
		public SetLabel() {
			super();
			this.type = "SET_LABEL";
		}

		public void operate(Form form) {
			form.setLabel(key, label);
		}
	}

	@Override
	public String getType() {
		return "operation";
	}
}

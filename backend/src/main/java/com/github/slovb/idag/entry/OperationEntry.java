package com.github.slovb.idag.entry;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.slovb.idag.day.Form;
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
		@JsonSubTypes.Type(value = ChangeTitle.class, name = "CHANGE_TITLE")
	})
	abstract public static class Op {
		public String type;
		public String key;

		abstract public void operate(Form form);
	}

	@JsonTypeName("ADD_INPUT")
	public static class AddInput extends Op {
		public String title;

		// TODO: Having to do this seems wrong and redundant, read the friendly manual!
		public AddInput() {
			super();
			this.type = "ADD_INPUT";
		}

		public void operate(Form form) {
			form.add(key, title);
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

	@JsonTypeName("CHANGE_TITLE")
	public static class ChangeTitle extends Op {
		public String title;

		// TODO: Having to do this seems wrong and redundant, read the friendly manual!
		public ChangeTitle() {
			super();
			this.type = "CHANGE_TITLE";
		}

		public void operate(Form form) {
			form.changeTitle(key, title);
		}
	}

	@Override
	public String getType() {
		return "operation";
	}
}

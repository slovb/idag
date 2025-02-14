package com.github.slovb.idag.entry;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
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

	@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = As.PROPERTY,
		property = "type")
	@JsonSubTypes({
		@JsonSubTypes.Type(value = AddInput.class, name = "ADD_INPUT"),
		@JsonSubTypes.Type(value = RemoveInput.class, name = "REMOVE_INPUT"),
		@JsonSubTypes.Type(value = ChangeTitle.class, name = "CHANGE_TITLE")
	})
	public static class Op {
		public String type;
		public String key;
	}

	@JsonTypeName("ADD_INPUT")
	public static class AddInput extends Op {
		public String title;

		// TODO: Having to do this seems wrong and redundant, read the friendly manual!
		public AddInput() {
			super();
			this.type = "ADD_INPUT";
		}
	}

	@JsonTypeName("REMOVE_INPUT")
	public static class RemoveInput extends Op {
		// TODO: Having to do this seems wrong and redundant, read the friendly manual!
		public RemoveInput() {
			super();
			this.type = "REMOVE_INPUT";
		}
	}

	@JsonTypeName("ADD_INPUT")
	public static class ChangeTitle extends Op {
		public String title;

		// TODO: Having to do this seems wrong and redundant, read the friendly manual!
		public ChangeTitle() {
			super();
			this.type = "CHANGE_TITLE";
		}
	}

	@Override
	public String getType() {
		return "operation";
	}
}

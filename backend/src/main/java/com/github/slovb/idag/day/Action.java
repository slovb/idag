package com.github.slovb.idag.day;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonSubTypes;

public class Action {
	// TODO: Unwrap this into base Action, this setup is too convoluted
	public Op op;

	public Action() {
	}

	public Action(Op op) {
		this();
		this.op = op;
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
}

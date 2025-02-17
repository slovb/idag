package com.github.slovb.idag.entry;

import com.github.slovb.idag.form.Form;
import com.github.slovb.idag.form.operation.Operation;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("operation")
public class OperationEntry extends Entry {
	public Operation data;

	public OperationEntry() {
	}

	public OperationEntry(Operation operation) {
		this();
		this.data = operation;
	}

	public void operate(Form form) {
		data.operate(form);
	}

	@Override
	public String getType() {
		return "operation";
	}
}

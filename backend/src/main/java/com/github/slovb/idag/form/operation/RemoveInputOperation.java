package com.github.slovb.idag.form.operation;

import com.github.slovb.idag.form.Form;

public class RemoveInputOperation extends Operation {

	public String label;

	@Override
	public void operate(Form form) {
		form.remove(key);
	}

	@Override
	public String getType() {
		return "REMOVE_INPUT";
	}
}

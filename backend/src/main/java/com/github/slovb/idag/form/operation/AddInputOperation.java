package com.github.slovb.idag.form.operation;

import com.github.slovb.idag.form.Form;

public class AddInputOperation extends Operation {

	public String label;

	@Override
	public void operate(Form form) {
		form.add(key, label);
	}

	@Override
	public String getType() {
		return "ADD_INPUT";
	}
}

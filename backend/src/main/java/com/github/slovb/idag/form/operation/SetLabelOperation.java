package com.github.slovb.idag.form.operation;

import com.github.slovb.idag.form.Form;

public class SetLabelOperation extends Operation {

	public String label;

	@Override
	public void operate(Form form) {
		form.setLabel(key, label);
	}

	@Override
	public String getType() {
		return "SET_LABEL";
	}
}

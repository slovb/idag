package com.github.slovb.idag.form;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A list of inputs and API methods for operating on the list
 */
@JsonSerialize(using = FormSerializer.class)
public class Form {

	// mostly hiding this to try to play with a custom serializer
	protected List<Input> inputs = new ArrayList<Input>();

	public Form() {
	}

	public Form(Form form) {
		for (Input input: form.list()) {
			this.add(new Input(input));
		}
	}

	public void add(Input input) {
		inputs.add(input);
	}

	public void add(String key, String label) {
		for (Input input: inputs) {
			if (key.equals(input.key)) {
				// Do not add duplicate rows
				return;
			}
		}
		Input input = new Input();
		input.key = key;
		input.label = label;
		this.add(input);
	}

	// specifically not naming this getInputs so that it won't naively serialize
	public List<Input> list() {
		return inputs;
	}

	public void remove(String key) {
		inputs.removeIf(row -> key.equals(row.key));
	}

	public void setLabel(String key, String label) {
		for (Input input: inputs) {
			if (key.equals(input.key)) {
				input.label = label;
			}
		}
	}
}

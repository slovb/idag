package com.github.slovb.idag.form;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Not actually needed with Form having list() renamed to getInputs(), but I did this to sate curiosity
 */
public class FormSerializer extends StdSerializer<Form> {

	private static final long serialVersionUID = 2514583600036913093L;

	public FormSerializer() {
		super(Form.class);
	}

	@Override
	public void serialize(Form value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeFieldName("inputs");
		gen.writeStartArray();
		for (Input input: value.list()) {
			provider.defaultSerializeValue(input, gen);
		}
		gen.writeEndArray();
		gen.writeEndObject();
	}
}

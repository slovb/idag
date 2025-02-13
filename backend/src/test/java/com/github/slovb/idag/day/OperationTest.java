package com.github.slovb.idag.day;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class OperationTest {

	@Test
	@DisplayName("Expect polymorphic serialization of Operation")
	@Tag("OPERATION")
	public void whenSerializingPolymorphic_thenCorrect() throws JsonProcessingException {
		Action.AddInput addInput = new Action.AddInput();
		Action operation = new Action(addInput);

		String result = new ObjectMapper().writeValueAsString(operation);

		assertThat(result, containsString("type"));
		assertThat(result, containsString("ADD_INPUT"));
	}
}

package com.github.slovb.idag.day;

import java.util.Map;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/day")
public class DayResource {

	@Inject
	private DayStorage dayStorage;

	@Operation(summary="Get all days")
	@APIResponse(responseCode="200", description="A list of all days")
    @GET
    public Map<String, Day> list() {
		return dayStorage.list();
    }
}

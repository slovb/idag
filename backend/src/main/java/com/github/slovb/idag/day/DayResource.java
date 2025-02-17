package com.github.slovb.idag.day;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import com.github.slovb.idag.entry.EntryStorage;
import com.github.slovb.idag.entry.UnknownEntryException;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/day")
public class DayResource {

	@Inject
	private EntryStorage entryStorage;

	@Operation(summary="Get a specific day")
	@APIResponse(responseCode="200", description="The state of the requested Day")
	@Path("{key}")
    @GET
    public Day get(String key) throws UnknownEntryException {
		return Day.of(key, entryStorage.list());
    }
}

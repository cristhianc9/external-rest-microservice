package com.external.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "external-api")
@Path("/api/consulta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ExternalApiClient {
    @GET
    @Path("/{clave}")
    Uni<ExternalApiResponse> consultarPorClave(@PathParam("clave") String clave);
}

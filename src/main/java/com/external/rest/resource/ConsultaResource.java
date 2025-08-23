package com.external.rest.resource;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.external.rest.client.ExternalApiClient;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/consulta")
public class ConsultaResource {

    @Inject
    @RestClient
    ExternalApiClient externalApiClient;

    @GET
    @Path("/{claveAcceso}")
    public Uni<Response> consultar(@PathParam("claveAcceso") String claveAcceso) {
        return externalApiClient.consultarPorClave(claveAcceso);
    }
}

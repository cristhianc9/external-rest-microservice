package com.external.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RegisterRestClient(configKey = "external-api")
public interface ExternalApiClient {

    @POST
    @Path("/ConsultasComprobantesElectronicos/porClaveAcceso")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Uni<Response> consultarPorClave(@FormParam("claveAcceso") String claveAcceso);
}

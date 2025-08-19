package com.external.rest.resource;

import com.external.rest.client.ExternalApiClient;
import com.external.rest.client.ExternalApiResponse;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@Path("/consulta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    @Inject
    @RestClient
    ExternalApiClient externalApiClient;

    @ConfigProperty(name = "external-api/mp-rest/url")
    String externalApiUrl;

    private static final Logger LOG = Logger.getLogger(ConsultaResource.class);

    @GET
    @Path("/{clave}")
    public Uni<ExternalApiResponse> consultar(@PathParam("clave") String clave) {
        LOG.infof("Consultando endpoint externo: %s/consulta/%s", externalApiUrl, clave);
        return externalApiClient.consultarPorClave(clave);
    }
}

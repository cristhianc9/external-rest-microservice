package com.external.rest.client;

import io.quarkus.test.Mock;
import io.smallrye.mutiny.Uni;

@Mock
public class ExternalApiClientMock implements ExternalApiClient {
    @Override
    public Uni<ExternalApiResponse> consultarPorClave(String clave) {
        ExternalApiResponse mock = new ExternalApiResponse();
        mock.setEstado("OK");
        mock.setMensaje("Mock OK");
        return Uni.createFrom().item(mock);
    }
}

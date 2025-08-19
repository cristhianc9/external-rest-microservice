package com.external.rest.client;

public class ExternalApiResponse {
    private String estado;
    private String mensaje;
    // Agrega más campos según sea necesario

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

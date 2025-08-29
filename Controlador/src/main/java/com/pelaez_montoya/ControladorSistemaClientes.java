package com.pelaez_montoya;

public class ControladorSistemaClientes {
    private FachadaInterface fachada;

    public ControladorSistemaClientes(FachadaInterface fachada) {
        this.fachada = fachada;
    }

    public String enviarInformacionSubSistemas(double id, String nombres, String apellidos, String email_destino, String texto_mensaje) {
        return fachada.enviarInformacionSubSistemas(id, nombres, apellidos, email_destino, texto_mensaje);
    }

    public SubsistemaInfoDto obtenerInformacionSubsistemas() {
        return fachada.informacionEnviadaSubsistemas();
    }
}

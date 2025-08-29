package com.pelaez_montoya;

import com.pelaez_montoya.Fabrica.DocumentoFabrica;

public interface FachadaInterface {
    Documento crearDocumento(DocumentoFabrica.TipoDocumento tipo, String contenido);

    String mostrarDocumento(Documento documento);

    String imprimirDocumento(Documento documento);

    String enviarInformacionSubSistemas( double id, String nombres, String apellidos, String email_destino, String texto_mensaje);

    SubsistemaInfoDto informacionEnviadaSubsistemas();
}

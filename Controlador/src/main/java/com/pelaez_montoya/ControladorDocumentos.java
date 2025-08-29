package com.pelaez_montoya;

import com.pelaez_montoya.Fabrica.DocumentoFabrica;

public class ControladorDocumentos {
    private FachadaInterface fachada;

    public ControladorDocumentos(FachadaInterface fachada) {
        this.fachada = fachada;
    }

    public Documento crearDocumento(String contenido, DocumentoFabrica.TipoDocumento tipo) {
        return fachada.crearDocumento(tipo, contenido);
    }

    public String mostrarDocumento(Documento documento) {
        String textoCompleto = fachada.mostrarDocumento(documento);
        return textoCompleto;
    }

    public String imprimirDocumento(Documento documento) {
        String textoCompleto = fachada.imprimirDocumento(documento);
        return textoCompleto;
    }
}

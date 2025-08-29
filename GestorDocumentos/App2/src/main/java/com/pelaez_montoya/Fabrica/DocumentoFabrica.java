package com.pelaez_montoya.Fabrica;

import com.pelaez_montoya.Documento;
import com.pelaez_montoya.DocumentoHtmlImp;
import com.pelaez_montoya.DocumentoPdfImp;
import com.pelaez_montoya.DocumentoTexto;

public class DocumentoFabrica {
    private static DocumentoFabrica instancia;

    public enum TipoDocumento {
        PDF, HTML, TEXTO_PLANO
    }

    private DocumentoFabrica() { }

    public static DocumentoFabrica getInstancia() {
        if (instancia == null) {
            instancia = new DocumentoFabrica();
        }
        return instancia;
    }

    public Documento crearDocumento(TipoDocumento tipo) {
        switch (tipo) {
            case PDF:
                return new DocumentoPdfImp();
            case HTML:
                return new DocumentoHtmlImp();
            case TEXTO_PLANO:
                return new DocumentoTexto();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado.");
        }
    }
}

package com.pelaez_montoya.Fabrica;

import com.pelaez_montoya.Documento;
import com.pelaez_montoya.DocumentoPdf;

public class DocumentoPdfFactory extends DocumentoFactory{
    @Override
    public Documento crearDocumento() {
        return new DocumentoPdf();
    }
}

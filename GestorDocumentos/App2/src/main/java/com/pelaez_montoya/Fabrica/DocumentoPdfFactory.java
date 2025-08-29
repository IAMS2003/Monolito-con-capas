package com.pelaez_montoya.Fabrica;

import com.pelaez_montoya.Documento;
import com.pelaez_montoya.DocumentoPdf;
import com.pelaez_montoya.ComponentePDF;

public class DocumentoPdfFactory extends DocumentoFactory{
    private static ComponentePDF componentePDF;
    
    @Override
    public Documento crearDocumento() {
        if (componentePDF == null) {
            componentePDF = new ComponentePDF();
        }
        return new DocumentoPdf(componentePDF);
    }
    
    public static ComponentePDF getComponentePDF() {
        if (componentePDF == null) {
            componentePDF = new ComponentePDF();
        }
        return componentePDF;
    }
}

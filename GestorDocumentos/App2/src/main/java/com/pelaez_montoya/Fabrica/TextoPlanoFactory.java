package com.pelaez_montoya.Fabrica;

import com.pelaez_montoya.Documento;
import com.pelaez_montoya.TextoPlano;

public class TextoPlanoFactory extends DocumentoFactory{
    private static TextoPlano textoPlano;
    
    @Override
    public Documento crearDocumento() {
        if (textoPlano == null) {
            textoPlano = new TextoPlano();
        }
        return textoPlano;
    }
    
    public static TextoPlano getTextoPlano() {
        if (textoPlano == null) {
            textoPlano = new TextoPlano();
        }
        return textoPlano;
    }
}

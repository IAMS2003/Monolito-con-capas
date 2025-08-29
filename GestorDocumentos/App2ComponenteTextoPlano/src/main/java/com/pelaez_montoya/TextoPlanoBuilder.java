package com.pelaez_montoya;

public class TextoPlanoBuilder implements DocumentoBuilder<TextoPlano> {
    private final TextoPlano documento = new TextoPlano();

    @Override
    public DocumentoBuilder<TextoPlano> contenido(String contenido) {
        documento.setContenido(contenido);
        return this;
    }

    @Override
    public TextoPlano build() {
        return documento;
    }
}



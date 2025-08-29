package com.pelaez_montoya;

public interface DocumentoBuilder<T extends Documento> {
    DocumentoBuilder<T> contenido(String contenido);
    T build();
}



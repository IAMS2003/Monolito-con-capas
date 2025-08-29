package com.pelaez_montoya;

import com.pelaez_montoya.ComponentePDF;

public class DocumentoPdf implements Documento{
    private final ComponentePDF componentePDF;

    public DocumentoPdf(ComponentePDF componentePDF) {
        this.componentePDF = componentePDF;
    }

    @Override
    public void setContenido(String contenido) {
        componentePDF.pdfFijaContenido(contenido);
    }

    @Override
    public void dibujar() {
        componentePDF.pdfPreparaVisualizacion();
        componentePDF.pdfRefresca();
        componentePDF.pdfFinalizarVisualizacion();
    }

    @Override
    public void imprimir() {
        componentePDF.pdfEnviarImpresora();
    }
}

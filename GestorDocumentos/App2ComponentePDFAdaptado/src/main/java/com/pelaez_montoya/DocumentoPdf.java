package com.pelaez_montoya;

public class DocumentoPdf implements Documento{
    private ComponentePDF componentePDF = new ComponentePDF();

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

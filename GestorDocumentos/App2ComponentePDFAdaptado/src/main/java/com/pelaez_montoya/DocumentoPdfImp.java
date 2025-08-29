package com.pelaez_montoya;

public class DocumentoPdfImp implements Documento{
    ComponentePDF  herramientaPdf = new ComponentePDF();

    @Override
    public void setContenido(String contenido) {
        herramientaPdf.pdfFijaContenido(contenido);
    }

    @Override
    public String dibujar() {

        String textoInicial = herramientaPdf.pdfPreparaVisualizacion();
        String contenidoArchivo = herramientaPdf.pdfRefresca();
        String textoFinal = herramientaPdf.pdfFinalizarVisualizacion();
        String textoCompleto = textoInicial + "\n" + contenidoArchivo + "\n" + textoFinal;
        return textoCompleto;
    }

    @Override
    public String imprimir() {
        String textoCompleto = herramientaPdf.pdfEnviarImpresora();
        return textoCompleto;
    }
}

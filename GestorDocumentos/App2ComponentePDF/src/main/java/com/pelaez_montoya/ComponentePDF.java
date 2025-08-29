package com.pelaez_montoya;

public class ComponentePDF {
    String contenido;

    public void pdfFijaContenido(String contenido){
        this.contenido=contenido;
    }

    public String pdfPreparaVisualizacion(){
        System.out.println("Visualiza PDF: Inicio ");
        return "Visualiza PDF: Inicio ";
    }

    public String pdfRefresca(){
        System.out.println("Visualiza contenido PDF: " + contenido);
        return "Visualiza contenido PDF: " + contenido;
    }

    public String pdfFinalizarVisualizacion(){
        System.out.println("Visualiza PDF: Fin ");
        return "Visualiza PDF: Fin ";
    }

    public String pdfEnviarImpresora(){
        System.out.println("Impresión contenido PDF: " + contenido);
        return "Impresión contenido PDF: " + contenido;
    }
}

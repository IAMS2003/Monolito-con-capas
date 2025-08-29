package com.pelaez_montoya;

public class ClaseB {

    private String destino;
    private String remitente;
    private String mensaje;

    private ClaseB(Builder builder) {
        this.destino = builder.destino;
        this.mensaje = builder.mensaje;
        this.remitente = "***miempresa***";
    }

    public static class Builder {
        private String destino;
        private String mensaje;

        public Builder destino(String destino) {
            this.destino = destino;
            return this;
        }

        public Builder mensaje(String mensaje) {
            this.mensaje = mensaje;
            return this;
        }

        public ClaseB build() {
            return new ClaseB(this);
        }
    }

    public String enviarMensaje() {
        System.out.println("Informacion enviada al sistema de mensajes.");
        System.out.println("Enviando mensaje: ");
        System.out.println("Destino: " + destino);
        System.out.println("Remitente: " + remitente);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("Mensaje enviado");
        return """
               Informacion enviada al sistema de mensajes.
               Enviando mensaje: 
               Destino: """ + destino + "\n" + "Remitente: " + remitente + "\n" + "Mensaje: " + mensaje + "\n" + "Mensaje enviado"; 
    }

    public String mensajeEnviado() {
        return "\nDestino: " + destino + "\nRemitente: " + remitente + "\nMensaje: " + mensaje;
    }

    public void mensajeEnviadoFinal() {
        System.out.println("\nDestino: " + destino + "\nRemitente: " + remitente + "\nMensaje: " + mensaje);
    }

    public String getDestino() {
        return destino;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getMensaje() {
        return mensaje;
    }
}

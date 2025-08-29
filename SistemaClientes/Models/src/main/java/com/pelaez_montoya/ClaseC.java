package com.pelaez_montoya;

public class ClaseC {

    private String texto;

    private ClaseC(Builder builder) {
        this.texto = builder.texto;
    }

    public static class Builder {
        private String texto;

        public Builder texto(String texto) {
            this.texto = texto;
            return this;
        }

        public ClaseC build() {
            return new ClaseC(this);
        }
    }

    public String procesarInformacion() {
        System.out.println("Informacion procesada para configuracion");
        System.out.println("Correo electronico y demas configuraciones creadas: " + texto.toUpperCase());
        return """
               Informacion procesada para configuracion
               Correo electronico y demas configuraciones creadas: """ + texto.toUpperCase();
    }

    public String getTexto() {
        return texto.toUpperCase();
    }
}

package com.pelaez_montoya;

import com.pelaez_montoya.Fabrica.DocumentoFabrica;
import com.pelaez_montoya.Fabrica.DocumentoBuilder;

public class Fachada implements FachadaInterface {

    @Override
    public Documento crearDocumento(DocumentoFabrica.TipoDocumento tipo, String contenido) {
        return new DocumentoBuilder()
                .conTipo(tipo)
                .conContenido(contenido)
                .build();
    }

    @Override
    public String mostrarDocumento(Documento documento) {
        String textoCompleto = documento.dibujar();
        return textoCompleto;
    }

    @Override
    public String imprimirDocumento(Documento documento) {
        String textoCompleto = documento.imprimir();
        return textoCompleto;
    }

    @Override
    public String enviarInformacionSubSistemas(double id, String nombres, String apellidos, String email_destino, String texto_mensaje) {

        ClaseA objetoA = ClaseAFabrica.getInstance().crearClaseA(id, nombres, apellidos);
        objetoA.cargarInformacionTerceros();

        ClaseB objetoB = ClaseBFabrica.getInstance().crearClaseB(email_destino, texto_mensaje);
        objetoB.mensajeEnviadoFinal();

        ClaseC objetoC = ClaseCFabrica.getInstance().crearClaseC(id + ";" + "nombres" + ";" + "apellidos "+ "\n");

        ClaseADao.getInstance().insertar(objetoA);
        ClaseBDao.getInstance().insertar(objetoB);
        ClaseCDao.getInstance().insertar(objetoC);
        objetoC.procesarInformacion();

        return "Información enviada a subsistemas exitosamente.";
    }

    @Override
    public SubsistemaInfoDto informacionEnviadaSubsistemas() {
        List<ClaseA> listaA = ClaseADao.getInstance().obtenerTodos();
        List<ClaseB> listaB = ClaseBDao.getInstance().obtenerTodos();
        List<ClaseC> listaC = ClaseCDao.getInstance().obtenerTodos();

        return new SubsistemaInfoDto(listaA, listaB, listaC);
    }
}

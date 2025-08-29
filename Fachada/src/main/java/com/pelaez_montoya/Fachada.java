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

        FabricaSistemaClientes fabrica = new FabricaSistemaClientes();
        ClaseADao claseADAO = fabrica.getClaseADAO();
        ClaseBDao claseBDao = fabrica.getClaseBDAO();
        ClaseCDao claseCDao = fabrica.getClaseCDAO();

        ClaseA objetoA = new ClaseA();
        objetoA.setId(id);
        objetoA.setNombres(nombres);
        objetoA.setApellidos(apellidos);
        objetoA.cargarInformacionTerceros();

        ClaseB objetoB = new ClaseB(email_destino, texto_mensaje);
        objetoB.mensajeEnviadoFinal();

        ClaseC objetoC = new ClaseC(id + ";" + "nombres" + ";" + "apellidos "+ "\n");

        claseADAO.insertar(objetoA);
        claseBDao.insertar(objetoB);
        claseCDao.insertar(objetoC);
        objetoC.procesarInformacion();

        return "Información enviada a subsistemas exitosamente.";
    }

    @Override
    public SubsistemaInfoDto informacionEnviadaSubsistemas() {

        FabricaSistemaClientes fabrica = new FabricaSistemaClientes();
        ClaseADao claseADAO = fabrica.getClaseADAO();
        ClaseBDao claseBDao = fabrica.getClaseBDAO();
        ClaseCDao claseCDao = fabrica.getClaseCDAO();

        List<ClaseA> listaA = claseADAO.obtenerTodos();
        List<ClaseB> listaB = claseBDao.obtenerTodos();
        List<ClaseC> listaC = claseCDao.obtenerTodos();

        return new SubsistemaInfoDto(listaA, listaB, listaC);
    }
}

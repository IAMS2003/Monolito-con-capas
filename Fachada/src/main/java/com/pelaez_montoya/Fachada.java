package com.pelaez_montoya;

import com.pelaez_montoya.DAO.ClaseADao;
import com.pelaez_montoya.DAO.ClaseBDao;
import com.pelaez_montoya.DAO.ClaseCDao;
import com.pelaez_montoya.Fabrica.DocumentoFabrica;
import com.pelaez_montoya.Fabrica.DocumentoBuilder;

import java.sql.SQLException;
import java.util.List;

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

        ClaseC objetoC = ClaseCFabrica.getInstance().crearClaseC(id + ";" + nombres + ";" + apellidos + "\n");

        try {
            ClaseADao.getInstance().insertar(objetoA);
            ClaseBDao.getInstance().insertar(objetoB);
            ClaseCDao.getInstance().insertar(objetoC);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        objetoC.procesarInformacion();

        return "Información enviada a subsistemas exitosamente.";
    }

    @Override
    public SubsistemaInfoDto informacionEnviadaSubsistemas() {

        List<ClaseA> listaA = null;
        List<ClaseB> listaB = null;
        List<ClaseC> listaC = null;
        try {
            listaA = ClaseADao.getInstance().obtenerTodos();
            listaB = ClaseBDao.getInstance().obtenerTodos();
            listaC = ClaseCDao.getInstance().obtenerTodos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new SubsistemaInfoDto(listaA, listaB, listaC);
    }
}

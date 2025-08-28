package com.pelaez_montoya;

import com.pelaez_montoya.LibroDTO;
import com.pelaez_montoya.LibroService;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LibroController {

    private final LibroService libroService;
    private static LibroController instance;

    private LibroController() throws SQLException, ClassNotFoundException {
        this.libroService = LibroService.getInstance();
    }

    public static LibroController getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new LibroController();
        }
        return instance;
    }

    public void crearLibro(LibroDTO dto) throws SQLException {
        libroService.crearLibro(dto);
    }

    public Optional<LibroDTO> obtenerPorId(Integer id) throws SQLException {
        return libroService.obtenerPorId(id);
    }

    public List<LibroDTO> obtenerTodos() throws SQLException {
        return libroService.obtenerTodos();
    }

    public void actualizarLibro(Integer id, LibroDTO dto) throws SQLException {
        libroService.actualizarLibro(id, dto);
    }

    public void eliminarLibro(Integer id) throws SQLException {
        libroService.eliminarLibro(id);
    }
}
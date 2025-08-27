package com.pelaez_montoya;

import com.pelaez_montoya.DAO.LibroDAO;
import com.pelaez_montoya.LibroFabrica;
import com.pelaez_montoya.Libro;
import com.pelaez_montoya.LibroDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibroService {

    private final LibroDAO libroDAO;
    private final LibroFabrica libroFabrica;

    public LibroService(LibroDAO libroDAO, LibroFabrica libroFabrica) {
        this.libroDAO = libroDAO;
        this.libroFabrica = libroFabrica;
    }

    public LibroDTO crearLibro(LibroDTO dto) throws SQLException {
        validarLibroDTO(dto);

        Libro libroSinId = libroFabrica.crearLibro(
                null,
                dto.getTitulo(),
                dto.getAutor(),
                dto.getEditorial()
        );

        libroDAO.guardar(libroSinId);

        return dto;
    }

    public Optional<LibroDTO> obtenerPorId(Integer idLibro) throws SQLException {
        return libroDAO.findById(idLibro)
                .map(this::toDTO);
    }

    public List<LibroDTO> obtenerTodos() throws SQLException {
        return libroDAO.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public LibroDTO actualizarLibro(Integer idLibro, LibroDTO dto) throws SQLException {
        Optional<Libro> libroExistente = libroDAO.findById(idLibro);
        if (libroExistente.isEmpty()) {
            throw new IllegalArgumentException("Libro con ID " + idLibro + " no encontrado.");
        }

        validarLibroDTO(dto);

        Libro libro = libroFabrica.crearLibro(
                idLibro,
                dto.getTitulo(),
                dto.getAutor(),
                dto.getEditorial()
        );

        boolean actualizado = libroDAO.actualizar(libro);
        if (!actualizado) {
            throw new SQLException("No se pudo actualizar el libro.");
        }

        return dto;
    }

    public void eliminarLibro(Integer idLibro) throws SQLException {
        Optional<Libro> libro = libroDAO.findById(idLibro);
        if (libro.isEmpty()) {
            throw new IllegalArgumentException("Libro con ID " + idLibro + " no encontrado.");
        }

        boolean eliminado = libroDAO.eliminar(idLibro);
        if (!eliminado) {
            throw new SQLException("Error al eliminar el libro.");
        }
    }

    private void validarLibroDTO(LibroDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO del libro no puede ser nulo.");
        }
        if (dto.getTitulo() == null || dto.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio.");
        }
        if (dto.getAutor() == null || dto.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("El autor es obligatorio.");
        }
    }

    private LibroDTO toDTO(Libro libro) {
        return new LibroDTO(
                libro.getTitulo(),
                libro.getAutor(),
                libro.getEditorial()
        );
    }
}
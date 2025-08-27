package com.pelaez_montoya;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void crearCliente(ClienteDTO dto) throws SQLException {
        clienteService.crearCliente(dto);
    }

    public Optional<ClienteDTO> obtenerPorDocumento(Integer documento) throws SQLException {
        return clienteService.obtenerPorDocumento(documento);
    }

    public List<ClienteDTO> obtenerTodos() throws SQLException {
        return clienteService.obtenerTodos();
    }

    public void actualizarCliente(Integer documento, ClienteDTO dto) throws SQLException {
        clienteService.actualizarCliente(documento, dto);
    }

    public void eliminarCliente(Integer documento) throws SQLException {
        clienteService.eliminarCliente(documento);
    }
}
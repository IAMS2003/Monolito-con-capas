package com.pelaez_montoya;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClienteController {

    private final ClienteService clienteService;
    private static ClienteController instance;

    private ClienteController() throws SQLException, ClassNotFoundException {
        this.clienteService = ClienteService.getInstance();
    }

    public static ClienteController getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new ClienteController();
        }
        return instance;
    }

    public void crearCliente(ClienteDTO dto) throws SQLException {
        System.out.println(dto.getDocumentoIdentidad());
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
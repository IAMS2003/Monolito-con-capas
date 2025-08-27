package com.pelaez_montoya;

import com.pelaez_montoya.DAO.ClienteDAO;
import com.pelaez_montoya.ClienteFabrica;
import com.pelaez_montoya.Cliente;
import com.pelaez_montoya.ClienteDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClienteService {

    private final ClienteDAO clienteDAO;
    private final ClienteFabrica clienteFabrica;

    public ClienteService(ClienteDAO clienteDAO, ClienteFabrica clienteFabrica) {
        this.clienteDAO = clienteDAO;
        this.clienteFabrica = clienteFabrica;
    }

    public ClienteDTO crearCliente(ClienteDTO dto) throws SQLException {
        validarClienteDTO(dto);

        Cliente cliente = clienteFabrica.crearCliente(
                dto.getDocumentoIdentidad(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getEdad(),
                dto.getCorreo(),
                dto.getTelefono()
        );

        clienteDAO.guardar(cliente);
        return dto;
    }

    public Optional<ClienteDTO> obtenerPorDocumento(Integer documento) throws SQLException {
        return clienteDAO.findByDocumento(documento)
                .map(this::toDTO);
    }

    public List<ClienteDTO> obtenerTodos() throws SQLException {
        return clienteDAO.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO actualizarCliente(Integer documento, ClienteDTO dto) throws SQLException {
        if (!dto.getDocumentoIdentidad().equals(documento)) {
            throw new IllegalArgumentException("El documento del DTO no coincide con el proporcionado.");
        }

        validarClienteDTO(dto);

        Optional<Cliente> clienteExistente = clienteDAO.findByDocumento(documento);
        if (clienteExistente.isEmpty()) {
            throw new IllegalArgumentException("Cliente con documento " + documento + " no encontrado.");
        }

        Cliente cliente = clienteFabrica.crearCliente(
                dto.getDocumentoIdentidad(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getEdad(),
                dto.getCorreo(),
                dto.getTelefono()
        );

        boolean actualizado = clienteDAO.actualizar(cliente);
        if (!actualizado) {
            throw new SQLException("No se pudo actualizar el cliente en la base de datos.");
        }

        return dto;
    }

    public void eliminarCliente(Integer documento) throws SQLException {
        Optional<Cliente> cliente = clienteDAO.findByDocumento(documento);
        if (cliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente con documento " + documento + " no encontrado.");
        }

        boolean eliminado = clienteDAO.eliminar(documento);
        if (!eliminado) {
            throw new SQLException("Error al eliminar el cliente de la base de datos.");
        }
    }

    private void validarClienteDTO(ClienteDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO no puede ser nulo.");
        }
        if (dto.getDocumentoIdentidad() == null || dto.getDocumentoIdentidad() <= 0) {
            throw new IllegalArgumentException("Documento de identidad debe ser un número positivo.");
        }
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre es obligatorio.");
        }
        if (dto.getCorreo() == null || !dto.getCorreo().contains("@")) {
            throw new IllegalArgumentException("Correo inválido.");
        }
    }

    private ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getDocumentoIdentidad(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEdad(),
                cliente.getCorreo(),
                cliente.getTelefono()
        );
    }
}
package com.pelaez_montoya;

import com.pelaez_montoya.Fabrica.DocumentoFabrica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class VistaGUI extends JFrame {

    // Controladores
    private final ClienteController clienteController;
    private final LibroController libroController;
    private final ReservaLibroController reservaLibroController;
    private final ControladorDocumentos controladorDocumentos;
    private final ControladorSistemaClientes controladorSistemaClientes;

    // Componentes - Clientes
    private JTextField txtDocCliente, txtNombreCliente, txtApellidoCliente, txtEdadCliente, txtCorreoCliente, txtTelefonoCliente;
    private JButton btnCrearCliente, btnBuscarCliente, btnListarClientes, btnActualizarCliente, btnEliminarCliente;
    private JTable tablaClientes;
    private DefaultTableModel modeloClientes;

    // Componentes - Libros
    private JTextField txtISBN, txtTituloLibro, txtAutorLibro, txtEditorialLibro;
    private JButton btnCrearLibro, btnBuscarLibro, btnListarLibros, btnActualizarLibro, btnEliminarLibro;
    private JTable tablaLibros;
    private DefaultTableModel modeloLibros;

    // Componentes - Reservas
    private JTextField txtIdLibroReserva, txtIdClienteReserva, txtDiasReserva;
    private JButton btnCrearReserva, btnBuscarReserva, btnListarReservas, btnActualizarReserva, btnEliminarReserva;
    private JTable tablaReservas;
    private DefaultTableModel modeloReservas;

    // Componentes - Documentos
    private JComboBox<DocumentoFabrica.TipoDocumento> comboTipoDocumento;
    private JTextArea areaContenidoDocumento;
    private JButton btnMostrarDocumento, btnImprimirDocumento;
    private JTextArea areaResultadoDocumento;

    // Componentes - Subsistemas
    private JTextField txtIdSubsistema, txtNombresSubsistema, txtApellidosSubsistema, txtEmailSubsistema, txtMensajeSubsistema;
    private JButton btnEnviarSubsistema, btnListarSubsistema;
    private JTextArea areaResultadoSubsistema;

    public VistaGUI() throws SQLException, ClassNotFoundException {
        this.clienteController = ClienteController.getInstance();
        this.libroController = LibroController.getInstance();
        this.reservaLibroController = ReservaLibroController.getInstance();
        FachadaInterface fachada = new Fachada();
        this.controladorDocumentos = new ControladorDocumentos(fachada);
        this.controladorSistemaClientes = new ControladorSistemaClientes(fachada);

        setTitle("Biblioteca - Sistema de Gestión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Crear pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Clientes", crearPanelClientes());
        tabbedPane.addTab("Libros", crearPanelLibros());
        tabbedPane.addTab("Reservas", crearPanelReservas());
        tabbedPane.addTab("Documentos", crearPanelDocumentos());
        tabbedPane.addTab("Subsistemas", crearPanelSubsistemas());

        add(tabbedPane);
    }

    // ▶️ PANEL CLIENTES
    private JPanel crearPanelClientes() {
        JPanel panel = new JPanel(new BorderLayout());

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        formPanel.add(new JLabel("Documento:"));
        txtDocCliente = new JTextField();
        formPanel.add(txtDocCliente);

        formPanel.add(new JLabel("Nombre:"));
        txtNombreCliente = new JTextField();
        formPanel.add(txtNombreCliente);

        formPanel.add(new JLabel("Apellido:"));
        txtApellidoCliente = new JTextField();
        formPanel.add(txtApellidoCliente);

        formPanel.add(new JLabel("Edad:"));
        txtEdadCliente = new JTextField();
        formPanel.add(txtEdadCliente);

        formPanel.add(new JLabel("Correo:"));
        txtCorreoCliente = new JTextField();
        formPanel.add(txtCorreoCliente);

        formPanel.add(new JLabel("Teléfono:"));
        txtTelefonoCliente = new JTextField();
        formPanel.add(txtTelefonoCliente);

        panel.add(formPanel, BorderLayout.NORTH);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnCrearCliente = new JButton("Crear");
        btnBuscarCliente = new JButton("Buscar");
        btnListarClientes = new JButton("Listar");
        btnActualizarCliente = new JButton("Actualizar");
        btnEliminarCliente = new JButton("Eliminar");

        buttonPanel.add(btnCrearCliente);
        buttonPanel.add(btnBuscarCliente);
        buttonPanel.add(btnListarClientes);
        buttonPanel.add(btnActualizarCliente);
        buttonPanel.add(btnEliminarCliente);

        panel.add(buttonPanel, BorderLayout.CENTER);

        // Tabla
        modeloClientes = new DefaultTableModel(new Object[]{"Documento", "Nombre", "Apellido", "Edad", "Correo", "Teléfono"}, 0);
        tablaClientes = new JTable(modeloClientes);
        JScrollPane scrollClientes = new JScrollPane(tablaClientes);
        panel.add(scrollClientes, BorderLayout.SOUTH);

        // Acciones
        btnCrearCliente.addActionListener(e -> crearCliente());
        btnBuscarCliente.addActionListener(e -> buscarCliente());
        btnListarClientes.addActionListener(e -> listarClientes());
        btnActualizarCliente.addActionListener(e -> actualizarCliente());
        btnEliminarCliente.addActionListener(e -> eliminarCliente());

        return panel;
    }

    private void crearCliente() {
        try {
            Integer doc = Integer.parseInt(txtDocCliente.getText().trim());
            ClienteDTO dto = new ClienteDTO(
                    doc,
                    txtNombreCliente.getText(),
                    txtApellidoCliente.getText(),
                    Integer.parseInt(txtEdadCliente.getText()),
                    txtCorreoCliente.getText(),
                    txtTelefonoCliente.getText()
            );
            clienteController.crearCliente(dto);
            JOptionPane.showMessageDialog(this, "Cliente creado exitosamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarCliente() {
        try {
            Integer doc = Integer.parseInt(txtDocCliente.getText().trim());
            clienteController.obtenerPorDocumento(doc).ifPresentOrElse(
                    cliente -> {
                        limpiarTabla(modeloClientes);
                        modeloClientes.addRow(new Object[]{
                                cliente.getDocumentoIdentidad(),
                                cliente.getNombre(),
                                cliente.getApellido(),
                                cliente.getEdad(),
                                cliente.getCorreo(),
                                cliente.getTelefono()
                        });
                    },
                    () -> JOptionPane.showMessageDialog(this, "Cliente no encontrado.")
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarClientes() {
        try {
            limpiarTabla(modeloClientes);
            List<ClienteDTO> clientes = clienteController.obtenerTodos();
            for (ClienteDTO c : clientes) {
                modeloClientes.addRow(new Object[]{
                        c.getDocumentoIdentidad(),
                        c.getNombre(),
                        c.getApellido(),
                        c.getEdad(),
                        c.getCorreo(),
                        c.getTelefono()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al listar clientes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCliente() {
        try {
            Integer doc = Integer.parseInt(txtDocCliente.getText().trim());
            ClienteDTO dto = new ClienteDTO(
                    doc,
                    txtNombreCliente.getText(),
                    txtApellidoCliente.getText(),
                    Integer.parseInt(txtEdadCliente.getText()),
                    txtCorreoCliente.getText(),
                    txtTelefonoCliente.getText()
            );
            clienteController.actualizarCliente(doc, dto);
            JOptionPane.showMessageDialog(this, "Cliente actualizado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCliente() {
        try {
            Integer doc = Integer.parseInt(txtDocCliente.getText().trim());
            clienteController.eliminarCliente(doc);
            JOptionPane.showMessageDialog(this, "Cliente eliminado.");
            listarClientes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ▶️ PANEL LIBROS
    private JPanel crearPanelLibros() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos del Libro"));

        formPanel.add(new JLabel("ISBN:"));
        txtISBN = new JTextField();
        formPanel.add(txtISBN);

        formPanel.add(new JLabel("Título:"));
        txtTituloLibro = new JTextField();
        formPanel.add(txtTituloLibro);

        formPanel.add(new JLabel("Autor:"));
        txtAutorLibro = new JTextField();
        formPanel.add(txtAutorLibro);

        formPanel.add(new JLabel("Editorial:"));
        txtEditorialLibro = new JTextField();
        formPanel.add(txtEditorialLibro);

        panel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnCrearLibro = new JButton("Crear");
        btnBuscarLibro = new JButton("Buscar");
        btnListarLibros = new JButton("Listar");
        btnActualizarLibro = new JButton("Actualizar");
        btnEliminarLibro = new JButton("Eliminar");

        buttonPanel.add(btnCrearLibro);
        buttonPanel.add(btnBuscarLibro);
        buttonPanel.add(btnListarLibros);
        buttonPanel.add(btnActualizarLibro);
        buttonPanel.add(btnEliminarLibro);

        panel.add(buttonPanel, BorderLayout.CENTER);

        modeloLibros = new DefaultTableModel(new Object[]{"ISBN", "Título", "Autor", "Editorial"}, 0);
        tablaLibros = new JTable(modeloLibros);
        panel.add(new JScrollPane(tablaLibros), BorderLayout.SOUTH);

        // Acciones
        btnCrearLibro.addActionListener(e -> crearLibro());
        btnBuscarLibro.addActionListener(e -> buscarLibro());
        btnListarLibros.addActionListener(e -> listarLibros());
        btnActualizarLibro.addActionListener(e -> actualizarLibro());
        btnEliminarLibro.addActionListener(e -> eliminarLibro());

        return panel;
    }

    private void crearLibro() {
        try {
            LibroDTO dto = new LibroDTO(
                    Integer.parseInt(txtISBN.getText().trim()),
                    txtTituloLibro.getText(),
                    txtAutorLibro.getText(),
                    txtEditorialLibro.getText()
            );
            libroController.crearLibro(dto);
            JOptionPane.showMessageDialog(this, "Libro creado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarLibro() {
        try {
            libroController.obtenerPorId(Integer.parseInt(txtISBN.getText().trim())).ifPresentOrElse(
                    libro -> {
                        limpiarTabla(modeloLibros);
                        modeloLibros.addRow(new Object[]{libro.getISBN(), libro.getISBN(), libro.getTitulo(), libro.getAutor(), libro.getEditorial()});
                    },
                    () -> JOptionPane.showMessageDialog(this, "Libro no encontrado.")
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarLibros() {
        try {
            limpiarTabla(modeloLibros);
            libroController.obtenerTodos().forEach(l -> modeloLibros.addRow(new Object[]{l.getISBN(), l.getTitulo(), l.getAutor(), l.getEditorial()}));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al listar libros.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarLibro() {
        try {
            LibroDTO dto = new LibroDTO(Integer.parseInt(txtISBN.getText().trim()), txtTituloLibro.getText(), txtAutorLibro.getText(), txtEditorialLibro.getText());
            libroController.actualizarLibro(Integer.parseInt(txtISBN.getText().trim()), dto);
            JOptionPane.showMessageDialog(this, "Libro actualizado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarLibro() {
        try {
            libroController.eliminarLibro(Integer.parseInt(txtISBN.getText().trim()));
            JOptionPane.showMessageDialog(this, "Libro eliminado.");
            listarLibros();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ▶️ PANEL RESERVAS
    private JPanel crearPanelReservas() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos de la Reserva"));

        formPanel.add(new JLabel("ISBN Libro:"));
        txtIdLibroReserva = new JTextField();
        formPanel.add(txtIdLibroReserva);

        formPanel.add(new JLabel("ID Cliente:"));
        txtIdClienteReserva = new JTextField();
        formPanel.add(txtIdClienteReserva);

        formPanel.add(new JLabel("Días de préstamo:"));
        txtDiasReserva = new JTextField("14");
        formPanel.add(txtDiasReserva);

        panel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnCrearReserva = new JButton("Crear");
        btnBuscarReserva = new JButton("Buscar");
        btnListarReservas = new JButton("Listar");
        btnActualizarReserva = new JButton("Actualizar");
        btnEliminarReserva = new JButton("Eliminar");

        buttonPanel.add(btnCrearReserva);
        buttonPanel.add(btnBuscarReserva);
        buttonPanel.add(btnListarReservas);
        buttonPanel.add(btnActualizarReserva);
        buttonPanel.add(btnEliminarReserva);

        panel.add(buttonPanel, BorderLayout.CENTER);

        modeloReservas = new DefaultTableModel(new Object[]{"ID Reserva", "ISBN Libro", "Cliente ID", "Fecha Reserva", "Fecha Devolución"}, 0);
        tablaReservas = new JTable(modeloReservas);
        panel.add(new JScrollPane(tablaReservas), BorderLayout.SOUTH);

        // Acciones
        btnCrearReserva.addActionListener(e -> crearReserva());
        btnBuscarReserva.addActionListener(e -> buscarReserva());
        btnListarReservas.addActionListener(e -> listarReservas());
        btnActualizarReserva.addActionListener(e -> actualizarReserva());
        btnEliminarReserva.addActionListener(e -> eliminarReserva());

        return panel;
    }

    private void crearReserva() {
        try {
            Integer idLibro = Integer.parseInt(txtIdLibroReserva.getText());
            Integer idCliente = Integer.parseInt(txtIdClienteReserva.getText());
            int dias = Integer.parseInt(txtDiasReserva.getText());

            Date fechaReserva = new Date();
            Date fechaDevolucion = new Date(fechaReserva.getTime() + dias * 24L * 60 * 60 * 1000);

            ReservaLibroDTO dto = new ReservaLibroDTO(null, idLibro, idCliente, fechaReserva, fechaDevolucion);
            reservaLibroController.crearReserva(dto);
            JOptionPane.showMessageDialog(this, "Reserva creada.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarReserva() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID de la reserva:"));
            reservaLibroController.obtenerPorId(id).ifPresentOrElse(
                    r -> {
                        limpiarTabla(modeloReservas);
                        modeloReservas.addRow(new Object[]{
                                r.getIdReserva(),
                                r.getIdLibro(),
                                r.getIdCliente(),
                                r.getFechaReserva(),
                                r.getFechaDevolucion()
                        });
                    },
                    () -> JOptionPane.showMessageDialog(this, "Reserva no encontrada.")
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarReservas() {
        try {
            limpiarTabla(modeloReservas);
            reservaLibroController.obtenerTodas().forEach(r -> modeloReservas.addRow(new Object[]{
                    r.getIdReserva(),
                    r.getIdLibro(),
                    r.getIdCliente(),
                    r.getFechaReserva(),
                    r.getFechaDevolucion()
            }));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al listar reservas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarReserva() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID de la reserva a actualizar:"));
            Integer idLibro = Integer.parseInt(txtIdLibroReserva.getText());
            Integer idCliente = Integer.parseInt(txtIdClienteReserva.getText());
            int dias = Integer.parseInt(txtDiasReserva.getText());

            Date fechaReserva = new Date();
            Date fechaDevolucion = new Date(fechaReserva.getTime() + dias * 24L * 60 * 60 * 1000);

            ReservaLibroDTO dto = new ReservaLibroDTO(null, idLibro, idCliente, fechaReserva, fechaDevolucion);
            reservaLibroController.actualizarReserva(id, dto);
            JOptionPane.showMessageDialog(this, "Reserva actualizada.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarReserva() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID de la reserva a eliminar:"));
            reservaLibroController.eliminarReserva(id);
            JOptionPane.showMessageDialog(this, "Reserva eliminada.");
            listarReservas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ▶️ Método auxiliar
    private void limpiarTabla(DefaultTableModel modelo) {
        modelo.setRowCount(0);
    }

    // ▶️ PANEL DOCUMENTOS
    private JPanel crearPanelDocumentos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new BorderLayout(5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Crear Documento"));

        JPanel topForm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topForm.add(new JLabel("Tipo de Documento:"));
        comboTipoDocumento = new JComboBox<>(DocumentoFabrica.TipoDocumento.values());
        topForm.add(comboTipoDocumento);
        formPanel.add(topForm, BorderLayout.NORTH);

        areaContenidoDocumento = new JTextArea(5, 40);
        formPanel.add(new JScrollPane(areaContenidoDocumento), BorderLayout.CENTER);
        panel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnMostrarDocumento = new JButton("Mostrar");
        btnImprimirDocumento = new JButton("Imprimir");
        buttonPanel.add(btnMostrarDocumento);
        buttonPanel.add(btnImprimirDocumento);
        panel.add(buttonPanel, BorderLayout.CENTER);

        areaResultadoDocumento = new JTextArea(15, 60);
        areaResultadoDocumento.setEditable(false);
        areaResultadoDocumento.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaResultadoDocumento);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultado"));
        panel.add(scrollPane, BorderLayout.SOUTH);

        btnMostrarDocumento.addActionListener(e -> procesarDocumento(true));
        btnImprimirDocumento.addActionListener(e -> procesarDocumento(false));

        return panel;
    }

    private void procesarDocumento(boolean esMostrar) {
        try {
            String contenido = areaContenidoDocumento.getText();
            DocumentoFabrica.TipoDocumento tipo = (DocumentoFabrica.TipoDocumento) comboTipoDocumento.getSelectedItem();

            if (contenido.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El contenido no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Documento doc = controladorDocumentos.crearDocumento(contenido, tipo);
            String resultado = esMostrar ? controladorDocumentos.mostrarDocumento(doc) : controladorDocumentos.imprimirDocumento(doc);
            areaResultadoDocumento.setText(resultado);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // ▶️ PANEL SUBSISTEMAS
    private JPanel crearPanelSubsistemas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Enviar Información a Subsistemas"));
        formPanel.add(new JLabel("ID (numérico):"));
        txtIdSubsistema = new JTextField();
        formPanel.add(txtIdSubsistema);
        formPanel.add(new JLabel("Nombres:"));
        txtNombresSubsistema = new JTextField();
        formPanel.add(txtNombresSubsistema);
        formPanel.add(new JLabel("Apellidos:"));
        txtApellidosSubsistema = new JTextField();
        formPanel.add(txtApellidosSubsistema);
        formPanel.add(new JLabel("Email Destino:"));
        txtEmailSubsistema = new JTextField();
        formPanel.add(txtEmailSubsistema);
        formPanel.add(new JLabel("Mensaje:"));
        txtMensajeSubsistema = new JTextField();
        formPanel.add(txtMensajeSubsistema);

        JPanel topButtonPanel = new JPanel(new FlowLayout());
        btnEnviarSubsistema = new JButton("Enviar a Subsistemas");
        topButtonPanel.add(btnEnviarSubsistema);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(topButtonPanel, BorderLayout.CENTER);
        panel.add(centerPanel, BorderLayout.NORTH);

        JPanel resultPanel = new JPanel(new BorderLayout());
        btnListarSubsistema = new JButton("Cargar Información de Subsistemas");
        resultPanel.add(btnListarSubsistema, BorderLayout.NORTH);
        areaResultadoSubsistema = new JTextArea(15, 60);
        areaResultadoSubsistema.setEditable(false);
        areaResultadoSubsistema.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultPanel.add(new JScrollPane(areaResultadoSubsistema), BorderLayout.CENTER);
        panel.add(resultPanel, BorderLayout.CENTER);

        btnEnviarSubsistema.addActionListener(e -> enviarASubsistemas());
        btnListarSubsistema.addActionListener(e -> listarDeSubsistemas());

        return panel;
    }

    private void enviarASubsistemas() {
        try {
            double id = Double.parseDouble(txtIdSubsistema.getText());
            String nombres = txtNombresSubsistema.getText();
            String apellidos = txtApellidosSubsistema.getText();
            String email = txtEmailSubsistema.getText();
            String mensaje = txtMensajeSubsistema.getText();

            if (nombres.trim().isEmpty() || apellidos.trim().isEmpty() || email.trim().isEmpty() || mensaje.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String resultado = controladorSistemaClientes.enviarInformacionSubSistemas(id, nombres, apellidos, email, mensaje);
            JOptionPane.showMessageDialog(this, resultado);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void listarDeSubsistemas() {
        try {
            SubsistemaInfoDto info = controladorSistemaClientes.obtenerInformacionSubsistemas();
            StringBuilder sb = new StringBuilder();
            sb.append("--- Datos del Subsistema A (Contabilidad) ---\n");
            if (info.getListaA() == null || info.getListaA().isEmpty()) sb.append("No hay datos.\n");
            else info.getListaA().forEach(a -> sb.append("ID: ").append(a.getId()).append(", Nombre: ").append(a.getNombres()).append(" ").append(a.getApellidos()).append("\n"));
            sb.append("\n--- Datos del Subsistema B (Mensajería) ---\n");
            if (info.getListaB() == null || info.getListaB().isEmpty()) sb.append("No hay datos.\n");
            else info.getListaB().forEach(b -> sb.append("Destino: ").append(b.getDestino()).append(", Mensaje: ").append(b.getMensaje()).append("\n"));
            sb.append("\n--- Datos del Subsistema C (Configuración) ---\n");
            if (info.getListaC() == null || info.getListaC().isEmpty()) sb.append("No hay datos.\n");
            else info.getListaC().forEach(c -> sb.append("Configuración: ").append(c.getTexto()).append("\n"));
            areaResultadoSubsistema.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar la información: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
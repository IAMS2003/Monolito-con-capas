package com.pelaez_montoya;

import com.pelaez_montoya.Fabrica.DocumentoFabrica;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VistaConsola {

    private final ClienteController clienteController;
    private final LibroController libroController;
    private final ReservaLibroController reservaLibroController;
    private final ControladorDocumentos controladorDocumentos;
    private final ControladorSistemaClientes controladorSistemaClientes;
    private final Scanner scanner;

    public VistaConsola(Scanner scanner) throws SQLException, ClassNotFoundException {
        this.clienteController = ClienteController.getInstance();
        this.libroController = LibroController.getInstance();
        this.reservaLibroController = ReservaLibroController.getInstance();

        // Instanciamos la fachada y los nuevos controladores
        FachadaInterface fachada = new Fachada();
        this.controladorDocumentos = new ControladorDocumentos(fachada);
        this.controladorSistemaClientes = new ControladorSistemaClientes(fachada);

        this.scanner = scanner;
    }

    public void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n=== BIBLIOTECA - MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Libros");
            System.out.println("3. Gestión de Reservas");
            System.out.println("4. Gestión de Documentos");
            System.out.println("5. Sistema de Clientes (Subsistemas)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcion();

            try {
                switch (opcion) {
                    case 1 -> menuClientes();
                    case 2 -> menuLibros();
                    case 3 -> menuReservas();
                    case 4 -> menuDocumentos();
                    case 5 -> menuSistemaClientes();
                    case 6 -> {
                        System.out.println("¡Hasta luego!");
                        return;
                    }
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.err.println("Ha ocurrido un error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void menuClientes() {
        while (true) {
            System.out.println("\n--- Gestión de Clientes ---");
            System.out.println("1. Crear cliente");
            System.out.println("2. Buscar cliente por documento");
            System.out.println("3. Listar todos los clientes");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcion();

            try {
                switch (opcion) {
                    case 1 -> crearCliente();
                    case 2 -> buscarCliente();
                    case 3 -> listarClientes();
                    case 4 -> actualizarCliente();
                    case 5 -> eliminarCliente();
                    case 6 -> { return; }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void crearCliente() throws SQLException {
        System.out.print("Documento de identidad: ");
        Integer doc = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Edad: ");
        Integer edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        ClienteDTO dto = new ClienteDTO(doc, nombre, apellido, edad, correo, telefono);
        clienteController.crearCliente(dto);
        System.out.println("Cliente creado exitosamente.");
    }

    private void buscarCliente() throws SQLException {
        System.out.print("Documento de identidad: ");
        Integer doc = scanner.nextInt();
        scanner.nextLine();

        clienteController.obtenerPorDocumento(doc)
                .ifPresentOrElse(
                        c -> System.out.println("Cliente encontrado: " + c.getNombre() + " " + c.getApellido()),
                        () -> System.out.println("Cliente no encontrado.")
                );
    }

    private void listarClientes() throws SQLException {
        List<ClienteDTO> clientes = clienteController.obtenerTodos();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("Clientes registrados:");
            clientes.forEach(c -> System.out.println("   • " + c.getDocumentoIdentidad() + c.getNombre() + " " + c.getApellido() + " (" + c.getDocumentoIdentidad() + ")"));
        }
    }

    private void actualizarCliente() throws SQLException {
        System.out.print("Documento de identidad (a actualizar): ");
        Integer doc = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Nueva edad: ");
        Integer edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo correo: ");
        String correo = scanner.nextLine();
        System.out.print("Nuevo teléfono: ");
        String telefono = scanner.nextLine();

        ClienteDTO dto = new ClienteDTO(doc, nombre, apellido, edad, correo, telefono);
        clienteController.actualizarCliente(doc, dto);
        System.out.println("Cliente actualizado.");
    }

    private void eliminarCliente() throws SQLException {
        System.out.print("Documento de identidad: ");
        Integer doc = scanner.nextInt();
        scanner.nextLine();

        clienteController.eliminarCliente(doc);
        System.out.println("Cliente eliminado.");
    }

    private void menuLibros() {
        while (true) {
            System.out.println("\n--- Gestión de Libros ---");
            System.out.println("1. Crear libro");
            System.out.println("2. Buscar libro por ID");
            System.out.println("3. Listar todos los libros");
            System.out.println("4. Actualizar libro");
            System.out.println("5. Eliminar libro");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcion();

            try {
                switch (opcion) {
                    case 1 -> crearLibro();
                    case 2 -> buscarLibro();
                    case 3 -> listarLibros();
                    case 4 -> actualizarLibro();
                    case 5 -> eliminarLibro();
                    case 6 -> { return; }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void crearLibro() throws SQLException {
        System.out.print("ISBN: ");
        Integer ISBN = Integer.valueOf(scanner.nextLine());
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Editorial: ");
        String editorial = scanner.nextLine();

        LibroDTO dto = new LibroDTO(ISBN, titulo, autor, editorial);
        libroController.crearLibro(dto);
        System.out.println("Libro creado exitosamente.");
    }

    private void buscarLibro() throws SQLException {
        System.out.print("ISBN del libro: ");
        Integer ISBN = scanner.nextInt();
        scanner.nextLine();

        libroController.obtenerPorId(ISBN)
                .ifPresentOrElse(
                        l -> System.out.println("Libro encontrado: " + l.getTitulo() + " - " + l.getAutor()),
                        () -> System.out.println("Libro no encontrado.")
                );
    }

    private void listarLibros() throws SQLException {
        List<LibroDTO> libros = libroController.obtenerTodos();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Libros disponibles:");
            libros.forEach(l -> System.out.println("   • ISBN " + l.getISBN() + " Titulo " + l.getTitulo() + " - " + l.getAutor()));
        }
    }

    private void actualizarLibro() throws SQLException {
        System.out.print("ISBN del libro: ");
        Integer ISBN = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo título: ");
        String titulo = scanner.nextLine();
        System.out.print("Nuevo autor: ");
        String autor = scanner.nextLine();
        System.out.print("Nueva editorial: ");
        String editorial = scanner.nextLine();

        LibroDTO dto = new LibroDTO(ISBN, titulo, autor, editorial);
        libroController.actualizarLibro(ISBN, dto);
        System.out.println("Libro actualizado.");
    }

    private void eliminarLibro() throws SQLException {
        System.out.print("ID del libro: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        libroController.eliminarLibro(id);
        System.out.println("Libro eliminado.");
    }

    private void menuReservas() {
        while (true) {
            System.out.println("\n--- Gestión de Reservas ---");
            System.out.println("1. Crear reserva");
            System.out.println("2. Buscar reserva por ID");
            System.out.println("3. Listar todas las reservas");
            System.out.println("4. Actualizar reserva");
            System.out.println("5. Eliminar reserva");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcion();

            try {
                switch (opcion) {
                    case 1 -> crearReserva();
                    case 2 -> buscarReserva();
                    case 3 -> listarReservas();
                    case 4 -> actualizarReserva();
                    case 5 -> eliminarReserva();
                    case 6 -> { return; }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void crearReserva() throws SQLException {
        System.out.print("ISBN del libro: ");
        Integer ISBN = scanner.nextInt();
        System.out.print("Documento del cliente: ");
        Integer idCliente = scanner.nextInt();
        scanner.nextLine();

        Date fechaReserva = new Date();
        System.out.println("Fecha de reserva: " + fechaReserva);

        System.out.print("Duración en días (ej: 14): ");
        int dias = scanner.nextInt();
        Date fechaDevolucion = new Date(fechaReserva.getTime() + dias * 24L * 60 * 60 * 1000);

        ReservaLibroDTO dto = new ReservaLibroDTO(null, ISBN, idCliente, fechaReserva, fechaDevolucion);
        reservaLibroController.crearReserva(dto);
        System.out.println("Reserva creada exitosamente.");
    }

    private void buscarReserva() throws SQLException {
        System.out.print("ID de la reserva: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        reservaLibroController.obtenerPorId(id)
                .ifPresentOrElse(
                        r -> System.out.println("Reserva encontrada: ISBN Libro " + r.getIdLibro() + ", Documento del Cliente " + r.getIdCliente() + " Fecha Devolución " + r.getFechaDevolucion()),
                        () -> System.out.println("Reserva no encontrada.")
                );
    }

    private void listarReservas() throws SQLException {
        List<ReservaLibroDTO> reservas = reservaLibroController.obtenerTodas();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas.");
        } else {
            System.out.println("Reservas actuales:");
            reservas.forEach(r -> System.out.println("   • ID reserva: " + r.getIdReserva() + " | ISBN Libro: " + r.getIdLibro() + " | Documento del Cliente: " + r.getIdCliente() + " | Desde: " + r.getFechaReserva() + " | Devolución: " + r.getFechaDevolucion()));
        }
    }

    private void actualizarReserva() throws SQLException {
        System.out.print("ID de la reserva: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo ISBN del libro: ");
        Integer ISBN = scanner.nextInt();
        System.out.print("Nuevo documento del cliente: ");
        Integer idCliente = scanner.nextInt();
        scanner.nextLine();

        Date fechaReserva = new Date();
        System.out.print("Días de préstamo: ");
        int dias = scanner.nextInt();
        Date fechaDevolucion = new Date(fechaReserva.getTime() + dias * 24L * 60 * 60 * 1000);

        ReservaLibroDTO dto = new ReservaLibroDTO(id, ISBN, idCliente, fechaReserva, fechaDevolucion);
        reservaLibroController.actualizarReserva(id, dto);
        System.out.println("Reserva actualizada.");
    }

    private void eliminarReserva() throws SQLException {
        System.out.print("ID de la reserva: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        reservaLibroController.eliminarReserva(id);
        System.out.println("Reserva eliminada.");
    }

    private int obtenerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();
            return opcion;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    private void menuDocumentos() {
        System.out.println("\n--- Gestión de Documentos ---");
        System.out.print("Contenido del documento: ");
        String contenido = scanner.nextLine();

        System.out.print("Tipo de documento (1: PDF, 2: HTML, 3: TEXTO_PLANO): ");
        int tipoOpcion = obtenerOpcion();
        DocumentoFabrica.TipoDocumento tipo;
        switch (tipoOpcion) {
            case 1:
                tipo = DocumentoFabrica.TipoDocumento.PDF;
                break;
            case 2:
                tipo = DocumentoFabrica.TipoDocumento.HTML;
                break;
            case 3:
                tipo = DocumentoFabrica.TipoDocumento.TEXTO_PLANO;
                break;
            default:
                System.out.println("Tipo no válido. Usando TEXTO_PLANO por defecto.");
                tipo = DocumentoFabrica.TipoDocumento.TEXTO_PLANO;
                break;
        }

        Documento doc = controladorDocumentos.crearDocumento(contenido, tipo);
        System.out.println("Documento creado.");

        System.out.print("¿Qué desea hacer? (1: Mostrar, 2: Imprimir): ");
        int accion = obtenerOpcion();
        if (accion == 1) {
            System.out.println("\n--- Mostrando Documento ---");
            controladorDocumentos.mostrarDocumento(doc);
        } else if (accion == 2) {
            System.out.println("\n--- Imprimiendo Documento ---");
            controladorDocumentos.imprimirDocumento(doc);
        } else {
            System.out.println("Acción no válida.");
        }
    }

    private void menuSistemaClientes() {
        while (true) {
            System.out.println("\n--- Sistema de Clientes (Subsistemas) ---");
            System.out.println("1. Enviar Información a Subsistemas");
            System.out.println("2. Ver Información Enviada a Subsistemas");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcion();

            try {
                switch (opcion) {
                    case 1 -> enviarInformacionSubsistemas();
                    case 2 -> verInformacionSubsistemas();
                    case 3 -> { return; }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void enviarInformacionSubsistemas() {
        System.out.println("\n--- Enviar Información a Subsistemas ---");
        System.out.print("ID (numérico): ");
        double id = scanner.nextDouble();
        scanner.nextLine(); // Consumir newline
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Email de destino: ");
        String email = scanner.nextLine();
        System.out.print("Mensaje: ");
        String mensaje = scanner.nextLine();

        String resultado = controladorSistemaClientes.enviarInformacionSubSistemas(id, nombres, apellidos, email, mensaje);
        System.out.println("\n" + resultado);
    }

    private void verInformacionSubsistemas() {
        System.out.println("\n--- Ver Información Enviada a Subsistemas ---");
        SubsistemaInfoDto info = controladorSistemaClientes.obtenerInformacionSubsistemas();

        System.out.println("\n--- Datos del Subsistema A (Contabilidad) ---");
        if (info.getListaA() == null || info.getListaA().isEmpty()) System.out.println("No hay datos.");
        else info.getListaA().forEach(a -> System.out.println("ID: " + a.getId() + ", Nombre: " + a.getNombres() + " " + a.getApellidos()));

        System.out.println("\n--- Datos del Subsistema B (Mensajería) ---");
        if (info.getListaB() == null || info.getListaB().isEmpty()) System.out.println("No hay datos.");
        else info.getListaB().forEach(b -> System.out.println("Destino: " + b.getDestino() + ", Mensaje: " + b.getMensaje()));

        System.out.println("\n--- Datos del Subsistema C (Configuración) ---");
        if (info.getListaC() == null || info.getListaC().isEmpty()) System.out.println("No hay datos.");
        else info.getListaC().forEach(c -> System.out.println("Configuración: " + c.getTexto()));
    }
}
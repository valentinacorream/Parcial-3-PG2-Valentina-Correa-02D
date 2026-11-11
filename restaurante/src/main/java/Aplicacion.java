import java.util.Scanner;
import java.time.LocalDate;

    public class Aplicacion {

        public static void main(String[] args) {

            Scanner teclado = new Scanner(System.in);

            Restaurante restaurante = new Restaurante(
                    "Sabor & Tradicion",
                    "123456789",
                    "Armenia, Quindio"
            );

            int opcion;

            do {
                System.out.println("\n==================================");
                System.out.println("       SABOR & TRADICION");
                System.out.println("==================================");
                System.out.println("1. Registrar cliente");
                System.out.println("2. Registrar producto");
                System.out.println("3. Mostrar productos");
                System.out.println("4. Registrar pedido");
                System.out.println("5. Entregar pedido");
                System.out.println("6. Cancelar pedido");
                System.out.println("7. Consultar historial de cliente");
                System.out.println("8. Mostrar reportes");
                System.out.println("0. Salir");
                System.out.println("==================================");

                System.out.print("Seleccione una opcion: ");
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {

                    // REGISTRAR CLIENTE
                    case 1: {

                        System.out.println("\n--- REGISTRAR CLIENTE ---");

                        System.out.print("Documento: ");
                        String documento = teclado.nextLine();

                        if (restaurante.clienteExiste(documento)) {
                            System.out.println("El cliente ya esta registrado.");
                        } else {

                            System.out.print("Nombre completo: ");
                            String nombre = teclado.nextLine();

                            System.out.print("Telefono: ");
                            String telefono = teclado.nextLine();

                            System.out.print("Correo: ");
                            String correo = teclado.nextLine();

                            System.out.print("Direccion: ");
                            String direccion = teclado.nextLine();

                            Cliente cliente = new Cliente(
                                    documento, nombre, telefono,
                                    correo, direccion
                            );

                            restaurante.registrarCliente(cliente);

                            System.out.println("Cliente registrado correctamente.");
                        }

                        break;
                    }

                    // REGISTRAR PRODUCTO
                    case 2: {

                        System.out.println("\n--- REGISTRAR PRODUCTO ---");

                        System.out.print("Codigo: ");
                        String codigo = teclado.nextLine();

                        if (restaurante.productoExiste(codigo)) {
                            System.out.println("El producto ya existe.");
                            break;
                        }

                        System.out.print("Nombre: ");
                        String nombre = teclado.nextLine();

                        System.out.print("Descripcion: ");
                        String descripcion = teclado.nextLine();

                        System.out.print("Precio base: ");
                        double precio = Double.parseDouble(
                                teclado.nextLine().replace(",", ".")
                        );

                        System.out.print("Tiempo de preparacion: ");
                        int tiempo = Integer.parseInt(teclado.nextLine());

                        System.out.println("\nSeleccione la categoria:");
                        System.out.println("1. Fuerte");
                        System.out.println("2. Bebida");
                        System.out.println("3. Postre");

                        System.out.print("Opcion: ");
                        int categoria = Integer.parseInt(teclado.nextLine());

                        Producto producto = null;

                        switch (categoria) {

                            case 1: {

                                System.out.print("Peso en gramos: ");
                                double peso = Double.parseDouble(
                                        teclado.nextLine().replace(",", ".")
                                );

                                System.out.println("Tipo de preparacion:");
                                System.out.println("1. ASADO");
                                System.out.println("2. FRITO");
                                System.out.println("3. HORNEADO");
                                System.out.println("4. PLANCHA");

                                System.out.print("Opcion: ");
                                int tipo = Integer.parseInt(teclado.nextLine());

                                TipoPreparacion preparacion;

                                switch (tipo) {
                                    case 1:
                                        preparacion = TipoPreparacion.ASADO;
                                        break;
                                    case 2:
                                        preparacion = TipoPreparacion.FRITO;
                                        break;
                                    case 3:
                                        preparacion = TipoPreparacion.HORNEADO;
                                        break;
                                    case 4:
                                        preparacion = TipoPreparacion.PLANCHA;
                                        break;
                                    default:
                                        System.out.println("Tipo no valido.");
                                        break;
                                }

                                if (tipo < 1 || tipo > 4) {
                                    break;
                                }

                                preparacion = TipoPreparacion.values()[tipo - 1];

                                System.out.print("Control de coccion aprobado (S/N): ");
                                boolean control = teclado.nextLine().equalsIgnoreCase("S");

                                producto = new Fuerte(
                                        codigo, nombre, descripcion, precio,
                                        tiempo, peso, preparacion, control
                                );

                                break;
                            }

                            case 2: {

                                System.out.print("Volumen en mililitros: ");
                                double volumen = Double.parseDouble(
                                        teclado.nextLine().replace(",", ".")
                                );

                                System.out.print("Temperatura: ");
                                double temperatura = Double.parseDouble(
                                        teclado.nextLine().replace(",", ".")
                                );

                                System.out.println("Tipo de bebida:");
                                System.out.println("1. FRIA");
                                System.out.println("2. CALIENTE");

                                System.out.print("Opcion: ");
                                int tipo = Integer.parseInt(teclado.nextLine());

                                if (tipo < 1 || tipo > 2) {
                                    System.out.println("Tipo no valido.");
                                    break;
                                }

                                TipoBebida tipoBebida =
                                        TipoBebida.values()[tipo - 1];

                                System.out.print("Preparacion especial (S/N): ");
                                boolean especial =
                                        teclado.nextLine().equalsIgnoreCase("S");

                                producto = new Bebida(
                                        codigo, nombre, descripcion, precio,
                                        tiempo, volumen, temperatura,
                                        tipoBebida, especial
                                );

                                break;
                            }

                            case 3: {

                                System.out.print("Peso en gramos: ");
                                double peso = Double.parseDouble(
                                        teclado.nextLine().replace(",", ".")
                                );

                                System.out.print("Temperatura de almacenamiento: ");
                                double temperatura = Double.parseDouble(
                                        teclado.nextLine().replace(",", ".")
                                );

                                System.out.print("Requiere refrigeracion (S/N): ");
                                boolean refrigeracion =
                                        teclado.nextLine().equalsIgnoreCase("S");

                                producto = new Postre(
                                        codigo, nombre, descripcion, precio,
                                        tiempo, peso, temperatura, refrigeracion
                                );

                                break;
                            }

                            default:
                                System.out.println("Categoria no valida.");
                                break;
                        }

                        if (producto != null) {

                            restaurante.agregarProducto(producto);

                            System.out.println("Producto registrado.");
                            System.out.println("Precio de venta: $"
                                    + producto.calcularPrecio());

                            System.out.println("Calidad: "
                                    + (producto.verificarCalidad()
                                    ? "Aprobada" : "No aprobada"));
                        }

                        break;
                    }

                    // MOSTRAR PRODUCTOS
                    case 3: {

                        System.out.println("\n--- PRODUCTOS DEL MENU ---");

                        if (restaurante.getListProductos().isEmpty()) {
                            System.out.println("No hay productos registrados.");
                        } else {

                            for (Producto producto : restaurante.getListProductos()) {

                                System.out.println("\nCodigo: " + producto.getCodigo());
                                System.out.println("Nombre: " + producto.getNombre());
                                System.out.println("Descripcion: " + producto.getDescripcion());
                                System.out.println("Precio base: $" + producto.getPrecio());
                                System.out.println("Precio de venta: $"
                                        + producto.calcularPrecio());
                                System.out.println("Tiempo: "
                                        + producto.getTimePreparacion() + " minutos");
                                System.out.println("Calidad: "
                                        + (producto.verificarCalidad()
                                        ? "Aprobada" : "No aprobada"));
                            }
                        }

                        break;
                    }

                    // REGISTRAR PEDIDO
                    case 4: {

                        System.out.println("\n--- REGISTRAR PEDIDO ---");

                        System.out.print("Codigo del pedido: ");
                        String codigo = teclado.nextLine();

                        if (restaurante.pedidoExiste(codigo)) {
                            System.out.println("El codigo del pedido ya existe.");
                            break;
                        }

                        System.out.print("Documento del cliente: ");
                        String documento = teclado.nextLine();

                        Cliente clienteEncontrado = null;

                        for (Cliente cliente : restaurante.getListClientes()) {
                            if (cliente.getDocumento().equalsIgnoreCase(documento)) {
                                clienteEncontrado = cliente;
                                break;
                            }
                        }

                        if (clienteEncontrado == null) {
                            System.out.println("Cliente no registrado.");
                            break;
                        }

                        System.out.println("Tipo de entrega:");
                        System.out.println("1. Restaurante");
                        System.out.println("2. Domicilio");

                        System.out.print("Opcion: ");
                        int opcionEntrega = Integer.parseInt(teclado.nextLine());

                        TipoEntrega tipoEntrega;
                        Domicilio domicilio = null;

                        if (opcionEntrega == 1) {

                            tipoEntrega = TipoEntrega.RESTAURANTE;

                        } else if (opcionEntrega == 2) {

                            tipoEntrega = TipoEntrega.DOMICILIO;

                            System.out.print("Distancia en kilometros: ");
                            double distancia = Double.parseDouble(
                                    teclado.nextLine().replace(",", ".")
                            );

                            System.out.println("Empresa de domicilio:");
                            System.out.println("1. RapidoExpress");
                            System.out.println("2. EntregaSegura");

                            System.out.print("Opcion: ");
                            int empresa = Integer.parseInt(teclado.nextLine());

                            IDomicilio servicio;

                            if (empresa == 1) {
                                servicio = new RapidoExpress();
                            } else if (empresa == 2) {
                                servicio = new EntregaSegura();
                            } else {
                                System.out.println("Empresa no valida.");
                                break;
                            }

                            domicilio = new Domicilio(distancia, true, servicio);

                            if (!domicilio.validarServicio()) {
                                System.out.println("El servicio no acepta esa distancia.");
                                break;
                            }

                        } else {
                            System.out.println("Tipo de entrega no valido.");
                            break;
                        }

                        Pedido pedido = new Pedido(
                                codigo,
                                LocalDate.now(),
                                clienteEncontrado,
                                tipoEntrega,
                                EstadoPedido.PENDIENTE,
                                domicilio,
                                restaurante
                        );

                        boolean continuar = true;

                        while (continuar) {

                            System.out.println("\n--- PRODUCTOS DISPONIBLES ---");

                            for (Producto producto : restaurante.getListProductos()) {
                                System.out.println(
                                        producto.getCodigo() + " - "
                                                + producto.getNombre() + " - $"
                                                + producto.calcularPrecio()
                                );
                            }

                            System.out.print("Codigo del producto: ");
                            String codigoProducto = teclado.nextLine();

                            Producto productoEncontrado = null;

                            for (Producto producto : restaurante.getListProductos()) {
                                if (producto.getCodigo().equalsIgnoreCase(codigoProducto)) {
                                    productoEncontrado = producto;
                                    break;
                                }
                            }

                            if (productoEncontrado == null) {
                                System.out.println("Producto no encontrado.");

                            } else if (!productoEncontrado.verificarCalidad()) {
                                System.out.println("El producto no cumple las condiciones de calidad.");

                            } else {

                                System.out.print("Cantidad: ");
                                int cantidad = Integer.parseInt(teclado.nextLine());

                                if (cantidad <= 0) {
                                    System.out.println("Cantidad no valida.");
                                } else {

                                    double precioFinal = productoEncontrado.calcularPrecio();
                                    double subtotal = precioFinal * cantidad;

                                    DetallePedido detalle = new DetallePedido(
                                            productoEncontrado,
                                            cantidad,
                                            precioFinal,
                                            subtotal
                                    );

                                    pedido.agregarDetalle(detalle);

                                    System.out.println("Producto agregado.");
                                }
                            }

                            System.out.print("¿Desea agregar otro producto? (S/N): ");
                            continuar = teclado.nextLine().equalsIgnoreCase("S");
                        }

                        if (pedido.getDetallePedidos().isEmpty()) {
                            System.out.println("El pedido no tiene productos.");
                            break;
                        }

                        if (pedido.confirmarPedido()) {

                            System.out.println("\nPedido confirmado correctamente.");
                            System.out.println("Codigo: " + pedido.getCodigo());
                            System.out.println("Cliente: " + clienteEncontrado.getNombre());
                            System.out.println("Total: $" + pedido.calcularTotal());
                            System.out.println("Estado: " + pedido.getEstado());

                        } else {
                            System.out.println("No fue posible confirmar el pedido.");
                        }

                        break;
                    }

                    // ENTREGAR PEDIDO
                    case 5: {

                        System.out.println("\n--- ENTREGAR PEDIDO ---");

                        System.out.print("Codigo del pedido: ");
                        String codigo = teclado.nextLine();

                        Pedido pedidoEncontrado = null;

                        for (Cliente cliente : restaurante.getListClientes()) {
                            for (Pedido pedido : cliente.getListPedidos()) {
                                if (pedido.getCodigo().equalsIgnoreCase(codigo)) {
                                    pedidoEncontrado = pedido;
                                    break;
                                }
                            }

                            if (pedidoEncontrado != null) {
                                break;
                            }
                        }

                        if (pedidoEncontrado == null) {
                            System.out.println("Pedido no encontrado.");

                        } else if (pedidoEncontrado.entregarPedido()) {
                            System.out.println("Pedido entregado correctamente.");

                        } else {
                            System.out.println("El pedido no puede entregarse.");
                        }

                        break;
                    }

                    // CANCELAR PEDIDO
                    case 6: {

                        System.out.println("\n--- CANCELAR PEDIDO ---");

                        System.out.print("Codigo del pedido: ");
                        String codigo = teclado.nextLine();

                        Pedido pedidoEncontrado = null;

                        for (Cliente cliente : restaurante.getListClientes()) {
                            for (Pedido pedido : cliente.getListPedidos()) {
                                if (pedido.getCodigo().equalsIgnoreCase(codigo)) {
                                    pedidoEncontrado = pedido;
                                    break;
                                }
                            }

                            if (pedidoEncontrado != null) {
                                break;
                            }
                        }

                        if (pedidoEncontrado == null) {
                            System.out.println("Pedido no encontrado.");

                        } else if (pedidoEncontrado.getEstado() == EstadoPedido.ENTREGADO
                                || pedidoEncontrado.getEstado() == EstadoPedido.CANCELADO) {
                            System.out.println("El pedido no puede cancelarse.");

                        } else {
                            pedidoEncontrado.cancelarPedido();
                            System.out.println("Pedido cancelado correctamente.");
                        }

                        break;
                    }

                    // CONSULTAR HISTORIAL
                    case 7: {

                        System.out.println("\n--- HISTORIAL DEL CLIENTE ---");

                        System.out.print("Documento del cliente: ");
                        String documento = teclado.nextLine();

                        Cliente clienteEncontrado = null;

                        for (Cliente cliente : restaurante.getListClientes()) {
                            if (cliente.getDocumento().equalsIgnoreCase(documento)) {
                                clienteEncontrado = cliente;
                                break;
                            }
                        }

                        if (clienteEncontrado == null) {
                            System.out.println("Cliente no encontrado.");

                        } else if (clienteEncontrado.getListPedidos().isEmpty()) {
                            System.out.println("El cliente no tiene pedidos.");

                        } else {

                            System.out.println("Cliente: " + clienteEncontrado.getNombre());

                            for (Pedido pedido : clienteEncontrado.getListPedidos()) {

                                System.out.println("\nCodigo: " + pedido.getCodigo());
                                System.out.println("Fecha: " + pedido.getFecha());
                                System.out.println("Entrega: " + pedido.getTipoEntrega());
                                System.out.println("Estado: " + pedido.getEstado());
                                System.out.println("Total: $" + pedido.calcularTotal());
                            }
                        }

                        break;
                    }

                    // MOSTRAR REPORTES
                    case 8: {

                        System.out.println("\n--- REPORTES DEL RESTAURANTE ---");

                        System.out.println("Ingresos del restaurante: $"
                                + restaurante.calcularIngresosRestaurante());

                        System.out.println("Producto mas vendido: "
                                + restaurante.obtenerProductoMasVendido());

                        System.out.println("Categoria con mayores ingresos: "
                                + restaurante.obtenerCategoriaMayorIngreso());

                        break;
                    }

                    // SALIR
                    case 0:
                        System.out.println("Gracias por utilizar Sabor & Tradicion.");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }

            } while (opcion != 0);

            teclado.close();
        }
    }


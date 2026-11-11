import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private String nombre;
    private String nit;
    private String direccion;
    private List<Producto> listProductos;
    private List<Cliente> listClientes;

    public Restaurante(String nombre, String nit, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;

        listProductos= new ArrayList<>();
        listClientes= new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getListProductos() {
        return listProductos;
    }

    public void setListProductos(List<Producto> listProductos) {
        this.listProductos = listProductos;
    }

    public List<Cliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    @Override
    public String toString() {
        return "Restaurante: " +
                " nombre: " + nombre +
                ", nit: " + nit +
                ", direccion: " + direccion +
                ", listProductos: " + listProductos +
                ", listClientes: " + listClientes;
    }

    // Agregar cliente
    public void registrarCliente(Cliente cliente) {
        if (cliente != null && !clienteExiste(cliente.getDocumento())) {
            listClientes.add(cliente);
        }
    }

    // Agregar producto
    public void agregarProducto(Producto producto) {
        if (producto != null && !productoExiste(producto.getCodigo())) {
            listProductos.add(producto);
        }
    }

    // Verificar producto
    public boolean productoExiste(String codigo) {
        boolean encontrado = false;
        for (Producto producto : listProductos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    // Verificar cliente
    public boolean clienteExiste(String documento) {
        boolean encontrado = false;
        for (Cliente cliente : listClientes) {
            if (cliente.getDocumento().equalsIgnoreCase(documento)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    // Verificar pedido
    public boolean pedidoExiste(String codigo) {
        boolean encontrado = false;
        for (Cliente cliente : listClientes) {
            for (Pedido pedido : cliente.getListPedidos()) {
                if (pedido.getCodigo().equalsIgnoreCase(codigo)) {
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                break;
            }
        }
        return encontrado;
    }

    // Verificar si el pedido cuenta para los reportes
    private boolean pedidoCuentaParaReporte(Pedido pedido) {
        return pedido != null
                && (pedido.getEstado() == EstadoPedido.CONFIRMADO
                || pedido.getEstado() == EstadoPedido.ENTREGADO);
    }

    // Calcular ingresos del restaurante sin domicilios
    public double calcularIngresosRestaurante() {
        double ingresos = 0;

        for (Cliente cliente : listClientes) {
            for (Pedido pedido : cliente.getListPedidos()) {
                if (pedidoCuentaParaReporte(pedido)) {
                    for (DetallePedido detalle : pedido.getDetallePedidos()) {
                        ingresos += detalle.getSubtotal();
                    }
                }
            }
        }

        return ingresos;
    }

    // Obtener el producto más vendido por unidades
    public String obtenerProductoMasVendido() {
        Producto masVendido = null;
        int mayorCantidad = 0;

        for (Producto producto : listProductos) {
            int cantidadTotal = 0;

            for (Cliente cliente : listClientes) {
                for (Pedido pedido : cliente.getListPedidos()) {
                    if (pedidoCuentaParaReporte(pedido)) {
                        for (DetallePedido detalle : pedido.getDetallePedidos()) {
                            if (detalle.getProducto() != null
                                    && detalle.getProducto().getCodigo()
                                    .equalsIgnoreCase(producto.getCodigo())) {
                                cantidadTotal += detalle.getCantidad();
                            }
                        }
                    }
                }
            }

            if (cantidadTotal > mayorCantidad) {
                mayorCantidad = cantidadTotal;
                masVendido = producto;
            }
        }

        if (masVendido == null) {
            return "No hay ventas registradas";
        }

        return masVendido.getNombre();
    }

    // Obtener la categoría con mayores ingresos
    public String obtenerCategoriaMayorIngreso() {
        double ingresosFuerte = 0;
        double ingresosBebida = 0;
        double ingresosPostre = 0;

        for (Cliente cliente : listClientes) {
            for (Pedido pedido : cliente.getListPedidos()) {
                if (pedidoCuentaParaReporte(pedido)) {
                    for (DetallePedido detalle : pedido.getDetallePedidos()) {
                        if (detalle.getProducto() instanceof Fuerte) {
                            ingresosFuerte += detalle.getSubtotal();
                        } else if (detalle.getProducto() instanceof Bebida) {
                            ingresosBebida += detalle.getSubtotal();
                        } else if (detalle.getProducto() instanceof Postre) {
                            ingresosPostre += detalle.getSubtotal();
                        }
                    }
                }
            }
        }

        if (ingresosFuerte == 0 && ingresosBebida == 0
                && ingresosPostre == 0) {
            return "No hay ventas registradas";
        }

        if (ingresosFuerte >= ingresosBebida
                && ingresosFuerte >= ingresosPostre) {
            return "Fuerte";
        } else if (ingresosBebida >= ingresosFuerte
                && ingresosBebida >= ingresosPostre) {
            return "Bebida";
        } else {
            return "Postre";
        }
    }
}

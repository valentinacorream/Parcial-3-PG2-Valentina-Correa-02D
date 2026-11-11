import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String codigo;
    private LocalDate fecha;
    private Cliente cliente;
    private TipoEntrega tipoEntrega;
    private EstadoPedido estado;
    private List<DetallePedido> detallePedidos;
    private Domicilio domicilio;
    private Restaurante restaurante;

    public Pedido(String codigo, LocalDate fecha, Cliente cliente, TipoEntrega tipoEntrega, EstadoPedido estado, Domicilio domicilio, Restaurante restaurante) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.estado = estado;
        this.domicilio = domicilio;
        this.restaurante= restaurante;

        detallePedidos= new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(TipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public String toString() {
        return "Pedido: " +
                "codigo: " + codigo +
                ", fecha: " + fecha +
                ", cliente: " + cliente +
                ", tipoEntrega: " + tipoEntrega +
                ", estado: " + estado +
                ", detallePedidos: " + detallePedidos +
                ", domicilio: " + domicilio +
                ", restaurante: " + restaurante;
    }

    // Agregar detalle
    public void agregarDetalle(DetallePedido detalle) {
        if (detalle != null) {
            detallePedidos.add(detalle);
        }
    }

    // Calcular total
    public double calcularTotal() {
        double total = 0;
        for (DetallePedido detalle : detallePedidos) {
            total += detalle.getSubtotal();
        }
        if (tipoEntrega == TipoEntrega.DOMICILIO && domicilio != null) {
            total += domicilio.calcularCosto();
        }

        return total;
    }

    // Confirmar pedido
    public boolean confirmarPedido() {
        if (estado != EstadoPedido.PENDIENTE || cliente == null || tipoEntrega == null || detallePedidos == null || detallePedidos.isEmpty() || restaurante == null || restaurante.pedidoExiste(codigo) || !restaurante.clienteExiste(cliente.getDocumento())) {
            return false;
        }
        for (DetallePedido detalle : detallePedidos) {
            if (detalle == null || detalle.getProducto() == null || detalle.getCantidad() <= 0 || !detalle.getProducto().verificarCalidad() || !restaurante.productoExiste(detalle.getProducto().getCodigo())) {
                return false;
            }
        }
        if (tipoEntrega == TipoEntrega.DOMICILIO) {
            if (domicilio == null || !domicilio.validarServicio()) {
                return false;
            }
        }
        estado = EstadoPedido.CONFIRMADO;
        cliente.agregarPedido(this);

        return true;
    }

    // Entregar pedido
    public boolean entregarPedido() {
        if (estado == EstadoPedido.CONFIRMADO) {
            estado = EstadoPedido.ENTREGADO;
            return true;
        }
        return false;
    }

    // Cancelar pedido
    public void cancelarPedido() {

        if (estado == EstadoPedido.PENDIENTE
                || estado == EstadoPedido.CONFIRMADO) {
            estado = EstadoPedido.CANCELADO;
        }
    }
}

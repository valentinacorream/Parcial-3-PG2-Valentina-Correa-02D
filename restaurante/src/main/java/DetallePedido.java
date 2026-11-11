public class DetallePedido {
    private Producto producto;
    private int cantidad;
    private double precioUFinal;
    private double subtotal;

    public DetallePedido(Producto producto, int cantidad, double precioUFinal, double subtotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUFinal = precioUFinal;
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUFinal() {
        return precioUFinal;
    }

    public void setPrecioUFinal(double precioUFinal) {
        this.precioUFinal = precioUFinal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double calcularSubtotal() {
        subtotal = precioUFinal * cantidad;
        return subtotal;
    }
}

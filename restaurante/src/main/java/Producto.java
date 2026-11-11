public abstract class Producto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int timePreparacion;

    public Producto(String codigo, String nombre, String descripcion, double precio, int timePreparacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.timePreparacion = timePreparacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTimePreparacion() {
        return timePreparacion;
    }

    public void setTimePreparacion(int timePreparacion) {
        this.timePreparacion = timePreparacion;
    }

    @Override
    public String toString() {
        return "Producto: " +
                "codigo: " + codigo +
                ", nombre: " + nombre +
                ", descripcion: " + descripcion +
                ", precio: " + precio +
                ", timePreparacion: " + timePreparacion;
    }

    public abstract double calcularPrecio();

    public abstract boolean verificarCalidad();
}

public class Fuerte extends Producto{
    private double peso;
    private TipoPreparacion tipoPreparacion;
    private boolean controlCoccion;

    public Fuerte(String codigo, String nombre, String descripcion, double precio, int timePreparacion, double peso, TipoPreparacion tipoPreparacion, boolean controlCoccion) {
        super(codigo, nombre, descripcion, precio, timePreparacion);
        this.peso = peso;
        this.tipoPreparacion = tipoPreparacion;
        this.controlCoccion = controlCoccion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public TipoPreparacion getTipoPreparacion() {
        return tipoPreparacion;
    }

    public void setTipoPreparacion(TipoPreparacion tipoPreparacion) {
        this.tipoPreparacion = tipoPreparacion;
    }

    public boolean isControlCoccion() {
        return controlCoccion;
    }

    public void setControlCoccion(boolean controlCoccion) {
        this.controlCoccion = controlCoccion;
    }

    @Override
    public String toString() {
        return "Fuerte: " +
                "peso: " + peso +
                ", tipoPreparacion: " + tipoPreparacion +
                ", controlCoccion: " + controlCoccion;
    }

    @Override
    public double calcularPrecio() {
        return getPrecio() + (getPrecio()*0.20);
    }

    @Override
    public boolean verificarCalidad() {
        if (peso>=200 && peso<=900 && getTimePreparacion()>=10 && getTimePreparacion()<=60 && controlCoccion){
            return true;
        }
        else {
            return false;
        }
    }
}

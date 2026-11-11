public class Postre extends Producto{
    private double peso;
    private double temperatura;
    private boolean refrigeracion;

    public Postre(String codigo, String nombre, String descripcion, double precio, int timePreparacion, double peso, double temperatura, boolean refrigeracion) {
        super(codigo, nombre, descripcion, precio, timePreparacion);
        this.peso = peso;
        this.temperatura = temperatura;
        this.refrigeracion = refrigeracion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public boolean isRefrigeracion() {
        return refrigeracion;
    }

    public void setRefrigeracion(boolean refrigeracion) {
        this.refrigeracion = refrigeracion;
    }

    @Override
    public String toString() {
        return "Postre: " +
                "peso: " + peso +
                ", temperatura: " + temperatura +
                ", refrigeracion: " + refrigeracion;
    }

    @Override
    public double calcularPrecio() {
        double precio= getPrecio() + (getPrecio()*0.15);

        if (refrigeracion){
            precio+= getPrecio()*0.05;
        }
        return precio;
    }

    @Override
    public boolean verificarCalidad() {
        if (peso>=80 && peso<=500){
            if (refrigeracion && temperatura>=0 && temperatura<=6){
                return true;
            } else if (!refrigeracion && temperatura>=15 && temperatura<=30) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

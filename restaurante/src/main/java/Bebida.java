public class Bebida extends Producto{
    private double volumen;
    private double temperatura;
    private TipoBebida tipoBebida;
    private boolean prepEspecial;

    public Bebida(String codigo, String nombre, String descripcion, double precio, int timePreparacion, double volumen, double temperatura, TipoBebida tipoBebida, boolean prepEspecial) {
        super(codigo, nombre, descripcion, precio, timePreparacion);
        this.volumen = volumen;
        this.temperatura = temperatura;
        this.tipoBebida = tipoBebida;
        this.prepEspecial = prepEspecial;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public TipoBebida getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(TipoBebida tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    public boolean isPrepEspecial() {
        return prepEspecial;
    }

    public void setPrepEspecial(boolean prepEspecial) {
        this.prepEspecial = prepEspecial;
    }

    @Override
    public String toString() {
        return "Bebida: " +
                "volumen: " + volumen +
                ", temperatura: " + temperatura +
                ", tipoBebida: " + tipoBebida +
                ", prepEspecial: " + prepEspecial;
    }

    @Override
    public double calcularPrecio() {
        double precio= getPrecio() + (getPrecio()*0.10);
        if (prepEspecial){
            precio+= 3000;
        }
        return precio;
    }

    @Override
    public boolean verificarCalidad() {
        if (tipoBebida==TipoBebida.FRIA) {
            if (volumen >= 150 && volumen <= 1500 && temperatura >= 0 && temperatura <= 10) {
                return true;
            } else {
                return false;
            }
        } else if (tipoBebida==TipoBebida.CALIENTE) {
            if (volumen >= 150 && volumen <= 1500 && temperatura>=55 && temperatura<=85){
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}

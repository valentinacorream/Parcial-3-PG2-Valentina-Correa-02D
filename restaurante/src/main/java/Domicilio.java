public class Domicilio {
    private double distancia;
    private boolean aceptacion;
    private IDomicilio servicioDomicilio;

    public Domicilio(double distancia, boolean aceptacion, IDomicilio servicioDomicilio) {
        this.distancia = distancia;
        this.aceptacion = aceptacion;
        this.servicioDomicilio= servicioDomicilio;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public boolean isAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public IDomicilio getServicioDomicilio() {
        return servicioDomicilio;
    }

    public void setServicioDomicilio(IDomicilio servicioDomicilio) {
        this.servicioDomicilio = servicioDomicilio;
    }

    @Override
    public String toString() {
        return "Domicilio: " +
                "distancia: " + distancia +
                ", aceptacion: " + aceptacion +
                ", servicioDomicilio: " + servicioDomicilio;
    }

    //Calcular Costo
    public double calcularCosto() {
        if (servicioDomicilio == null) {
            return 0;
        }
        return servicioDomicilio.calcularCosto(distancia);
    }

    //validar servicio
    public boolean validarServicio() {
        return aceptacion && servicioDomicilio != null && servicioDomicilio.validarDistancia(distancia);
    }
}

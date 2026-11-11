public class EntregaSegura implements IDomicilio{
    private final double TARIFA_BASE = 7000;
    private final double VALOR_KILOMETRO = 1000;
    private final double RECARGO = 3000;
    private final double DISTANCIA_RECARGO = 5;
    private final double DISTANCIA_MAX = 20;

    @Override
    public double calcularCosto(double distancia) {
        double costo = TARIFA_BASE + (VALOR_KILOMETRO * distancia);
        if (distancia > DISTANCIA_RECARGO) {
            costo += RECARGO;
        }
        return costo;
    }

    @Override
    public boolean validarDistancia(double distancia) {
        if (distancia>0 && distancia<=DISTANCIA_MAX){
            return true;
        }else {
            return false;
        }
    }
}

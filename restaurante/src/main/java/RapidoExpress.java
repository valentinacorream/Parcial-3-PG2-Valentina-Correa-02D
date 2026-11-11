public class RapidoExpress implements IDomicilio{
    private final double TARIFA_BASE = 5000;
    private final double VALOR_KILOMETRO = 1500;
    private final double DISTANCIA_MAX = 12;

    @Override
    public double calcularCosto(double distancia) {
        return TARIFA_BASE + (VALOR_KILOMETRO * distancia);
    }

    @Override
    public boolean validarDistancia(double distancia) {
        if (distancia>0 && distancia<=DISTANCIA_MAX) {
            return true;
        }
        else {
            return false;
        }
    }
}

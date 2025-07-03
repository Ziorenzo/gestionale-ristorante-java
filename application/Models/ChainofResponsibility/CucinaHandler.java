package application.Models.ChainofResponsibility;

public class CucinaHandler extends RepartoHandler {
    @Override
    protected boolean deveGestireOrdine(String type) {
        return "Meals".equals(type);
    }

    @Override
    protected int calcolaTempoDiPreparazione(int qty) {
        return 5000 * qty;
    }
}
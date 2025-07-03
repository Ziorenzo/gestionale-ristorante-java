package application.Models.ChainofResponsibility;

public class BevandeHandler extends RepartoHandler {
    @Override
    protected boolean deveGestireOrdine(String type) {
        return "Drinks".equals(type);
    }

    @Override
    protected int calcolaTempoDiPreparazione(int qty) {
        return 5000 * qty;
    }
}
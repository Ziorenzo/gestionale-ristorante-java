package application.Models.ChainofResponsibility;

import java.util.Timer;
import java.util.TimerTask;

import application.Database.Manager.DatabaseManagerHandler;

public abstract class RepartoHandler {
    private RepartoHandler nextHandler;

    public void setNextHandler(RepartoHandler handler) {
        this.nextHandler = handler;
    }

    public RepartoHandler getNextHandler() {
        return nextHandler;
    }

    public void preparaOrdine(int id, int qty, String type) {
        if (deveGestireOrdine(type)) {
            int tempoDiPreparazioneInMillisecondi = calcolaTempoDiPreparazione(qty);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    DatabaseManagerHandler.eseguiAggiornamentoDelDatabase(id);
                }
            }, tempoDiPreparazioneInMillisecondi);
        } else if (getNextHandler() != null) {
            getNextHandler().preparaOrdine(id, qty, type);
        } else {
            System.out.println("No next");
        }
    }

    protected abstract boolean deveGestireOrdine(String type);

    protected abstract int calcolaTempoDiPreparazione(int qty);
}
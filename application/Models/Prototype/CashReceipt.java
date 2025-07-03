package application.Models.Prototype;

import java.sql.Date;

public class CashReceipt extends Ricevuta {

    // Costruttore per la ricevuta con carta di debito
    public CashReceipt(Integer id_ricevuta, String customer_id, Double total, Date date, String em_username,
                           Integer tavolo_id, String tipo_pagamento) {
        super(id_ricevuta, customer_id, total, date, em_username, tipo_pagamento);
        // TODO: Aggiungi campi e metodi specifici per la ricevuta con carta di debito
    }

    // Sovrascrive il metodo clone per restituire un'istanza di DebitCardReceipt
    @Override
    public CashReceipt clone() {
        try {
            return (CashReceipt) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

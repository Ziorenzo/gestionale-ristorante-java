package application.Models.Prototype;

import java.sql.Date;

public abstract class Ricevuta implements Cloneable {

    // Campi specifici per una ricevuta singola
    protected Integer id_ricevuta;
    protected String customer_id;
    protected Double total;
    protected Date date;
    protected String em_username;
    protected String tipo_pagamento;

    // Costruttore per inizializzare i campi della ricevuta
    public Ricevuta(Integer id_ricevuta, String customer_id, Double total, Date date, String em_username, String tipo_pagamento) {
        this.id_ricevuta = id_ricevuta;
        this.customer_id = customer_id;
        this.total = total;
        this.date = date;
        this.em_username = em_username;
        this.tipo_pagamento = tipo_pagamento;
    }


    // Metodo clone
    @Override
    public Ricevuta clone() throws CloneNotSupportedException {
        return (Ricevuta) super.clone();
    }
    
    public void setId_ricevuta(Integer id_ricevuta) {
        this.id_ricevuta = id_ricevuta;
    }

    public void setCustomer_id(String cID) {
        this.customer_id = cID;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEm_username(String em_username) {
        this.em_username = em_username;
    }

    public void setTipo_pagamento(String tipo_pagamento) {
        this.tipo_pagamento = tipo_pagamento;
    }
    public Integer getId_ricevuta() {
        return this.id_ricevuta;
    }

    public String getCustomer_id() {
        return this.customer_id;
    }

    public Double getTotal() {
        return this.total;
    }

    public Date getDate() {
        return this.date;
    }

    public String getEm_username() {
        return this.em_username;
    }

    public String getTipo_pagamento() {
        return this.tipo_pagamento;
    }
    
}
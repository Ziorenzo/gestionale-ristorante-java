package application.Models;

public class cucinaData {

    private Integer id;
    private String prod_name;
    private Integer quantity;
    private String customer_id;

    public cucinaData(Integer id, String prod_name, Integer quantity,
    		String customer_id) {
        this.id = id;
        this.prod_name = prod_name;
        this.quantity = quantity;
        this.customer_id = customer_id;
    }

    public Integer getId() {
        return id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getCustomer_id() {
        return customer_id;
    }


}

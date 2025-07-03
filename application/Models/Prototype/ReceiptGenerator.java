package application.Models.Prototype;

public class ReceiptGenerator {
    public static Ricevuta generateReceipt(String selectedPaymentMethod, int idreceipt, String customer_id, double totalP, java.sql.Date sqlDate, String em_username, int tavolo_id) {
        switch (selectedPaymentMethod) {
            case "Cash":
                return new CashReceipt(idreceipt, customer_id, totalP, sqlDate, em_username, tavolo_id, selectedPaymentMethod);
            case "Credit Card":
                return new CreditCardReceipt(idreceipt, customer_id, totalP, sqlDate, em_username, tavolo_id, selectedPaymentMethod);
            case "Debit Card":
                return new DebitCardReceipt(idreceipt, customer_id, totalP, sqlDate, em_username, tavolo_id, selectedPaymentMethod);
            default:
                return null;
        }
    }
}
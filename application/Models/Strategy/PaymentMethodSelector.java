package application.Models.Strategy;

public class PaymentMethodSelector {
    public PaymentStrategy selectPaymentMethod(String paymentMethodName) {
        switch (paymentMethodName) {
            case "Cash":
                return new CashPaymentStrategy();
            case "Credit Card":
                return new CreditCardPaymentStrategy();
            case "Debit Card":
                return new DebitCardPaymentStrategy();
            default:
                throw new IllegalArgumentException("Invalid payment method: " + paymentMethodName);
        }
    }
}

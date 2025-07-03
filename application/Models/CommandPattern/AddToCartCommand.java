package application.Models.CommandPattern;

import application.Controllers.Employee.cardProductController;

// Questa classe rappresenta un comando per aggiungere un prodotto al carrello
public class AddToCartCommand implements Command {
    private cardProductController controller;

    // Costruttore che accetta un controller cardProductController
    public AddToCartCommand(cardProductController controller) {
        this.controller = controller;
    }

    // Implementazione del metodo execute per eseguire il comando
    @Override
    public void execute() {
        controller.addOrderToCart();
    }	
}

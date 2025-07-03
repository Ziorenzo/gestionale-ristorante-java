package application.Models.CommandPattern;

import application.Controllers.Employee.menuformController;

public class RemoveFromCartCommand implements Command {
    private menuformController menuformcontroller;

    public RemoveFromCartCommand(menuformController menuformcontroller) {
        this.menuformcontroller = menuformcontroller;
    }

    @Override
    public void execute() {
    	menuformcontroller.removeOrderFromCart();
    }
}



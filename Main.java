package Project;

public class Main {

    public static void main(String[] args) {

        while (true) {
            CommandHandle.show();
            Command com = CommandHandle.getInput();
            if (com == Command.ADD) {
                CommandHandle.addEmployee();
                System.out.println("\n\n");
            } else if (com == Command.REMOVE) {
                CommandHandle.RemoveEmployee();
                System.out.println("\n\n");
            }
            if (com == Command.LISTOFEMPLOYEE) {
                CommandHandle.ListOfEmployee();
                System.out.println("\n\n");
            }
            if (com == Command.PAYROLLDETAILS) {
                CommandHandle.PayrollDetiails();
                System.out.println("\n\n");
            }
            if (com == Command.PAY) {
                CommandHandle.pay();
                System.out.println("\n\n");
            }
            if (com == Command.END) {
                System.exit(0);
            }
        }
    }
}

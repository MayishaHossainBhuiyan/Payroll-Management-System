package Project;

public class Main {
    public static void main(String[] args) {

        while (true) {
            try {
                CommandHandle.show();
                Command com = CommandHandle.getInput();

                if (com == Command.ADD) {
                    CommandHandle.addEmployee();
                    System.out.println();
                } else if (com == Command.REMOVE) {
                    CommandHandle.removeEmployee();
                    System.out.println();
                } else if (com == Command.LISTOFEMPLOYEE) {
                    EmployeeDataHandle.fetchEmployeeData();
                    System.out.println();
                } else if (com == Command.PAYROLLDETAILS) {
                    CommandHandle.payrollDetails();
                    System.out.println();
                } else if (com == Command.PAY) {
                    CommandHandle.pay();
                    System.out.println();
                } else if (com == Command.END) {
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                }
            } catch (Exception ex) {
                System.out.println("Unexpected error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}

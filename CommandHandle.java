package Project;

import java.util.Scanner;

public class CommandHandle {

    static void show() {
        System.out.println("***************************************");
        System.out.println("Pres 1 - Add Employee");
        System.out.println("Pres 2 - Remove Employee");
        System.out.println("Pres 3 - List Of Employee");
        System.out.println("Pres 4 - Payroll Details");
        System.out.println("Pres 5 - Make Payroll");
        System.out.println("Pres 6 - End");
    }
    static Command getInput() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your Command: ");
        int in = scan.nextInt();
        switch (in) {
            case 1:
                return Command.ADD;
            case 2:
                return Command.REMOVE;
            case 3:
                return Command.LISTOFEMPLOYEE;
            case 4:
                return Command.PAYROLLDETAILS;
            case 5:
                return Command.PAY;
            default:
                return Command.END;
        }

    }
        
    
    static void addEmployee(){
          Scanner scan = new Scanner(System.in);
          System.out.print("Enter the id: ");
          int id = scan.nextInt();
          System.out.print("Enter the Name: ");
          String name = scan.next();
          System.out.print("Enter the age: ");
          int age = scan.nextInt();
          System.out.print("Enter Phone Number: ");
          String phoneNumber = scan.next();
          System.out.print("Enter salary: ");
          int salary = scan.nextInt();
          Employee em = new Employee(id,name,age,phoneNumber,salary);
          EmplyeeDataHandle.addEmployeeToDatabase(em);
        }
    
    static void RemoveEmployee(){
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter the id: ");
            int id = scan.nextInt();
            EmplyeeDataHandle.deleteEmployeeFromDatabase(id);
    }
    
    static void ListOfEmployee(){
            EmplyeeDataHandle.FetchEmplyeeData();
    }
    static void PayrollDetiails(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Press 1 - show paid employee of January");
        System.out.println("Press 2 - show paid employee of February");
        System.out.println("Press 3 - show paid employee of March");
        System.out.println("Press 4 - show paid employee of April");
        System.out.println("Press 5 - show paid employee of May");
        System.out.println("Press 6 - show paid employee of June");
        System.out.println("Press 7 - show paid employee of July");
        System.out.println("Press 8 - show paid employee of August");
        System.out.println("Press 9 - show paid employee of September");
        System.out.println("Press 10 - show paid employee of October");
        System.out.println("Press 11 - show paid employee of November");
        System.out.println("Press 12 - show paid employee of December");
        System.out.print("Enter: ");
        int in = scan.nextInt();
        EmplyeeDataHandle.payrollDetiailsFromDatabase(in);
    }
    static void pay(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the id: ");
        int id = scan.nextInt();
        System.out.println("{January, February, March, April, May, June, July, August, September, October, November, and December}");
        System.out.print("Enter Month Name: ");
        String month = scan.next();
         EmplyeeDataHandle.PayDatabase(id,month);
    }
    
}

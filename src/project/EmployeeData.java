package Project;

public interface EmployeeData {
    int getSalary();
    void setSalary(int salary);

    String getPhoneNumber();
    void setPhoneNumber(String phoneNumber);

    int getId();
    void setId(int id);

    String getPaidMonths();  
    void addMonth(String month);
}

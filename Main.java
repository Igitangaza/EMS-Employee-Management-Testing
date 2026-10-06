// Demo: seeds 3 employees and prints the payroll report
public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        ems.addEmployee(new Employee("EMP001", "Alice Uwase", "Accountant", 450000));
        ems.addEmployee(new Employee("EMP002", "Jean Mugisha", "Manager", 900000));
        ems.addEmployee(new Employee("EMP003", "Grace Ineza", "Clerk", 80000));
        System.out.println(new ReportGenerator().generatePayrollReport(ems));
    }
}

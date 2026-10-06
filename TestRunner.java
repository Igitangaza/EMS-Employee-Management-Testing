// Runs every black-box (BB) and white-box (WB) test case and prints PASS/FAIL
public class TestRunner {
    static int passed = 0, failed = 0;

    static void check(String id, String expected, String actual, boolean ok) {
        System.out.printf("%-6s | Expected: %-45s | Actual: %-45s | %s%n", id, expected, actual, ok ? "PASS" : "FAIL");
        if (ok) passed++; else failed++;
    }

    static boolean rejected(EmployeeManagementSystem ems, Employee e) {
        try { ems.addEmployee(e); return false; } catch (IllegalArgumentException ex) { return true; }
    }

    static String f(double d) { return String.format("%.2f", d); }

    public static void main(String[] args) {
        PayrollProcessor p = new PayrollProcessor();
        System.out.println("===== BLACK-BOX TESTS (Functional) =====");

        // BB-01 FR1
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        boolean r = rejected(ems, new Employee("EMP001", "Alice Uwase", "Accountant", 450000));
        check("BB-01", "Employee added", r ? "Rejected" : "Employee added", !r && ems.findEmployee("EMP001") != null);

        // BB-02 FR2 (only 1 employee in list)
        r = rejected(ems, new Employee("EMP001", "Bob Mugisha", "Clerk", 200000));
        check("BB-02", "Rejected: duplicate employee ID", r ? "Rejected" : "Accepted - duplicate silently added", r);

        // BB-03 / BB-04 FR3
        r = rejected(new EmployeeManagementSystem(), new Employee("EMP002", "Test", "Clerk", 0));
        check("BB-03", "Rejected: salary must be > 0", r ? "Rejected" : "Accepted", r);
        r = rejected(new EmployeeManagementSystem(), new Employee("EMP003", "Test", "Clerk", -50000));
        check("BB-04", "Rejected: salary must be > 0", r ? "Rejected" : "Accepted", r);

        // BB-05..07 FR7 (equivalence + boundary)
        check("BB-05", "0.00", f(p.calculateTax(50000)), p.calculateTax(50000) == 0);
        check("BB-06", "0.00", f(p.calculateTax(100000)), p.calculateTax(100000) == 0);
        check("BB-07", "10000.10", f(p.calculateTax(100001)), f(p.calculateTax(100001)).equals("10000.10"));

        // BB-08 FR4
        ems = new EmployeeManagementSystem();
        ems.addEmployee(new Employee("EMP004", "Eric", "Driver", 150000));
        boolean up = ems.updateSalary("EMP004", 250000);
        double s = ems.findEmployee("EMP004").getBaseSalary();
        check("BB-08", "Updated, salary=250000.0", (up ? "Updated" : "Not updated") + ", salary=" + s, up && s == 250000);

        // BB-09 FR5
        ems.addEmployee(new Employee("EMP005", "Diane", "Cashier", 120000));
        boolean rm = ems.removeEmployee("EMP005");
        boolean found = ems.findEmployee("EMP005") != null;
        check("BB-09", "Removed, found=false", (rm ? "Removed" : "Not removed") + ", found=" + found, rm && !found);

        // BB-10 FR8/FR9
        ems = new EmployeeManagementSystem();
        ems.addEmployee(new Employee("EMP006", "Paul", "Officer", 100000));
        ems.addEmployee(new Employee("EMP007", "Ange", "Officer", 200000));
        String rep = new ReportGenerator().generatePayrollReport(ems);
        boolean ok = rep.contains("EMP006") && rep.contains("EMP007") && rep.contains("Total employees: 2");
        check("BB-10", "Report lists both + 'Total employees: 2'", ok ? "Report contains both + correct total" : "Report incomplete", ok);

        System.out.println("\n===== WHITE-BOX TESTS (Structural) =====");

        // WB-01 statement coverage: all 4 branches
        String a = f(p.calculateTax(50000)) + " / " + f(p.calculateTax(300000)) + " / " + f(p.calculateTax(700000)) + " / " + f(p.calculateTax(1500000));
        check("WB-01", "0.00 / 30000.00 / 140000.00 / 450000.00", a, a.equals("0.00 / 30000.00 / 140000.00 / 450000.00"));

        // WB-02 boundary of first decision
        a = f(p.calculateTax(99999)) + " / " + f(p.calculateTax(100000)) + " / " + f(p.calculateTax(100001));
        check("WB-02", "0.00 / 0.00 / 10000.10", a, a.equals("0.00 / 0.00 / 10000.10"));

        // WB-03 loop boundary: size()==1, loop starting at i=1 never runs
        ems = new EmployeeManagementSystem();
        ems.addEmployee(new Employee("EMP001", "Alice", "Clerk", 200000));
        r = rejected(ems, new Employee("EMP001", "Bob", "Clerk", 200000));
        check("WB-03", "Duplicate detected and rejected", r ? "Duplicate detected" : "Duplicate NOT detected", r);

        // WB-04 path: duplicate at index 1 of 3
        ems = new EmployeeManagementSystem();
        ems.addEmployee(new Employee("EMP010", "A", "Clerk", 200000));
        ems.addEmployee(new Employee("EMP011", "B", "Clerk", 200000));
        ems.addEmployee(new Employee("EMP012", "C", "Clerk", 200000));
        r = rejected(ems, new Employee("EMP011", "D", "Clerk", 200000));
        check("WB-04", "Duplicate detected and rejected", r ? "Duplicate detected" : "Duplicate NOT detected", r);

        // WB-05 guard clauses: null ID, empty name
        boolean n1 = rejected(new EmployeeManagementSystem(), new Employee(null, "X", "Clerk", 200000));
        boolean n2 = rejected(new EmployeeManagementSystem(), new Employee("EMP020", "", "Clerk", 200000));
        check("WB-05", "Both rejected", "idNullRejected=" + n1 + ", nameEmptyRejected=" + n2, n1 && n2);

        System.out.println("\nTOTAL: " + (passed + failed) + " | PASSED: " + passed + " | FAILED: " + failed);
    }
}

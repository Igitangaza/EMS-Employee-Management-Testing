// FR8, FR9: payroll report with organization totals
public class ReportGenerator {
    private PayrollProcessor payroll = new PayrollProcessor();

    public String generatePayrollReport(EmployeeManagementSystem ems) {
        StringBuilder sb = new StringBuilder();
        double totalGross = 0, totalTax = 0, totalNet = 0;
        sb.append("PAYROLL REPORT - ORGANIZATION X\n");
        sb.append(String.format("%-8s %-15s %12s %12s %12s%n", "ID", "Name", "Gross", "Tax", "Net"));
        for (Employee e : ems.getAllEmployees()) {
            double gross = e.getBaseSalary();
            double tax = payroll.calculateTax(gross);
            double net = gross - tax;
            totalGross += gross; totalTax += tax; totalNet += net;
            sb.append(String.format("%-8s %-15s %12.2f %12.2f %12.2f%n", e.getId(), e.getName(), gross, tax, net));
        }
        sb.append("Total employees: ").append(ems.getAllEmployees().size()).append("\n");
        sb.append(String.format("Total gross: %.2f | Total tax: %.2f | Total net: %.2f%n", totalGross, totalTax, totalNet));
        return sb.toString();
    }
}

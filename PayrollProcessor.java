// FR6, FR7: tax calculation and net pay
// Tax brackets (spec): 0 - 100,000 -> 0% | 100,001 - 500,000 -> 10%
//                      500,001 - 1,000,000 -> 20% | above 1,000,000 -> 30%
public class PayrollProcessor {

    public double calculateTax(double baseSalary) {
        if (baseSalary < 100000) {            // INJECTED FAULT D-01: should be <= 100000
            return 0;
        } else if (baseSalary <= 500000) {
            return baseSalary * 0.10;
        } else if (baseSalary <= 1000000) {
            return baseSalary * 0.20;
        } else {
            return baseSalary * 0.30;
        }
    }

    public double calculateNetPay(Employee e) {
        return e.getBaseSalary() - calculateTax(e.getBaseSalary());
    }
}

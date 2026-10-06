# Employee Management System – Software Testing Homework

**Student:** Igitangaza Eddy Samuel (ID 22838) – AUCA, Software Testing

Java implementation of an Employee Management System for "Organization X"
(manage employees, process payments, generate payroll reports), used to
practise **black-box** and **white-box** testing with injected faults.

## Files
| File | Purpose |
|------|---------|
| `Employee.java` | Employee record (ID, name, position, base salary) |
| `EmployeeManagementSystem.java` | Add, update, remove, search employees (FR1–FR5, FR10) |
| `PayrollProcessor.java` | Tax brackets and net pay (FR6, FR7) |
| `ReportGenerator.java` | Payroll report with totals (FR8, FR9) |
| `Main.java` | Demo program |
| `TestRunner.java` | Automated test cases BB-01..BB-10 and WB-01..WB-05 |

## How to run
```
javac *.java
java Main          # demo
java TestRunner    # runs all 15 test cases
```

## Results
- 15 test cases: 11 passed, 4 failed – the failures come from 2 deliberately injected faults (D-01 tax boundary, D-02 duplicate-ID loop).
- After fixing both faults: 15/15 passed.

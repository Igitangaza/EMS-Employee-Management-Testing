import java.util.ArrayList;
import java.util.List;

// FR1-FR5, FR10: add, update, remove, find employees
public class EmployeeManagementSystem {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee e) {
        if (e.getId() == null || e.getId().isEmpty())
            throw new IllegalArgumentException("Employee ID is required");
        if (e.getName() == null || e.getName().isEmpty())
            throw new IllegalArgumentException("Employee name is required");
        if (e.getBaseSalary() <= 0)
            throw new IllegalArgumentException("Salary must be > 0");

        for (int i = 1; i < employees.size(); i++) {   // INJECTED FAULT D-02: should start at i = 0
            if (employees.get(i).getId().equals(e.getId()))
                throw new IllegalArgumentException("Duplicate employee ID");
        }
        employees.add(e);
    }

    public Employee findEmployee(String id) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    public boolean updateSalary(String id, double newSalary) {
        if (newSalary <= 0) throw new IllegalArgumentException("Salary must be > 0");
        Employee e = findEmployee(id);
        if (e == null) return false;
        e.setBaseSalary(newSalary);
        return true;
    }

    public boolean removeEmployee(String id) {
        Employee e = findEmployee(id);
        if (e == null) return false;
        return employees.remove(e);
    }

    public List<Employee> getAllEmployees() { return employees; }
}

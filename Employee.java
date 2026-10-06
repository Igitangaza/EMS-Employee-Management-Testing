// Employee record: id, name, position, base salary
public class Employee {
    private String id;
    private String name;
    private String position;
    private double baseSalary;

    public Employee(String id, String name, String position, double baseSalary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.baseSalary = baseSalary;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }
}

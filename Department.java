import java.util.HashMap;

public class Department {
    private String depCode;
    private String depName;
    private double depTotalSalary;
    private HashMap<String, Employee> employeeMap;

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public double getDepTotalSalary() {
        return depTotalSalary;
    }

    public void setDepTotalSalary(double depTotalSalary) {
        this.depTotalSalary = depTotalSalary;
    }

    public HashMap<String, Employee> getEmployeeMap() {
        return employeeMap;
    }

    public void setEmployeeMap(HashMap<String, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }

}

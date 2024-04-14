
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DepartmentDA {
    private HashMap<String, Employee> employeeMap;
    private EmployeeDA employeeDA;
    public static double salary = 0;

    public DepartmentDA() {

        try {
            Scanner departmentFile = new Scanner(new FileReader("src/dep.csv"));
            String depCode;

            while (departmentFile.hasNext()) {
                employeeMap = new HashMap<>();
                double totalSalary = 0;

                String depFileLineData = departmentFile.nextLine();
                String[] depFileSplit = depFileLineData.split(",");

                depCode = depFileSplit[0].trim();

                Department department = new Department();
                department.setDepCode(depCode);
                department.setDepName(depFileSplit[1].trim());

                readDepEmp(department);

                for (Map.Entry<String, Employee> entryMap : department.getEmployeeMap().entrySet()) {
                    totalSalary += entryMap.getValue().getSalary();
                }

                department.setDepTotalSalary(totalSalary);

                myDysfunction(department);

                printDepartment(department);
            }



            departmentFile.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
    }

    public void readDepEmp(Department department) {
        EmployeeDA employeeDA = new EmployeeDA(department.getDepCode());
        department.setEmployeeMap(employeeDA.getEmployeeMap());

    }


    public void myDysfunction(Department department) {
        try {

            Scanner depEmpFile = new Scanner(new FileReader("src/deptemp.csv"));

            while (depEmpFile.hasNext()) {

                String depEmpFileLineData = depEmpFile.nextLine();
                String[] depEmpFileSplit = depEmpFileLineData.split(",");

                String depCode = depEmpFileSplit[0].trim();
                String empNo = depEmpFileSplit[1].trim();
                salary = Double.parseDouble(depEmpFileSplit[2].trim());


                if (depCode.equals(department.getDepCode())) {
                    EmployeeDA employeeDA = new EmployeeDA(empNo);
                    HashMap<String, Employee> empMap = employeeDA.getEmployeeMap();
                    department.getEmployeeMap().putAll(empMap);
                    department.setDepTotalSalary(department.getDepTotalSalary() + salary);
                }
            }

            depEmpFile.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }

    public void printDepartment(Department department) {
        System.out.println("Department code: " + department.getDepCode());
        System.out.println("Department name: " + department.getDepName());
        System.out.println("Department total salary: " + department.getDepTotalSalary());

        System.out.println("---------------------Details-------------------------");
        System.out.println("EmpNo\t\tEmployee Name\tSalary");

        for (Map.Entry<String, Employee> entryMap : department.getEmployeeMap().entrySet()) {
            System.out.print(entryMap.getValue().getEmpNo() + "\t");
            System.out.print(entryMap.getValue().getLastName() + "\t");
            System.out.print(entryMap.getValue().getFirstName() + "\t");
            System.out.println(entryMap.getValue().getSalary());
        }
        System.out.println();
    }

}

package model;

public class EmployeeCUD {

    private String employee_name;
    private float employee_salary;
    private int employee_age;

    public EmployeeCUD(String employee_name, float employee_salary, int employee_age) {
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;

    }



    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public float getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(float employee_salary) {
        this.employee_salary = employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }


}

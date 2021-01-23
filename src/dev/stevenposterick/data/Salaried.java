package dev.stevenposterick.data;

import dev.stevenposterick.utils.StringFormatter;

/**
 *
 * @author Steven Posterick
 * @version 1/19/2021
 * Salaried Employee sub class.
 */
public class Salaried extends Employee {

    private static int salaryEmployees;
    private String title;
    private int salary;

    /**
     *
     * @param id - id of the employee
     * @param name - name of the employee
     * @param title - title of the salaried employee
     * @param salary - salary of the salaried employee
     */
    public Salaried(int id, String name, String title, int salary) {
        super(id, name);
        this.title = title;
        this.salary = salary;

        // Increment the salaried employees.
        ++salaryEmployees;
    }

    /**
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title - sets the title of the salaried employee
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     *
     * @param salary - sets the salary of the salaried employee
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     *
     * @return salaryEmployees
     */
    public static int getSalaryEmployees() {
        return salaryEmployees;
    }

    /**
     *
     * @return the contents of the instance
     */
    @Override
    public String toString() {
        return super.toString() + ":" +
                StringFormatter.getFormattedClassString(":",
                        this.getClass(),
                        this.salary,
                        this.title);
    }

    /**
     *
     * @param o - object o
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Salaried)) return false;

        Salaried s = (Salaried) o;
        return super.equals(s) &&
                salary == s.salary &&
                title.equals(s.title);
    }
}

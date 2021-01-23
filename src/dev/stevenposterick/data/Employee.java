package dev.stevenposterick.data;

import dev.stevenposterick.utils.StringFormatter;

/**
 *
 * @author Steven Posterick
 * @version 1/19/2021
 * Employee super class.
 */
public class Employee {

    private static int employeeCount;
    private int id;
    private String name;

    /**
     * Constructor for the Employee class
     * @param id - id of the employee
     * @param name - name of the employee
     */
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;

        // Increment the total employee count
        employeeCount++;
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id - sets the id of the employee
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name - sets the name of the employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return employeeCount
     */
    public static int getEmployeeCount() {
        return employeeCount;
    }
    /**
     *
     *
     * @return the contents of the instance
     */
    @Override
    public String toString() {
        return StringFormatter.getFormattedClassString(":",
                (this.getClass().equals(Employee.class) ? // Makes it use Employee when using super.toString()
                        this.getClass() :
                        this.getClass().getSuperclass()),
                this.id,
                this.name);
    }

    /**
     *
     * @param o - object o
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Employee))
            return false;

        Employee e = (Employee) o;
        return id == e.id &&
                name.equals(e.name);
    }
}

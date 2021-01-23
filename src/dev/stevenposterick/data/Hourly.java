package dev.stevenposterick.data;

import dev.stevenposterick.utils.StringFormatter;

import java.util.Objects;

/**
 *
 * @author Steven Posterick
 * @version 1/19/2021
 * Hourly Employee sub class.
 */
public class Hourly extends Employee {

    private static int hourlyEmployees;
    private String position;
    private double hourlyRate;

    /**
     *
     * @param id - id of the employee
     * @param name - name of the employee
     * @param position - position of the hourly employee
     * @param hourlyRate - hourly rate of the hourly employee
     */
    public Hourly(int id, String name, String position, double hourlyRate) {
        super(id, name);
        this.position = position;
        this.hourlyRate = hourlyRate;

        // Increment the total hourly employees.
        ++hourlyEmployees;
    }

    /**
     *
     * @return position
     */
    public String getPosition() {
        return position;
    }

    /**
     *
     * @param position - sets the position of the hourly employee
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     *
     * @return hourlyRate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /**
     *
     * @param hourlyRate - sets the hourly rate of the hourly employee
     */
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     *
     * @return hourlyEmployees
     */
    public static int getHourlyEmployees() {
        return hourlyEmployees;
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
                        this.hourlyRate,
                        this.position,
                        hourlyEmployees);
    }

    /**
     *
     * @param o - object o
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Hourly)) return false;

        Hourly h = (Hourly) o;

        // Ensure all the fields data are equal.
        return super.equals(h) &&
                Math.abs(h.hourlyRate - hourlyRate) < 0.01 &&
                h.position.equals(position);
    }
}

package dev.stevenposterick.core;

import dev.stevenposterick.data.Employee;
import dev.stevenposterick.data.Hourly;
import dev.stevenposterick.data.Salaried;
import dev.stevenposterick.utils.ConsoleParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Steven Posterick
 * @version 1/19/2021
 * Client reads the files from the employee list, then manipulates the employee classes, prints out the employees,
 * and demonstrates the working use of the equals methods.
 */
public class Client {

    public static void main(String[] args) throws FileNotFoundException {
        // Array of the employees.
        Employee[] employeeArray = new Employee[20];

        // Create a scanner
        Scanner scanner = new Scanner(new File("C:\\data\\employeeList.txt"));

        // Integer that stores the total iterations.
        int i = 0;

        // Fill the array with the read data stop iterating when either the file is empty or over the array length.
        for (; scanner.hasNextLine() && i < employeeArray.length; i++) {
            // Store the line.
            String employeeLine = scanner.nextLine();
            try {
                // Split the data by the tab delimiter.
                String[] delimitedEmployee = employeeLine.split("\t");

                // First element is the character S (salaried) or H (hourly) employee.
                boolean salaried = delimitedEmployee[0].equals("S");

                // Second element is the employees id.
                int id = Integer.parseInt(delimitedEmployee[1]);

                // Third element is the employees name.
                String name = delimitedEmployee[2];

                // Fourth element is the employees title (if salaried) or position (if hourly)
                String jobType = delimitedEmployee[3];

                // Fifth element is the employees salary (if salaried) or hourly rate (if hourly)
                String payString = delimitedEmployee[4];

                // Create an employee instance to add to array.
                Employee employee = salaried ?
                        new Salaried(id, name, jobType, Integer.parseInt(payString)) :
                        new Hourly(id, name, jobType, Double.parseDouble(payString));

                // Add the employee instance to the array.
                employeeArray[i] = employee;

            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid line in the employee list: " + employeeLine);
                e.printStackTrace();
            }
        }

        // Add an additional salaried employee from user input.
        employeeArray[i++] = createSalariedWorker();

        // Add an additional hourly employee from user input.
        employeeArray[i++] = createHourlyWorker();

        // Loop through all the employees.
        for (Employee employee : employeeArray) {
            System.out.println(employee);
        }

        // Give everyone a 10% raise.
        for (int j = 0; j < i; j++) {
            Employee employee = employeeArray[j];

            if (employee == null) continue;

            if (employee instanceof Hourly){
                // Store the hourly employee object.
                Hourly h = (Hourly) employee;

                // Set the rate to 1.1 times the rate.
                h.setHourlyRate(h.getHourlyRate() * 1.1);
            } else if (employee instanceof Salaried){
                // Store the salaried employee object.
                Salaried s = (Salaried) employee;

                // Set the salary to 1.1 times the salary.
                s.setSalary((int) (s.getSalary() * 1.1));
            }
        }

        // Loop through all the valid employees.
        for (int j = 0; j < i; j++) {
            Employee employee = employeeArray[j];

            // Need this just in case there is an error thrown in the adding which would cause a null entry.
            if (employee == null) continue;

            // Print the employee.
            System.out.println(employee.toString());
        }

        // Print out the ending totals.
        System.out.println("Total employee count: " + Employee.getEmployeeCount());
        System.out.println("Total hourly employee count: " + Hourly.getHourlyEmployees());
        System.out.println("Total Salaried employee count: " + Salaried.getSalaryEmployees());

        // Employees for testing .equals()
        Employee employeeSam1 = new Employee(33, "Sam");
        Employee employeeJess = new Employee(22, "Jess");
        Employee employeeSam2 = new Employee(33, "Sam");

        // Test employee .equals()
        System.out.println("Employee equals test, two different objects: " + employeeJess.equals(employeeSam1));
        System.out.println("Employee equals test, same data in objects: " + employeeSam1.equals(employeeSam2));

        // Salaried workers for testing .equals()
        Salaried salariedJan1 = new Salaried(50, "Jan", "CEO", 200_000);
        Salaried salariedPaul = new Salaried(30, "Paul", "Accountant", 50_000);
        Salaried salariedJan2 = new Salaried(50, "Jan", "CEO", 200_000);

        // Test salaried .equals()
        System.out.println("Salaried equals test, two different objects: " + salariedJan1.equals(salariedPaul));
        System.out.println("Salaried equals test, same data in objects: " + salariedJan1.equals(salariedJan2));

        // Hourly workers for testing .equals()
        Hourly hourlyFrancis1 = new Hourly(40, "Francis", "Contractor", 40);
        Hourly hourlyJon = new Hourly(25, "Jon", "Intern Contractor", 20);
        Hourly hourlyFrancis2 = new Hourly(40, "Francis", "Contractor", 40);

        // Test hourly .equals()
        System.out.println("Salaried equals test, two different objects: " + hourlyFrancis1.equals(hourlyJon));
        System.out.println("Salaried equals test, same data in objects: " + hourlyFrancis1.equals(hourlyFrancis2));
    }

    /**
     *
     * @return id - the id of an employee from user input.
     */
    private static int getEmployeeID(){
        return ConsoleParser.getUserInput("Please enter the id of the worker: ", ConsoleParser.INTEGER_PARSER);
    }

    /**
     *
     * @return - the name of an employee from user input.
     */
    private static String getEmployeeName(){
        return ConsoleParser.getUserInput("Please enter the name: ", ConsoleParser.STRING_PARSER);
    }

    /**
     *
     * @return an hourly employee created from input from the console.
     */
    private static Hourly createHourlyWorker(){
        // Get all the fields of the hourly worker.
        int id = getEmployeeID();
        String name = getEmployeeName();
        String position = ConsoleParser.getUserInput("Please enter the position of the employee: ", ConsoleParser.STRING_PARSER);
        double hourlyRate = ConsoleParser.getUserInput("Please enter the hourly rate of the employee: ", ConsoleParser.DOUBLE_PARSER);

        // Create and return the hourly worker.
        return new Hourly(id, name, position, hourlyRate);
    }

    /**
     *
     * @return a salaried employee from input from the console.
     */
    private static Salaried createSalariedWorker(){
        // Get all the fields of the salaried worker.
        int id = getEmployeeID();
        String name = getEmployeeName();
        String title = ConsoleParser.getUserInput("Please enter the title of the employee: ", ConsoleParser.STRING_PARSER);
        int salary = ConsoleParser.getUserInput("Please enter the salary of the employee: ", ConsoleParser.INTEGER_PARSER);

        // Create and return the salaried worker.
        return new Salaried(id, name, title, salary);
    }
}

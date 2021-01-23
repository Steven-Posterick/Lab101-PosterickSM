package dev.stevenposterick.utils;

import java.util.Scanner;

/**
 * @author Steven Posterick
 * @version 1/22/2021
 * A simple utility class for using the scanner to return input from the System.in console.
 */
public class ConsoleParser {
    // Parser for parsing doubles.
    public static final Parser<Double> DOUBLE_PARSER = (s) -> {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e){
            return null;
        }
    };

    // Parser for parsing integers.
    public static final Parser<Integer> INTEGER_PARSER = (s)-> {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e){
            return null;
        }
    };

    // Parser for strings (returns self)
    public static final Parser<String> STRING_PARSER = (s)-> s;

    /**
     *
     * Interface used to parse a specified type
     * @param <T> type of object to parse
     */
    public interface Parser<T> {
        /**
         *
         * @param s - string value that will be parsed.
         * @return T - the type of input you want, if invalid return null, otherwise return the non-null type.
         */
        T parse(String s);
    }

    /**
     *
     * @param output - text to output to the user.
     * @param parser - parser interface to get the value of string in correct type.
     * @param <T> - type of object that you want back.
     * @return value - the parsed input from the scanner (cannot be null).
     */
    public static <T>T getUserInput(String output, Parser<T> parser){
        // Create a scanner.
        Scanner scanner = new Scanner(System.in);

        // Field that will be returned.
        T value = null;

        while (value == null) {
            // Output the initial output text.
            System.out.println(output);

            // Parse the value, if it is invalid it will return null and continue.
            value = parser.parse(scanner.nextLine());

            // Tell them to re input the value if null.
            if (value == null)
                System.out.println("Invalid input, please retype the value.");
        }

        return value;
    }
}

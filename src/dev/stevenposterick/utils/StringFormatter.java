package dev.stevenposterick.utils;

/**
 *
 * @author Steven Posterick
 * @version 1/19/2021
 * A simple utility class for formatting strings
 */
public class StringFormatter {

    /**
     *
     * @param classType - the class in which it will print the simple name of
     * @param objects - the fields which will be delimited to show the data inside the instance
     * @return a formatted string to show the values inside of an instance
     */
    public static String getFormattedClassString(Class<?> classType, Object... objects) {
        // Use comma by default.
        return getFormattedClassString(",", classType, objects);
    }

    /**
     *
     * @param delimiter - the character to delimit the data
     * @param classType - the class in which it will print the simple name of
     * @param objects - the fields which will be delimited to show the data inside the instance
     * @return a formatted string to show the values inside of an instance
     */
    public static String getFormattedClassString(String delimiter, Class<?> classType, Object... objects) {
        StringBuilder sb = new StringBuilder();

        // Start with the class name.
        sb.append(classType.getSimpleName());

        // Surround in parenthesis.
        sb.append(" (");

        // Then append all the elements put in.
        for (int i = 0; i < objects.length; i++) {

            // Append the object's string value.
            sb.append(objects[i].toString());

            // Only add a comma if it is not the last element.
            if (i != objects.length - 1){
                sb.append(delimiter);
            }
        }

        // End parenthesis.
        sb.append(")");

        return sb.toString();
    }
}

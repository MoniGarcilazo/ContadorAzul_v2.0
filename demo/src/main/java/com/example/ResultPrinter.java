package com.example;

import java.util.List;

import com.example.constants.SymbolsConstants;

/**
 * The ResultPrinter class is responsible for formatting and printing a table 
 * with the results of a program's line count.
 * 
 * <p>It prints a table in the console displaying the program name, the number 
 * of logical lines, and the number of physical lines of code.</p>
 * 
 */
public class ResultPrinter {

    /**
     * Represents the title for the program column.
     */
    public static final String TITLE_PROGRAM = "Programa";

    /**
     * Represents the title for the physical lines of code (LOC) column.
     */
    public static final String TITLE_PHYSICAL_LOC = "LOC Físicas";

    /**
     * Represents the title for the classes column.
     */
    public static final String TITLE_CLASS = "Clase";
    /**
     * Represents the title for the methods column.
    */
    public static final String TITLE_METHODS_CLASS = "Total de métodos en la clase";
        /**
     * Represents the title for the total LOC of the class.
    */
    public static final String TITLE_TOTAL_LOC_CLASS = "Total de LOC físicas de la clase";
    /**
     * Represents the title for the total LOC.
    */
    public static final String TITLE_TOTAL_LOC = "Total de LOC físicas del programa";

    /**
     * Defines the format template for a table column.
     * This format ensures that each column has a fixed width, aligning text properly.
     * The placeholders `%d` are used to specify column width dynamically.
     */
    public static final String COLUMN_FORMAT_TEMPLATE = "| %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds |\n";

    /**
     * Defines the horizontal padding used in table formatting.
     * This value represents the number of spaces added on each side
     * of the content within a cell to improve readability.
     */
    public static final int HORIZONTAL_PADDING = 2;
    
    /**
     * Prints a table in the console with the results of the line count.
     * 
     * @param programName Name of the analyzed program.
     * @param classInfoList List of ClassInfo objects for each class.
     * @param totalLOC Total number of physical LOC in the program.
     */
    public static void printResults(String programName, List<ClassInfo> classInfoList, int totalLOC) {
        String tableText = buildTable(programName, classInfoList, totalLOC);
        System.out.println(tableText);
    }   

    /**
     * Builds a formatted table with the results of the line count.
     * 
     * @param programName Name of the analyzed program.
     * @param classInfoList List of ClassInfo objects for each class.
     * @param totalLOC Total number of physical LOC in the program.
     * @return A formatted string representing the table with the data.
     */
    private static String buildTable(String programName, List<ClassInfo> classInfoList, int totalLOC) {

        int maxProgramLength = getMaxColumnWidth(TITLE_PROGRAM, programName);
        int maxClassNameLength = getMaxColumnWidthMultiple(TITLE_CLASS, classInfoList.stream().map(ClassInfo::getClassName).toList());
        int maxMethodsLength = getMaxColumnWidthMultiple(TITLE_METHODS_CLASS, classInfoList.stream().map(ClassInfo::getMethodCount).map(Object::toString).toList());
        int maxLOCClassLength =getMaxColumnWidthMultiple(TITLE_TOTAL_LOC_CLASS, classInfoList.stream().map(ClassInfo::getPhysicalLines).map(Object::toString).toList());
        int maxLOCLenght = getMaxColumnWidth(TITLE_TOTAL_LOC, String.valueOf(totalLOC));

        String headerFormat = createHeaderFormat(maxProgramLength, maxClassNameLength, maxMethodsLength, maxLOCClassLength, maxLOCLenght);
        String separator = createSeparator(maxProgramLength, maxClassNameLength, maxMethodsLength, maxLOCClassLength, maxLOCLenght);

        StringBuilder table = new StringBuilder();
        table.append(separator);
        table.append(String.format(headerFormat, TITLE_PROGRAM, TITLE_CLASS, TITLE_METHODS_CLASS, TITLE_TOTAL_LOC_CLASS, TITLE_TOTAL_LOC));
        table.append(separator);
        

        for (ClassInfo classInfo : classInfoList) {
            table.append(String.format(headerFormat, programName, classInfo.getClassName(), classInfo.getMethodCount(), classInfo.getPhysicalLines(), totalLOC));
        }

        table.append(separator);
        return table.toString();
    }

    /**
     * Calculates the maximum column width based on the length of the title and the corresponding value.
     *
     * @param title The title of the column.
     * @param value The value to be displayed in the column.
     * @return The maximum width required for the column.
     */
    private static int getMaxColumnWidth(String title, String value) {
        return Math.max(title.length(), value.length());
    }
    /**
     * Calculates the maximum column width based on the length of the title and a list of values.
     *
     * @param title The title of the column.
     * @param values The list of values to be displayed in the column.
     * @return The maximum width required for the column.
     */
    private static int getMaxColumnWidthMultiple(String title, List<String> values) {
        int maxLength = title.length();
        for (String value : values) {
            maxLength = Math.max(maxLength, value.length());
        }
        return maxLength;
    }
    /**
     * Creates the header row of the table, formatting the column titles
     * with the appropriate column widths.
     *
     * @param maxProgramLength   The maximum width of the column for the program name.
     * @param maxClassNameLength  The maximum width of the column for the class ame.
     * @param maxMethodsLength   The maximum width of the column for the number of methods.
     * @param maxLOCClassLength   The maximum width of the column for the lines of code of the class.
     * @param maxLOCLength   The maximum width of the column for the lines of code of the program.
     * @return A string representing the formatted header row of the table.
     */
    private static String createHeaderFormat(int maxProgramLength, int maxClassNameLength, int maxMethodsLength, int maxLOCClassLength, int maxLOCLenght) {
        String header = String.format(
            COLUMN_FORMAT_TEMPLATE,
            maxProgramLength,
            maxClassNameLength,
            maxMethodsLength,
            maxLOCClassLength,
            maxLOCLenght
        );
        return header;
    }

    /**
     * Creates the separator line for the table, adjusting the length of each
     * section based on the column widths.
     *
     * @param maxProgramLength   The maximum width of the column for the program name.
     * @param maxClassNameLength  The maximum width of the column for the class ame.
     * @param maxMethodsLength   The maximum width of the column for the number of methods.
     * @param maxLOCClassLength   The maximum width of the column for the lines of code of the class.
     * @param maxLOCLength   The maximum width of the column for the lines of code of the program.
     * @return A string representing the separator line of the table.
     */
    private static String createSeparator(int maxProgramLength, int maxClassNameLength, int maxMethodsLength, int maxLOCClassLength, int maxLOCLenght) {
        return SymbolsConstants.PLUS_SIGN + 
        SymbolsConstants.MINUS_SIGN.repeat(maxProgramLength + HORIZONTAL_PADDING) + 
        SymbolsConstants.PLUS_SIGN + 
        SymbolsConstants.MINUS_SIGN.repeat(maxClassNameLength + HORIZONTAL_PADDING) +
        SymbolsConstants.PLUS_SIGN + 
        SymbolsConstants.MINUS_SIGN.repeat(maxMethodsLength + HORIZONTAL_PADDING) + 
        SymbolsConstants.PLUS_SIGN +
        SymbolsConstants.MINUS_SIGN.repeat(maxLOCClassLength + HORIZONTAL_PADDING) +
        SymbolsConstants.PLUS_SIGN +
        SymbolsConstants.MINUS_SIGN.repeat(maxLOCLenght + HORIZONTAL_PADDING) +
        SymbolsConstants.PLUS_SIGN +
        "\n";
    }

}

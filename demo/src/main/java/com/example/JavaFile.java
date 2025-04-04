package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.constants.FileFormatConstants;
import com.example.exceptions.FileException;

/**
 * Represents a Java file with information about its directory path, name,
 * content, and metrics for physical and logical lines.
 */
public class JavaFile {

    /**
     * The directory path where the file is located.
     */
    private String filePath;

    /**
     * The name of the file.
     */
    private String name;

    /**
     * The lines of code contained in the file.
     */
    private List<String> lines;

    /**
     * The number of physical lines in the file.
     */
    private int physicalLines;

    /**
     * The number of classes in the file.
     */
    private int classCount;

    /**
     * List of classes in the file.
     */
    private List<ClassInfo> classesInfo;
    
    /**
    * The number of methods in the class
    */
    private int numberOfMethods;

    /**
     * Constructor to create an instance of JavaFile.
     *
     * @param filePath The file path where the file is located.
     * @param name The name of the file.
     * @param lines The lines of code contained in the file.
     * @param physicalLines The number of physical lines in the file.
     * @param logicalLines The number of logical lines in the file.
     * @throws FileException If an error occurs related to file handling (e.g., invalid file).
     * @throws IOException If an I/O error occurs during the file search process.
     */
    public JavaFile(String filePath, String name) throws FileException, IOException {        
        this.filePath = filePath;
        this.name = name;
        this.lines = FileManager.readLines(this.filePath);
        this.physicalLines = 0;
        this.classCount = 0;
        this.numberOfMethods = 0;
        this.classesInfo = new ArrayList<>();
    }
    /**
     * Gets the directory path where the file is located.
     *
     * @return The directory path.
     */
    public String getfilePath() {
        return filePath;
    }

    /**
     * Sets the directory path where the file is located.
     *
     * @param filePath The directory path.
     */
    public void setfilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the name of the file.
     *
     * @return The name of the file.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the file.
     *
     * @param name The name of the file.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the lines of code contained in the file.
     *
     * @return The lines of code.
     */
    public List<String> getLines() {
        return lines;
    }

    /**
     * Sets the lines of code contained in the file.
     *
     * @param lines The lines of code.
     */
    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    /**
     * Gets the number of physical lines in the file.
     *
     * @return The number of physical lines.
     */
    public int getPhysicalLines() {
        return physicalLines;
    }

    /**
     * Sets the number of physical lines in the file.
     *
     * @param physicalLines The number of physical lines.
     */
    public void setPhysicalLines(int physicalLines) {
        this.physicalLines = physicalLines;
    }

    /**
     * Gets the number of logical lines in the file.
     *
     * @return The number of logical lines.
     */
    public int getClassCount() {
        return classCount;
    }

    /**
     * Sets the number of classes in the file.
     *
     * @param classCount The number of classes.
     */
    public void setClassCount(int classCount) {
        this.classCount = classCount;
    }


    /**
     * Checks if the file has a valid Java file extension.
     *
     * @param fileName The name of the file to check.
     * @return {@code true} if the file is a Java file, {@code false} otherwise.
     */
    public static boolean isValidFileType(String name) {
        return name.endsWith(FileFormatConstants.JAVA_FILE_TYPE);
    }
        /**
     * Sets the number of methods in the file
     * 
     * @param numberOfMethods The number of methods
     */
    public void setNumberOfMethods(int numberOfMethods) { 
        this.numberOfMethods = numberOfMethods;
    }

    /**
     * Gets the number of methods in the file
     *
     * @return The number of methods in the file
     */
    public int getNumberOfMethods() {
        return this.numberOfMethods;
    }

    public void addClassInfo(String className, int methodCount, int physicalLines) {
        this.classesInfo.add(new ClassInfo(className, methodCount, physicalLines));
    }
    public List<ClassInfo> getClassesInfo() {
        return this.classesInfo;
    }
}
package com.example;

/**
 * The {@code ClassInfo} class stores information about a Java class, 
 * including its name, the number of methods it contains, and its physical line count.
 */
public class ClassInfo {
    private String className;
    private int methodCount;
    private int physicalLines;

    /**
     * Constructs a {@code ClassInfo} object with the specified class name, 
     * method count, and physical line count.
     *
     * @param className    The name of the class.
     * @param methodCount  The number of methods in the class.
     * @param physicalLines The number of physical lines of code in the class.
     */
    public ClassInfo(String className, int methodCount, int physicalLines) {
        this.className = className;
        this.methodCount = methodCount;
        this.physicalLines = physicalLines;
    }

    /**
     * Gets the name of the class.
     *
     * @return The class name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the number of methods in the class.
     *
     * @return The method count.
     */
    public int getMethodCount() {
        return methodCount;
    }

    /**
     * Gets the number of physical lines of code in the class.
     *
     * @return The physical line count.
     */
    public int getPhysicalLines() {
        return physicalLines;
    }
}

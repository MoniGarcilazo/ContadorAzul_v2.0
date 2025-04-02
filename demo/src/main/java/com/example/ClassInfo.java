package com.example;

public class ClassInfo {
    private String className;
    private int methodCount;
    private int physicalLines;

    public ClassInfo(String className, int methodCount, int physicalLines) {
        this.className = className;
        this.methodCount = methodCount;
        this.physicalLines = physicalLines;
    }

    public String getClassName() {
        return className;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public int getPhysicalLines() {
        return physicalLines;
    }
}


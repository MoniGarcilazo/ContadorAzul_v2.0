package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.constants.JavaRegexConstants;

/**
 * The {@code ClassAnalyzer} class is responsible for analyzing Java source files
 * to extract information about class declarations, including the class name, 
 * method count, and physical line count.
 */
public class ClassAnalyzer {
    /**
     * Regular expression pattern used to match class declarations.
     */
    private Pattern pattern;
    /**
     * Matcher for evaluating patterns against each line.
     */
    private Matcher matcher;

    private final PhysicalLineCounter physicalLineCounter = new PhysicalLineCounter();
    private final MethodLineCounter methodLineCounter = new MethodLineCounter();

    /**
     * Analyzes the provided Java file and returns a list of {@link ClassInfo} objects 
     * containing details about each class, such as the class name, method count, 
     * and physical line count.
     *
     * @param javaFile The Java file to analyze.
     * @return A list of ClassInfo objects with class names, method counts, 
     *         and physical line counts.
     */
    public List<ClassInfo> analyze(JavaFile javaFile) {
        List<String> lines = javaFile.getLines();
        List<ClassInfo> classInfoList = new ArrayList<>();
        
        for (String line : lines) {
            if (isClassDeclaration(line)) {
                String className = extractClassName(line);
                int physicalLines = physicalLineCounter.count(javaFile);
                int methodCount = methodLineCounter.count(javaFile);
                classInfoList.add(new ClassInfo(className, methodCount, physicalLines));
            }
        }
        
        return classInfoList;
    }

    /**
     * Extracts the class name from a class declaration line.
     *
     * @param line The line containing the class declaration.
     * @return The name of the class, or "UnknownClass" if not found.
     */
    private String extractClassName(String line) {
        Matcher matcher = Pattern.compile("\\bclass\\s+(\\w+)").matcher(line);
        return matcher.find() ? matcher.group(1) : "UnknownClass";
    }

    /**
     * Determines whether a given line of code is a class declaration.
     *
     * @param line The line of code to analyze.
     * @return {@code true} if the line declares a class, otherwise {@code false}.
     */
    private boolean isClassDeclaration(String line) {
        line = line.trim();
        this.pattern = Pattern.compile(JavaRegexConstants.CLASS_DECLARATION_REGEX);
        this.matcher = this.pattern.matcher(line);
        return this.matcher.find();
    }
}

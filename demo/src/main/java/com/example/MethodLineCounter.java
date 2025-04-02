package com.example;

import java.util.List;
import java.util.regex.Pattern;

import com.example.constants.JavaRegexConstants;
import com.example.validators.CommentValidator;

import java.util.regex.Matcher;
/**
 * The {@code ClassCounter} class implements the {@code LineCounter} interface
 * to count the number of Java methods declarations in a given source file.
 */
public class MethodLineCounter implements LineCounter {
    /**
     * Regular expression pattern used to match method declaration.
     */
    private Pattern pattern;
    /**
     * Matcher for evaluating patterns against each line.
     */
    private Matcher matcher;

    /**
     * Counts the number of methods declarations in the provided Java file.
     *
     * @param javaFile The Java file to analyze.
     * @return The number of methods declarations found in the file.
     */
    @Override
    public int count(JavaFile javaFile) {
        List<String> lines = javaFile.getLines();
        CommentValidator commentValidator = new CommentValidator();
        int numberOfMethods = (int) lines.stream()
                .filter(line -> !commentValidator.isComment(line))
                .filter(line -> isMethod(line))
                .count();
        return numberOfMethods;
    }

    /**
     * Determines whether a given line of code is a method declaration.
     * 
     * @param line The line of code to analyze.
     * @return {@code true} if the line is a logical line, otherwise {@code false}.
     */
    private boolean isMethod(String line) {
        line = line.trim();
        this.pattern = Pattern.compile(JavaRegexConstants.METHOD_DECLARATION_REGEX);
        this.matcher = this.pattern.matcher(line);
        while (this.matcher.find()) {
            return true;
        }
        return false;
    }
}

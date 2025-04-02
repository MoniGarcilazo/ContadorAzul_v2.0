package com.example;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.example.constants.JavaRegexConstants;
import com.example.validators.CommentValidator;

/**
 * The {@code ClassCounter} class implements the {@code LineCounter} interface
 * to count the number of Java class declarations in a given source file.
 */
public class ClassCounter implements LineCounter {
    /**
     * Regular expression pattern used to match class declarations.
     */
    private Pattern pattern;
    /**
     * Matcher for evaluating patterns against each line.
     */
    private Matcher matcher;

    /**
     * Counts the number of class declarations in the provided Java file.
     *
     * @param javaFile The Java file to analyze.
     * @return The number of class declarations found in the file.
     */
    @Override
    public int count(JavaFile javaFile) {
        List<String> lines = javaFile.getLines();
        int classCount = 0;
        CommentValidator validator = new CommentValidator();
        
        for (String line : lines) {
            if (validator.isComment(line)) {
                continue;
            } else if (isClassDeclaration(line)) {
                classCount++;
            }
        }
        return classCount;
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

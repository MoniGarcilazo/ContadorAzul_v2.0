package com.example;

import org.junit.jupiter.api.*;

import com.example.exceptions.FileException;

import java.io.*;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MethodLineCounterTest {

    private MethodLineCounter methodLineCounter;
    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        methodLineCounter = new MethodLineCounter();
        // Crear un directorio temporal para cada prueba
        tempDir = Files.createTempDirectory("testMethodLineCounter");
    }

    @AfterEach
    void tearDown() throws IOException {
        // Eliminar el directorio temporal después de cada prueba
        if (tempDir != null) {
            Files.walk(tempDir)
                .map(Path::toFile)
                .forEach(File::delete);
        }
    }

    @Test
    void testCount_NoMethods() throws IOException, FileException {
        Path javaFilePath = createJavaFile("Example1.java", List.of(
                "public class TestClass {",
                "    private int x;",
                "    // No methods here",
                "}"
        ));

        JavaFile javaFile = new JavaFile(javaFilePath.toString(), javaFilePath.getFileName().toString());

        int result = methodLineCounter.count(javaFile);
        assertEquals(0, result);
    }

    @Test
    void testCount_SingleMethod() throws IOException, FileException {
        Path javaFilePath = createJavaFile("Example2.java", List.of(
                "public class TestClass {",
                "    public void myMethod() { }",
                "}"
        ));

        JavaFile javaFile = new JavaFile(javaFilePath.toString(), javaFilePath.getFileName().toString());

        int result = methodLineCounter.count(javaFile);
        assertEquals(1, result);
    }

    @Test
    void testCount_MultipleMethods() throws IOException, FileException {
        Path javaFilePath = createJavaFile("Example3.java", List.of(
                "public class TestClass {",
                "    public void methodOne() { }",
                "    private int methodTwo(int x) { return x; }",
                "}"
        ));

        JavaFile javaFile = new JavaFile(javaFilePath.toString(), javaFilePath.getFileName().toString());

        int result = methodLineCounter.count(javaFile);
        assertEquals(2, result);
    }

    @Test
    void testCount_CommentedMethod() throws IOException, FileException {
        Path javaFilePath = createJavaFile("Example4.java", List.of(
                "public class TestClass {",
                "    // public void commentedMethod() { }",
                "    public void actualMethod() { }",
                "}"
        ));

        JavaFile javaFile = new JavaFile(javaFilePath.toString(), javaFilePath.getFileName().toString());

        int result = methodLineCounter.count(javaFile);
        assertEquals(1, result);
    }

    /**
     * Helper method to create a temporary Java file for testing.
     */
    private Path createJavaFile(String fileName, List<String> lines) throws IOException {
        Path javaFilePath = tempDir.resolve(fileName);
        Files.write(javaFilePath, lines);
        return javaFilePath;
    }
}

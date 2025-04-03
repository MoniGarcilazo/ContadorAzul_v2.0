package com.example;

import org.junit.jupiter.api.*;

import com.example.exceptions.FileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassAnalyzerTest {

    private ClassAnalyzer classAnalyzer;
    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        classAnalyzer = new ClassAnalyzer();
        // Crear un directorio temporal para cada prueba
        tempDir = Files.createTempDirectory("testClassAnalyzer");
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
    void testAnalyze_SingleClass() throws IOException, FileException {
        // Crear un archivo Java de ejemplo
        Path javaFilePath = createJavaFile("TestClass.java", List.of(
                "public class TestClass {",
                "    private int x;",
                "    public void myMethod() { }",
                "    public int anotherMethod() { return 0; }",
                "}"));

        JavaFile javaFile = new JavaFile(javaFilePath.toString(), javaFilePath.getFileName().toString());

        // Analizar el archivo
        List<ClassInfo> classInfoList = classAnalyzer.analyze(javaFile);

        // Verificar que se analice correctamente
        assertEquals(1, classInfoList.size());
        ClassInfo classInfo = classInfoList.get(0);
        assertEquals("TestClass", classInfo.getClassName());
        assertEquals(2, classInfo.getMethodCount()); // Dos métodos en la clase
        assertEquals(5, classInfo.getPhysicalLines()); // 5 líneas físicas
    }

    private Path createJavaFile(String fileName, List<String> lines) throws IOException {
        Path javaFilePath = tempDir.resolve(fileName);
        Files.write(javaFilePath, lines);
        return javaFilePath;
    }
}

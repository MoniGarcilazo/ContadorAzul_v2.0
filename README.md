# Contador

Este proyecto consiste en un programa que cuenta las líneas lógicas y las líneas físicas en archivos de código fuente, omitiendo comentarios y líneas en blanco.

## Requisitos

- Java 17 o superior  
- Maven para la construcción y empaquetado del proyecto (si deseas compilar desde el código fuente)  
- Para ejecutar el archivo `.exe`, se requiere sistema operativo **Windows**  
- Para ejecutar el archivo `.jar`, se puede usar **Windows**, **macOS** o **Linux**

## Archivos disponibles

- `Contador-v2.0.exe`: ejecutable para Windows (no requiere Java)  
- `Contador-v2.0.jar`: ejecutable multiplataforma (requiere Java instalado)

## Instalación y Ejecución

### 1. Clona el repositorio (opcional si ya tienes los archivos)
```sh
git clone <URL_DEL_REPOSITORIO>
cd demo
```

### 2. Compila con Maven (opcional si ya tienes los ejecutables)
```sh
mvn clean install
```

## Ejecución en macOS o Windows (usando `Contador-v2.0.jar`)

1. Asegúrate de tener Java instalado (versión 11 o superior).

2. Abre la terminal y navega al directorio donde está el archivo:
```sh
cd <ruta_al_directorio>
```

3. Ejecuta el JAR:
```sh
java -jar Contador-v2.0.jar
```

## Ejecución en Windows (usando `Contador-v2.0.exe`)

1. Navega al directorio donde se encuentra `Contador-v2.0.exe`.

2. Haz doble clic sobre el archivo o ejecútalo desde la línea de comandos:
```sh
cd <ruta_al_directorio>
Contador-v2.0.exe
```

> Este archivo no requiere tener Java instalado, ya que está empaquetado como ejecutable nativo para Windows.

## Pruebas (si estás desarrollando o modificando el código)

Para ejecutar las pruebas:
```sh
mvn test
```

## Autores

- Aaron Isaac Graniel Arzat  
- Fernando Joachín Prieto  
- David Peña Muñoz  
- Jose Luis Pooc Moo  
- Andrea Isabel Torres Perez
- Josué Israel Canul Ordoñez
- Mónica Garcilazo Cuevas
- José Carlos Leo Fernández
- Endrick Alfredo Pool Flores
- Samuel David Rodríguez Coral


  

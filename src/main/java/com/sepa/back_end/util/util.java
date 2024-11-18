package com.sepa.back_end.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Archivo que ayuda con la creacion del proyecto creando algunos archivos necesarios como el indice de la factura
 */
public class util {
    // Función para crear un archivo y escribir '0' en él si no existe.
    public static void createFileWithZero(String filePath) throws IOException {
        // Verificar si el archivo ya existe
        if (!Files.exists(Paths.get(filePath))) {
            // Si no existe, crear el archivo con el número 0
            Files.write(Paths.get(filePath), "0".getBytes(), StandardOpenOption.CREATE_NEW);
            System.out.println("Archivo creado con el número 0.");
        } else {
            System.out.println("El archivo ya existe.");
        }
    }

    public static void main(String[] args) {
        String filePath = "numero.txt";  // Cambia esto con la ruta de tu archivo

        try {
            createFileWithZero(filePath);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo: " + e.getMessage());
        }
    }
}


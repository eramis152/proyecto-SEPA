package com.sepa.back_end.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sepa.back_end.entities.Transaction;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @PostMapping
    public Transaction emitTransaction(@RequestBody Transaction transaction) {

        // Guardar la transacción en un archivo
        saveTransactionToFile(transaction);
        System.out.println("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡NUEVA TRANSACCION!!!!!!!!!!!!!!!!");
        System.out.println("Nombre compañia: " + transaction.getNameCompany());
        System.out.println("IBAN empresa: "+transaction.getIbanCompany());
        System.out.println("BIC Empresa: "+transaction.getNameCompany());
        System.out.println("Nombre cliente: " +transaction.getNameClient());
        System.out.println("IBAN cliente:" +transaction.getIbanClient());
        System.out.println("BIC cliente: " +transaction.getBicClient());
        System.out.println("Dinero: " +transaction.getAmount());
        System.out.println("Fecha: " +transaction.getDate());
        return transaction;
    }

    private void saveTransactionToFile(Transaction transaction) {

        String filePath = "C:\\Users\\sergi\\BorjaMoll\\transacciones.txt";  // Especifica el archivo en Documentos
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // 'true' para añadir al archivo
            writer.write(transaction.toString()); // Guarda la representación legible de la transacción
            writer.newLine(); // Añade nueva línea para la próxima transacción
        } catch (IOException e) {
            e.printStackTrace(); // Manejar excepciones adecuadamente
        }
    }
    
    

}

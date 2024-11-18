package com.sepa.back_end.controllers;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sepa.back_end.entities.Transaction;
import com.sepa.back_end.util.XmlGenerator;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/transaction")

public class TransactionController {

    /**
     * Guarda la transacción y genera un archivo XML correspondiente a la misma
     * 
     * @param transaction La transacción que contiene toda la información relevante 
     *                    de la operacion, incluyendo datos de la empresa, cliente,
     *                    monto y fechas de la transaccion
     * @return La transaccion recibida después de haber sido guardada y procesada
     */
    @PostMapping
    public Transaction emitTransaction(@RequestBody Transaction transaction) {

        saveTransactionToFile(transaction);
        
        // Generar XML después de guardar la transacción
        XmlGenerator xmlGenerator = new XmlGenerator(transaction);
        xmlGenerator.generateXML(); // Llamar al método para generar el XML

        System.out.println("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡NUEVA TRANSACCION!!!!!!!!!!!!!!!!");
        System.out.println("Nombre compañia: " + transaction.getNameCompany());
        System.out.println("IBAN empresa: " + transaction.getIbanCompany());
        System.out.println("BIC Empresa: " + transaction.getBicCompany());
        System.out.println("Nombre cliente: " + transaction.getNameClient());
        System.out.println("IBAN cliente: " + transaction.getIbanClient());
        System.out.println("BIC cliente: " + transaction.getBicClient());
        System.out.println("Dinero: " + transaction.getAmount());
        System.out.println("Fecha: " + transaction.getDateV1());
        System.out.println("Fecha: " + transaction.getDateV2());
        
        return transaction;
    }


    /**
     * Genera el archivo donde se guarda la informacion de la transaccion
     * 
     * @param transaction La transaccion que sera guardada en el archivo con toda la 
     *                    informacion relevante para el xml
     */
    private void saveTransactionToFile(Transaction transaction) {
        String filePath = "nada";  // Especifica el archivo en Documentos
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // 'true' para añadir al archivo
            writer.write(transaction.toString()); // Guarda la representación legible de la transacción
            writer.newLine(); // Añade nueva línea para la próxima transacción
        } catch (IOException e) {}
    }
}
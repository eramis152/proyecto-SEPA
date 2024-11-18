package com.sepa.back_end.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa la entidad Transaction que genera una tabla en la base de datos para almacenar 
 * la informacion del formulario de transacciones en la web. 
 * Incluye datos sobre la empresa y el cliente, como el nombre, IBAN, BIC, y detalles de la transaccion.
 */
@Entity
public class Transaction {
    
    /** Identificador unico de la transaccion, generado automaticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    Long id;

    /** Nombre de la compañia que realiza la transaccion. */
    @Getter @Setter
    String nameCompany;

    /** IBAN de la compañia que realiza la transaccion. */
    @Getter @Setter
    String ibanCompany;

    /** BIC de la compañia que realiza la transaccion. */
    @Getter @Setter
    String bicCompany;

    /** Nombre del cliente que recibe la transaccion. */
    @Getter @Setter
    String nameClient;

    /** IBAN del cliente que recibe la transaccion. */
    @Getter @Setter
    String ibanClient;

    /** BIC del cliente que recibe la transaccion. */
    @Getter @Setter
    String bicClient;

    /** Monto de la transaccion. */
    @Getter @Setter
    String amount;

    /** Primera fecha asociada a la transaccion (por ejemplo, fecha de emision). */
    @Getter @Setter
    String dateV1;

    /** Segunda fecha asociada a la transaccion (por ejemplo, fecha de vencimiento). */
    @Getter @Setter
    String dateV2;

    /**
     * Convierte los datos de la transaccion en un formato de texto legible.
     *
     * @return una representacion en texto de los detalles de la transaccion.
     */
    @Override
    public String toString() {
        return "id=" + id +
                ", name company=" + nameCompany +
                ", iban company=" + ibanCompany +
                ", bic company=" + bicCompany +
                ", name client=" + nameClient +
                ", iban client=" + ibanClient +
                ", bic client=" + bicClient +
                ", cantidad=" + amount + "€" +
                ", fecha='" + dateV1 + ' ' + dateV2 + '\'';
    }
}

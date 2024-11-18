package com.sepa.back_end.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.sepa.back_end.entities.Transaction;

/**
 * Clase para generar archivos XML basados en la información de transacciones.
 * Permite modificar fechas, actualizar el número de factura, y crear archivos XML
 * con un formato compatible con el estándar SEPA.
 */
public class XmlGenerator {

    /** La transacción a utilizar para generar el XML */
    private final Transaction transaction;

    /**
     * Constructor que inicializa la clase con una transacción dada.
     *
     * @param transaction la transacción que se usará para generar el XML.
     */
    public XmlGenerator(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * Reemplaza las barras (/) por guiones (-) en la fecha para cumplir con el formato XML.
     *
     * @param input la cadena de fecha en formato original.
     * @return la cadena de fecha con guiones en lugar de barras.
     */
    public static String replaceSlashWithDash(String input) {
        return input.replace('/', '-');
    }

    /**
     * Actualiza el número en el archivo dado incrementándolo en uno, y devuelve el nuevo valor.
     *
     * @param filePath la ruta del archivo donde se encuentra el número.
     * @return el nuevo número actualizado.
     * @throws IOException si ocurre un error al leer o escribir en el archivo.
     */
    public static int updateNumberInFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        int number = Integer.parseInt(content.trim());
        number += 1;
        Files.write(Paths.get(filePath), String.valueOf(number).getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        return number;
    }

    /**
     * Modifica un número para que tenga un formato compatible con el estándar ISO.
     * Agrega ceros al principio del número para que tenga al menos seis dígitos.
     *
     * @param number el número a formatear.
     * @return el número formateado como una cadena con seis dígitos.
     */
    public static String modifyString(int number) {
        String baseString = "PRE201800000";
        String paddedNumber = String.format("%06d", number);
        return baseString.substring(0, baseString.length() - 6) + paddedNumber;
    }

    /**
     * Genera un archivo XML utilizando la información de la transacción.
     */
    public void generateXML() {
        int factureID = 0;
        try {
            factureID = updateNumberInFile("numero.txt");
        } catch (IOException e) {
            System.out.println("Error al guardar la base: " + e.getMessage());
        }

        String DateV2 = replaceSlashWithDash(transaction.getDateV2());
        String DateV1 = replaceSlashWithDash(transaction.getDateV1());

        // Variables personalizadas para el contenido del XML
        String msgId = String.valueOf(factureID);
        String creDtTm = DateV2;
        String nbOfTxs = "1";
        String ctrlSum = transaction.getAmount();
        String nombreIniciador = transaction.getNameCompany();
        String identificador = "TU_IDENTIFICADOR";
        String pmtInfId = "ES****";
        String ctrlSumPmtInf = transaction.getAmount();
        String ibanCreditor = transaction.getIbanCompany();
        String bic = transaction.getBicCompany();
        String nombreDebtor = transaction.getNameClient();
        String ibanDebtor = transaction.getIbanClient();
        String mandatoId = "41023";
        String fechaFirma = DateV1;

        // Genera el archivo XML con los valores personalizados
        crearArchivoXMLSEPA(".\\xml\\info.xml", msgId, creDtTm, nbOfTxs, ctrlSum, nombreIniciador, identificador,
                pmtInfId, ctrlSumPmtInf, ibanCreditor, bic, nombreDebtor, ibanDebtor, mandatoId, fechaFirma);
    }

    /**
     * Punto de entrada para ejecutar el generador de XML con una transacción de prueba.
     *
     * @param args argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        Transaction transaction = new Transaction(); // Crear o recibir la transacción
        XmlGenerator generator = new XmlGenerator(transaction);
        generator.generateXML(); // Usar el método de generación de XML
    }

    /**
     * Crea y guarda un archivo XML SEPA basado en los parámetros proporcionados.
     *
     * @param nombreArchivo   la ruta donde se guardará el archivo XML.
     * @param msgId           el ID del mensaje.
     * @param creDtTm         la fecha y hora de creación del mensaje.
     * @param nbOfTxs         el número de transacciones.
     * @param ctrlSum         el total de la suma de las transacciones.
     * @param nombreIniciador el nombre de la entidad que inicia la transacción.
     * @param identificador   un identificador único.
     * @param pmtInfId        el ID de la información de pago.
     * @param ctrlSumPmtInf   el total de la suma de las transacciones en la información de pago.
     * @param ibanCreditor    el IBAN del acreedor.
     * @param bic             el BIC del acreedor.
     * @param nombreDebtor    el nombre del deudor.
     * @param ibanDebtor      el IBAN del deudor.
     * @param mandatoId       el ID del mandato de la transacción.
     * @param fechaFirma      la fecha de firma del mandato.
     */
    public static void crearArchivoXMLSEPA(String nombreArchivo, String msgId, String creDtTm, String nbOfTxs,
                                           String ctrlSum, String nombreIniciador, String identificador,
                                           String pmtInfId, String ctrlSumPmtInf, String ibanCreditor,
                                           String bic, String nombreDebtor, String ibanDebtor,
                                           String mandatoId, String fechaFirma) {

        // Contenido XML basado en los parámetros de la transacción
        String contenidoXML = "<Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:pain.008.001.02\">\n" +
                "    <CstmrDrctDbtInitn>\n" +
                "        <GrpHdr>\n" +
                "            <MsgId>%s</MsgId>\n" +
                "            <CreDtTm>%s</CreDtTm>\n" +
                "            <NbOfTxs>%s</NbOfTxs>\n" +
                "            <CtrlSum>%s</CtrlSum>\n" +
                "            <InitgPty>\n" +
                "                <Nm>%s</Nm>\n" +
                "                <Id>\n" +
                "                    <PrvtId>\n" +
                "                        <Othr>\n" +
                "                            <Id>%s</Id>\n" +
                "                        </Othr>\n" +
                "                    </PrvtId>\n" +
                "                </Id>\n" +
                "            </InitgPty>\n" +
                "        </GrpHdr>\n" +
                "        <PmtInf>\n" +
                "            <PmtInfId>%s</PmtInfId>\n" +
                "            <PmtMtd>DD</PmtMtd>\n" +
                "            <BtchBookg>true</BtchBookg>\n" +
                "            <NbOfTxs>1</NbOfTxs>\n" +
                "            <CtrlSum>%s</CtrlSum>\n" +
                "            <PmtTpInf>\n" +
                "                <SvcLvl>\n" +
                "                    <Cd>SEPA</Cd>\n" +
                "                </SvcLvl>\n" +
                "                <LclInstrm>\n" +
                "                    <Cd>CORE</Cd>\n" +
                "                </LclInstrm>\n" +
                "                <SeqTp>RCUR</SeqTp>\n" +
                "            </PmtTpInf>\n" +
                "            <ReqdColltnDt>2018-12-10</ReqdColltnDt>\n" +
                "            <Cdtr>\n" +
                "                <Nm>%s</Nm>\n" +
                "            </Cdtr>\n" +
                "            <CdtrAcct>\n" +
                "                <Id>\n" +
                "                    <IBAN>%s</IBAN>\n" +
                "                </Id>\n" +
                "            </CdtrAcct>\n" +
                "            <CdtrAgt>\n" +
                "                <FinInstnId>\n" +
                "                    <BIC>%s</BIC>\n" +
                "                </FinInstnId>\n" +
                "            </CdtrAgt>\n" +
                "            <CdtrSchmeId>\n" +
                "                <Id>\n" +
                "                    <PrvtId>\n" +
                "                        <Othr>\n" +
                "                            <Id>11223344A</Id>\n" +
                "                            <SchmeNm>\n" +
                "                                <Prtry>SEPA</Prtry>\n" +
                "                            </SchmeNm>\n" +
                "                        </Othr>\n" +
                "                    </PrvtId>\n" +
                "                </Id>\n" +
                "            </CdtrSchmeId>\n" +
                "            <DrctDbtTxInf>\n" +
                "                <PmtId>\n" +
                "                    <EndToEndId>2-*******</EndToEndId>\n" +
                "                </PmtId>\n" +
                "                <InstdAmt Ccy=\"EUR\">100.00</InstdAmt>\n" +
                "                <DrctDbtTx>\n" +
                "                    <MndtRltdInf>\n" +
                "                        <MndtId>%s</MndtId>\n" +
                "                        <DtOfSgntr>%s</DtOfSgntr>\n" +
                "                        <AmdmntInd>true</AmdmntInd>\n" +
                "                        <AmdmntInfDtls>\n" +
                "                            <OrgnlCdtrSchmeId>\n" +
                "                                <Nm>%s</Nm>\n" +
                "                            </OrgnlCdtrSchmeId>\n" +
                "                        </AmdmntInfDtls>\n" +
                "                    </MndtRltdInf>\n" +
                "                </DrctDbtTx>\n" +
                "                <DbtrAgt>\n" +
                "                    <FinInstnId/>\n" +
                "                </DbtrAgt>\n" +
                "                <Dbtr>\n" +
                "                    <Nm>%s</Nm>\n" +
                "                    <Id>\n" +
                "                        <PrvtId>\n" +
                "                            <Othr>\n" +
                "                                <Id>11223344A</Id>\n" +
                "                                <Issr>DNI</Issr>\n" +
                "                            </Othr>\n" +
                "                        </PrvtId>\n" +
                "                    </Id>\n" +
                "                </Dbtr>\n" +
                "                <DbtrAcct>\n" +
                "                    <Id>\n" +
                "                        <IBAN>%s</IBAN>\n" +
                "                    </Id>\n" +
                "                </DbtrAcct>\n" +
                "                <RmtInf>\n" +
                "                    <Ustrd>000000001</Ustrd>\n" +
                "                </RmtInf>\n" +
                "            </DrctDbtTxInf>\n" +
                "        </PmtInf>\n" +
                "    </CstmrDrctDbtInitn>\n" +
                "</Document>";

        contenidoXML = String.format(contenidoXML, msgId, creDtTm, nbOfTxs, ctrlSum, nombreIniciador, identificador,
                pmtInfId, ctrlSumPmtInf, nombreIniciador, ibanCreditor, bic, mandatoId, fechaFirma, nombreIniciador,
                nombreDebtor, ibanDebtor);

        try (FileWriter fileWriter = new FileWriter(nombreArchivo)) {
            fileWriter.write(contenidoXML);
            System.out.println("Archivo XML creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo XML: " + e.getMessage());
        }
    }
}
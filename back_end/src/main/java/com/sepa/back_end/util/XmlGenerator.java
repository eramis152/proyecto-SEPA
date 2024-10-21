package com.sepa.back_end.util;

import com.sepa.back_end.entities.Transaction;

import java.io.FileWriter;
import java.io.IOException;

public class XmlGenerator {

    private Transaction transaction;

    public XmlGenerator(Transaction transaction) {
        this.transaction = transaction;
    }

    public void generateXML() {

        // Variables dinámicas para los valores que deben ser personalizados
        String msgId = "PRE2018*****";
        String creDtTm = "2024-10-18T14:30:00"; // Acceso a la fecha desde la transacción
        String nbOfTxs = "1";
        String ctrlSum = transaction.getAmount(); // Ahora como cadena de texto
        String nombreIniciador = transaction.getNameCompany();
        String identificador = "TU_IDENTIFICADOR";
        String pmtInfId = "ES****";
        String ctrlSumPmtInf = transaction.getAmount(); // Ahora como cadena de texto
        String ibanCreditor = transaction.getIbanCompany();
        String bic = transaction.getBicCompany();
        String nombreDebtor = transaction.getNameClient();
        String ibanDebtor = transaction.getIbanClient();
        String mandatoId = "41023";
        String fechaFirma = "2018-12-10";

        // Llamada a la función con los valores personalizados
        crearArchivoXMLSEPA("..\\xml\\info.xml", msgId, creDtTm, nbOfTxs, ctrlSum, nombreIniciador, identificador,
                pmtInfId, ctrlSumPmtInf, ibanCreditor, bic, nombreDebtor, ibanDebtor, mandatoId, fechaFirma);
    }

    public static void main(String[] args) {
        Transaction transaction = new Transaction(); // Crear o recibir la transacción
        XmlGenerator2 generator = new XmlGenerator2(transaction);
        generator.generarXml(); // Usar el método no estático
    }

    public static void crearArchivoXMLSEPA(String nombreArchivo, String msgId, String creDtTm, String nbOfTxs,
                                           String ctrlSum, String nombreIniciador, String identificador,
                                           String pmtInfId, String ctrlSumPmtInf, String ibanCreditor,
                                           String bic, String nombreDebtor, String ibanDebtor,
                                           String mandatoId, String fechaFirma) {

        // Contenido XML con todos los números gestionados como cadenas de texto
        String contenidoXML = """
        <Document xmlns="urn:iso:std:iso:20022:tech:xsd:pain.008.001.02">
            <CstmrDrctDbtInitn>
                <GrpHdr>
                    <MsgId>%s</MsgId>
                    <CreDtTm>%s</CreDtTm>
                    <NbOfTxs>%s</NbOfTxs>
                    <CtrlSum>%s</CtrlSum>
                    <InitgPty>
                        <Nm>%s</Nm>
                        <Id>
                            <PrvtId>
                                <Othr>
                                    <Id>%s</Id>
                                </Othr>
                            </PrvtId>
                        </Id>
                    </InitgPty>
                </GrpHdr>
                <PmtInf>
                    <PmtInfId>%s</PmtInfId>
                    <PmtMtd>DD</PmtMtd>
                    <BtchBookg>true</BtchBookg>
                    <NbOfTxs>1</NbOfTxs>
                    <CtrlSum>%s</CtrlSum>
                    <PmtTpInf>
                        <SvcLvl>
                            <Cd>SEPA</Cd>
                        </SvcLvl>
                        <LclInstrm>
                            <Cd>CORE</Cd>
                        </LclInstrm>
                        <SeqTp>RCUR</SeqTp>
                    </PmtTpInf>
                    <ReqdColltnDt>2018-12-10</ReqdColltnDt>
                    <Cdtr>
                        <Nm>%s</Nm>
                    </Cdtr>
                    <CdtrAcct>
                        <Id>
                            <IBAN>%s</IBAN>
                        </Id>
                    </CdtrAcct>
                    <CdtrAgt>
                        <FinInstnId>
                            <BIC>%s</BIC>
                        </FinInstnId>
                    </CdtrAgt>
                    <CdtrSchmeId>
                        <Id>
                            <PrvtId>
                                <Othr>
                                    <Id>11223344A</Id>
                                    <SchmeNm>
                                        <Prtry>SEPA</Prtry>
                                    </SchmeNm>
                                </Othr>
                            </PrvtId>
                        </Id>
                    </CdtrSchmeId>
                    <DrctDbtTxInf>
                        <PmtId>
                            <EndToEndId>2-*******</EndToEndId>
                        </PmtId>
                        <InstdAmt Ccy="EUR">100.00</InstdAmt>
                        <DrctDbtTx>
                            <MndtRltdInf>
                                <MndtId>%s</MndtId>
                                <DtOfSgntr>%s</DtOfSgntr>
                                <AmdmntInd>true</AmdmntInd>
                                <AmdmntInfDtls>
                                    <OrgnlCdtrSchmeId>
                                        <Nm>%s</Nm>
                                    </OrgnlCdtrSchmeId>
                                </AmdmntInfDtls>
                            </MndtRltdInf>
                        </DrctDbtTx>
                        <DbtrAgt>
                            <FinInstnId/>
                        </DbtrAgt>
                        <Dbtr>
                            <Nm>%s</Nm>
                            <Id>
                                <PrvtId>
                                    <Othr>
                                        <Id>11223344A</Id>
                                        <Issr>DNI</Issr>
                                    </Othr>
                                </PrvtId>
                            </Id>
                        </Dbtr>
                        <DbtrAcct>
                            <Id>
                                <IBAN>%s</IBAN>
                            </Id>
                        </DbtrAcct>
                        <RmtInf>
                            <Ustrd>000000001</Ustrd>
                        </RmtInf>
                    </DrctDbtTxInf>
                </PmtInf>
            </CstmrDrctDbtInitn>
        </Document>
        """.formatted(msgId, creDtTm, nbOfTxs, ctrlSum, nombreIniciador, identificador, pmtInfId, ctrlSumPmtInf,
                nombreIniciador, ibanCreditor, bic, mandatoId, fechaFirma, nombreIniciador, nombreDebtor, ibanDebtor);

        try (FileWriter fileWriter = new FileWriter(nombreArchivo)) {
            fileWriter.write(contenidoXML);
            System.out.println("Archivo XML creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo XML: " + e.getMessage());
        }
    }
}
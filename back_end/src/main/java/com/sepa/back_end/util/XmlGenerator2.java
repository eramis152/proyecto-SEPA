package com.sepa.back_end.util;

import com.sepa.back_end.entities.Transaction;

import java.io.FileWriter;
import java.io.IOException;

public class XmlGenerator2 {

    private Transaction transaction;

    public XmlGenerator2(Transaction transaction) {
        this.transaction = transaction;
    }

    public void generarXml() {
        

        // Variables dinámicas para los valores que deben ser personalizados
        String msgId = "PRE2018*****";
        String creDtTm = "2018-12-10"; // Acceso a la fecha desde la transacción
        String nbOfTxs = "1";
        String ctrlSum = transaction.getAmount(); // Ahora como cadena de texto
        String nombreIniciador = "Elm Enterprice";
        String identificador = "TU_IDENTIFICADOR";
        String pmtInfId = "ES****";
        String ctrlSumPmtInf = "100.00"; // Ahora como cadena de texto
        String ibanCreditor = "ES6000491500051234567892";
        String bic = "BBVAESMMXXX";
        String nombreDebtor = "Elm Ramis";
        String ibanDebtor = "ES6000491500051234567890";
        String mandatoId = "41023";
        String fechaFirma = "2018-12-10";

        // Llamada a la función con los valores personalizados
        crearArchivoXMLSEPA(".\\xml\\info.xml", msgId, creDtTm, nbOfTxs, ctrlSum, nombreIniciador, identificador,
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

/*
package com.sepa.back_end.util;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sepa.back_end.entities.Transaction;

public class XmlGenerator {

    private Transaction transaction;

    public XmlGenerator(Transaction transaction) {
        this.transaction = transaction;
    }

    public void generateXML() {
        try {
            // Crear un documento vacío
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
    
            // Crear el nodo raíz <Document>
            Element documentElement = doc.createElement("Document");
            documentElement.setAttribute("xmlns", "urn:iso:std:iso:20022:tech:xsd:pain.001.001.03");
            doc.appendChild(documentElement);
    
            // Crear el nodo <CstmrCdtTrfInitn>
            Element cstmrCdtTrfInitn = doc.createElement("CstmrCdtTrfInitn");
            documentElement.appendChild(cstmrCdtTrfInitn);
    
            // Crear el nodo <GrpHdr>
            Element grpHdr = doc.createElement("GrpHdr");
            cstmrCdtTrfInitn.appendChild(grpHdr);
    
            // Usar los datos de la transacción
            createElement(doc, grpHdr, "MsgId", "123456"); // Cambiar según tus necesidades
            createElement(doc, grpHdr, "CreDtTm", transaction.getDate()); // Utilizar la fecha de la transacción
            createElement(doc, grpHdr, "NbOfTxs", "1"); // Solo un pago
            createElement(doc, grpHdr, "CtrlSum", String.valueOf(transaction.getAmount())); // Utilizar el monto de la transacción
    
            // Crear el nodo <InitgPty> dentro de <GrpHdr>
            Element initgPty = doc.createElement("InitgPty");
            grpHdr.appendChild(initgPty);
            createElement(doc, initgPty, "Nm", transaction.getNameCompany()); // Nombre de la compañía
    
            // Crear el nodo <PmtInf>
            Element pmtInf = doc.createElement("PmtInf");
            cstmrCdtTrfInitn.appendChild(pmtInf);
    
            // Usar los datos de la transacción
            createElement(doc, pmtInf, "PmtInfId", "PmtInf123"); // ID de información de pago
            createElement(doc, pmtInf, "PmtMtd", "TRF"); // Método de pago
            createElement(doc, pmtInf, "ReqdExctnDt", transaction.getDate()); // Fecha de ejecución requerida
    
            // Crear el nodo <Dbtr> dentro de <PmtInf>
            Element dbtr = doc.createElement("Dbtr");
            pmtInf.appendChild(dbtr);
            createElement(doc, dbtr, "Nm", transaction.getNameClient()); // Nombre del cliente
    
            // Crear el nodo <DbtrAcct> dentro de <Dbtr>
            Element dbtrAcct = doc.createElement("DbtrAcct");
            dbtr.appendChild(dbtrAcct);
    
            // Crear el nodo <Id> dentro de <DbtrAcct>
            Element dbtrAcctId = doc.createElement("Id");
            dbtrAcct.appendChild(dbtrAcctId);
            createElement(doc, dbtrAcctId, "IBAN", transaction.getIbanClient()); // IBAN del cliente
    
            // Crear el nodo <DbtrAgt> dentro de <Dbtr>
            Element dbtrAgt = doc.createElement("DbtrAgt");
            dbtr.appendChild(dbtrAgt);
    
            // Crear el nodo <FinInstnId> dentro de <DbtrAgt>
            Element dbtrAgtFinInstnId = doc.createElement("FinInstnId");
            dbtrAgt.appendChild(dbtrAgtFinInstnId);
            createElement(doc, dbtrAgtFinInstnId, "BIC", transaction.getBicClient()); // BIC del cliente
    
            // Crear el nodo <Cdtr> dentro de <PmtInf>
            Element cdtr = doc.createElement("Cdtr");
            pmtInf.appendChild(cdtr);
            createElement(doc, cdtr, "Nm", transaction.getNameCompany()); // Nombre de la compañía
    
            // Crear el nodo <CdtrAcct> dentro de <Cdtr>
            Element cdtrAcct = doc.createElement("CdtrAcct");
            cdtr.appendChild(cdtrAcct);
    
            // Crear el nodo <Id> dentro de <CdtrAcct>
            Element cdtrAcctId = doc.createElement("Id");
            cdtrAcct.appendChild(cdtrAcctId);
            createElement(doc, cdtrAcctId, "IBAN", transaction.getIbanCompany()); // IBAN de la empresa
    
            // Crear el nodo <CdtrAgt> dentro de <Cdtr>
            Element cdtrAgt = doc.createElement("CdtrAgt");
            cdtr.appendChild(cdtrAgt);
    
            // Crear el nodo <FinInstnId> dentro de <CdtrAgt>
            Element cdtrAgtFinInstnId = doc.createElement("FinInstnId");
            cdtrAgt.appendChild(cdtrAgtFinInstnId);
            createElement(doc, cdtrAgtFinInstnId, "BIC", transaction.getBicCompany()); // BIC de la empresa
    
            // Crear el nodo <CdtTrfTxInf> dentro de <PmtInf>
            Element cdtTrfTxInf = doc.createElement("CdtTrfTxInf");
            pmtInf.appendChild(cdtTrfTxInf);
    
            // Crear el nodo <PmtId> dentro de <CdtTrfTxInf>
            Element pmtId = doc.createElement("PmtId");
            cdtTrfTxInf.appendChild(pmtId);
            createElement(doc, pmtId, "EndToEndId", "ETOE123456"); // ID de transacción
    
            // Crear el nodo <Amt> dentro de <CdtTrfTxInf>
            Element amt = doc.createElement("Amt");
            cdtTrfTxInf.appendChild(amt);
    
            // Crear el nodo <InstdAmt> con atributo dentro de <Amt>
            Element instdAmtElement = doc.createElement("InstdAmt");
            instdAmtElement.setAttribute("Ccy", "EUR"); // Establecer la moneda
            instdAmtElement.setTextContent(String.valueOf(transaction.getAmount())); // Monto de la transacción
            amt.appendChild(instdAmtElement);
    
            // Escribir el contenido en un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("..\\xml\\info.xml")); //a
            transformer.transform(source, result);
    
            System.out.println("Archivo XML generado correctamente!");
    
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
    private static void createElement(Document doc, Element parent, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }
}
 */
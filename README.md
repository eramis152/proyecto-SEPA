# Manual de Usuario  
**Aplicación:** Proyecto-SEPA  
**Versión:** 1.0  
**Fecha:** Noviembre 2024  

## Índice  
1. [Introducción](#1-introducción)  
2. [Requisitos del Sistema](#2-requisitos-del-sistema)  
3. [Estructura de carpetas](#3-estructura-de-carpetas)
4. [Instalación](#4-instalacion)  
5. [Validación del documento generado](#4-Validación-del-documento-generado)

---

## 1. Introducción  
La aplicación **Proyecto-SEPA** está diseñada para convertir datos adquiridos desde una página web externa a un XML generado desde una aplicación Java.  

**SE RECOMIENDA ENCARECIDAMENTE LA INSTALACIÓN DE TODAS LAS DEPENDENCIAS Y EL SEGUIMIENTO PASO POR PASO PARA EL CORRECTO FUNCIONAMIENTO DE LA APLICACIÓN.**

---

## 2. Requisitos del Sistema  
- **Sistema Operativo:** Windows 10 o superior, macOS 10.15 o superior, Linux.  
- **Espacio en Disco:** 301 MB.  
- **Java Runtime Environment (JRE):** Versión 17 o superior.  

---

## 3. Estructura de carpetas
- **doc:** Archivos HTML del JavaDoc
- **documentation:** Documentación del proyecto.
- **src/main/java/com/sepa:** Ruta principal, dentro de la misma se encuentran las carpetas de **front_end** y **back_end**, las cuales contienen toda la información relacionada con los mismos.
- **target:** Archivos necesarios para ejecutar el Back End ( backend.jar ).
- **xml:** Aquí se guardará el archivo xml generado por la aplicación.
- **otros archivos:** Archivos de instalación y otras dependencias.
  
---

## 4. Instalación  
1. Descargue el proyecto desde el link de GitHub.  
2. Instale las dependencias de Node (archivo `z1_node_instalation`):  
   - Simplemente pulse *Siguiente* en todos los pasos del instalador.  
3. Instale las dependencias de Spring Boot (archivo `z2_install_dependences`).  
4. Ejecute el Back End (archivo `z3_start_back`):  
   - Procure no cerrar la terminal, ya que de hacerlo finalizará el proceso.  
5. Ejecute el Front End (archivo `z4_start_front`):  
   - Procure no cerrar la terminal, ya que de hacerlo finalizará el proceso.  

---

## 5. Validación del documento generado  
Para validar el documento generado ( en la carpeta XML/info.xml ) acceda a la [página para validación](https://www.lasosl.com/validacion-ficheros-sepa) y adjunte el archivo.  


# Manual de Usuario  
**Aplicación:** Proyecto-SEPA  
**Versión:** 1.0  
**Fecha:** Noviembre 2024  

## Índice  
1. [Introducción](#1-introducción)  
2. [Requisitos del Sistema](#2-requisitos-del-sistema)  
3. [Instalación](#3-instalación)  
4. [Navegación por la Aplicación](#4-navegación-por-la-aplicación)  
5. [Funcionalidades](#5-funcionalidades)  
   - [5.1 Creación de documento XML](#51-creación-de-documento-xml)  
6. [Configuración](#6-configuración)  
7. [Desinstalación de dependencias](#7-desinstalación-de-dependencias)  

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

## 3. Instalación  
1. Descargue el proyecto desde el link de GitHub.  
2. Instale las dependencias de Node (archivo `z_node_intalation`):  
   - Simplemente pulse *Siguiente* en todos los pasos del instalador.  
3. Instale las dependencias de Spring Boot (archivo `z_install_dependences`).  
4. Ejecute el Back End (archivo `z_start_back`):  
   - Procure no cerrar la terminal, ya que de hacerlo finalizará el proceso.  
5. Ejecute el Front End (archivo `z_start_front`):  
   - Procure no cerrar la terminal, ya que de hacerlo finalizará el proceso.  

---

## 4. Validación del documento generado  
Para validar el documento generado ( en la carpeta XML/info.xml ) acceda a la [página para validación](https://www.lasosl.com/validacion-ficheros-sepa) y adjunte el archivo.  



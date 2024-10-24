# Proyecto Angular - Instrucciones de Instalación

Una vez clonado el repositorio en tu maquina, este documento proporciona una guía paso a paso para instalar dependencias y ejecutar un proyecto Angular y Spring Boot en tu maquina.

## Requisitos previos

### Angular
- **Node.js** (versión 12.x o superior) y **npm** (administrador de paquetes de Node.js).
  
  - Puedes descargar Node.js y npm desde [aquí](https://nodejs.org/).
    
  - Para verificar si están instalados, ejecuta los siguientes comandos en el PowerShell:
    ```bash
    node -v
    npm -v
    ```
    
- **Angular CLI** (Interfaz de Línea de Comandos de Angular) instalado globalmente:
  - Si no lo tienes instalado, puedes hacerlo ejecutando:
    ```bash
    npm install -g @angular/cli
    ```

  - Si te salen errores en el proyecto angular despues de seguir estos pasos prueba lo siguiente dentro de la carpeta front_end:
    ```bash
    npm install
    ```

  - Por ultimo para lanzar el proyecto:
    ```bash
    ng serve
    ```
    cd src\main\java\com\sepa\front_end
### Spring Boot
- Como ejecutar y que extensiones descargar en VSCode:
  - El proyecto esta hecho con la version de Java 17, si usas otra version el programa no funcionara al ejecutarlo.

  - Extensiones: <br>
    ·Java Extension Pack. <br>
    ·Spring Boot Extension Pack. <br>
 
  - Por ultimo abre la extension de Spring Boot Dashboard y le das al play o "Run"





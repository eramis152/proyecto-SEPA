@echo off
REM Inicia el servidor de Angular
start cmd /k "cd src/main/java/com/sepa/front_end && ng serve"

java -jar cd src/main/java/com/sepa/back_end/BackEndApplication.java
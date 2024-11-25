@echo off

@REM Esta linea accede a la carpeta front_end, para iniciar el front_end.

cd src\main\java\com\sepa\front_end 

@REM Con este comando abre una nueva pestaña de cmd y ejecuta el comando para iniciar l front_end.

start cmd /k "ng serve"

@REM espera 5s antes de abrir el navegador con la pagina web del proyecto. 

timeout /t 5 >nul

start http://localhost:4200

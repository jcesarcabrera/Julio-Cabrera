@ECHO OFF
CLS
:MENU
CLS
ECHO ..................................................
ECHO EJECUCION DE CONTIGENCIA DE USOS Y NOVEDADES DAVIVIENDA
ECHO ..................................................
ECHO.
ECHO 1 - ENVIO DE USOS POR CONTINGENCIA AL BANCO
ECHO 2 - ENVIO DE NOVEDADES A FCS
ECHO 3 - ENVIO DE RESPUESTA DE NOVEDADES AL BANCO
ECHO 4 - SALIR
ECHO.
SET /P M=OPCIONES 1, 2, 3 o 4 SELECCIONE UNA OPCIONE Y PRESIONE LA TECLA ENTER: 
IF %M%==1 GOTO EUC
IF %M%==2 GOTO NVE
IF %M%==3 GOTO NVR
IF %M%==4 GOTO EOF

:EUC
CLS
ECHO SE ENVIARA EL MENSAJE PARA GENERAR EL ARCHIVO DE CONTINGENCIA DAVIVIENDA
SET "queuecontingencia=Davivienda_Transactions_Contingency"
SET "ems_server=tcp://localhost:7222"
SET "contingenciapath=C:\ArchivosRecaudo2\Usos\Contingencias\AEB03"
SET "EMSUser=admin"
SET /P message=Por favor indique la fecha a realizar la contingencia: 
java -jar tibjmsMsgProducer.jar -server %ems_server% -user %EMSUser% -queue %queuecontingencia% %message%
ECHO SE HA ENVIADO EL MENSAJE %message% A LA QUEUE %queuecontingencia% POR FAVOR REVISE EL TRACING EN EL ADMINISTRATOR PARA ASEGURAR LA EJECUCION CORRECTA DEL SERVICIO'
PAUSE
GOTO MENU

:NVE
java -jar tibjmsMsgProducer.jar -server tcp://localhost:7222 -user admin -queue hola.queue 20160809
PAUSE
GOTO MENU

:NVR
java -jar tibjmsMsgProducer.jar -server tcp://localhost:7222 -user admin -queue hola.queue 20160809
PAUSE
GOTO MENU
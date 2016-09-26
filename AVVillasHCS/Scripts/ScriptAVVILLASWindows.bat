@ECHO OFF
CLS
SET "ems_server=tcp://localhost:7222"
:MENU
CLS
ECHO ..................................................
ECHO EJECUCION DE CONTIGENCIA DE USOS Y NOVEDADES AV VILLAS
ECHO ..................................................
ECHO.
ECHO 1 - ENVIO DE USOS POR AL BANCO
ECHO 2 - ENVIO DE USOS POR CONTINGENCIA AL BANCO
ECHO 3 - ENVIO DE NOVEDADES A FCS
ECHO 4 - ENVIO DE RESPUESTA DE NOVEDADES AL BANCO
ECHO 5 - SALIR
ECHO.
SET /P M=OPCIONES 1, 2, 3 o 4 SELECCIONE UNA OPCIONE Y PRESIONE LA TECLA ENTER: 
IF %M%==1 GOTO EUB
IF %M%==2 GOTO EUC
IF %M%==3 GOTO NVE
IF %M%==4 GOTO NVR
IF %M%==5 GOTO EOF

:EUB
CLS
ECHO SE PETICION PARA GENERAR EL ARCHIVO DE USOS PARA AV VILLAS
SET "queueusos=AV_VILLAS_Transactions_Manual"
SET "usospath=C:\ArchivosRecaudo2\Usos\AEFile\AEFiles\"
SET "EMSUser=admin"
java -jar tibjmsMsgProducer.jar -server %ems_server% -user %EMSUser% -queue %queueusos% ""
ECHO SE HA ENVIADO LA PETICION DE FORMA MANUAL PARA GENERAR ARCHIVO DE USOS EN LA RUTA: %usospath% PARA EL BANCO AV VILLAS
PAUSE
GOTO MENU

:EUC
CLS
ECHO SE ENVIARA EL MENSAJE PARA GENERAR EL ARCHIVO DE CONTINGENCIA AV VILLAS
SET "queuecontingencia=AV_VILLAS_Transactions_Contingency"
SET "contingenciapath=C:\ArchivosRecaudo2\AVVillas\Contigencias\AEB01"
SET "EMSUser=admin"
SET /P message=Por favor indique la fecha a realizar la contingencia: 
java -jar tibjmsMsgProducer.jar -server %ems_server% -user %EMSUser% -queue %queuecontingencia% %message%
ECHO SE HA ENVIADO EL MENSAJE %message% A LA QUEUE %queuecontingencia% POR FAVOR REVISE EL TRACING EN EL ADMINISTRATOR PARA ASEGURAR LA EJECUCION CORRECTA DEL SERVICIO'
PAUSE
GOTO MENU

:NVE
ECHO SE HA SELECCIONADO ENVIAR NOVEDADES AL FCS
java -jar tibjmsMsgProducer.jar -server %ems_server% -user admin -queue ContingencyNoveltyVillas.Queue.Send ""
ECHO LAS NOVEDADES HAN SIDO ENVIADAS AL FCS
PAUSE
GOTO MENU

:NVR
ECHO SE HA SELECCIONADO ENVIAR RESPUESTA DE NOVEDADES AL BANCO AV VILLAS
java -jar tibjmsMsgProducer.jar -server %ems_server% -user admin -queue ContingencyNoveltyVillas.Queue.Reply ""
ECHO SE HA ENVIADO RESPUESTA DE NOVEDADES AL BANCO AV VILLAS
PAUSE
GOTO MENU
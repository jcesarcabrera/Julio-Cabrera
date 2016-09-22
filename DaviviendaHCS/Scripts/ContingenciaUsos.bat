@ECHO OFF
ECHO SE ENVIARA EL MENSAJE PARA GENERAR EL ARCHIVO DE CONTINGENCIA DAVIVIENDA
SET /p archivos=<C:\WorkSpaceRB\DaviviendaHCS\Scripts\archivos.txt
SET /p queuecontingencia=<%archivos%QueueContingencia.txt
SET /p jarlocation=<%archivos%jarlocation.txt
SET /p ems_server=<%archivos%EMS_SERVER.txt
SET /p contingenciapath=<%archivos%ContingenciaPathPrefix.txt
SET "EMSUser=admin"
SET /P message=Por favor indique la fecha a realizar la contingencia: 
java -jar %jarlocation% -server %ems_server% -user %EMSUser% -queue %queuecontingencia% %message%
ECHO SE HA ENVIADO EL MENSAJE %message% A LA QUEUE %queuecontingencia% POR FAVOR REVISE EL TRACING EN EL ADMINISTRATOR PARA ASEGURAR LA EJECUCION CORRECTA DEL SERVICIO'
PAUSE

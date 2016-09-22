@ECHO OFF
ECHO SE ENVIARA EL MENSAJE PARA GENERAR EL ARCHIVO DE CONTINGENCIA DAVIVIENDA
SET /p queuecontingencia=<C:\WorkSpaceRB\DaviviendaHCS\Scripts\QueueContingencia.txt
SET /p jarlocation=<C:\WorkSpaceRB\DaviviendaHCS\Scripts\jarlocation.txt
SET /p ems_server=<C:\WorkSpaceRB\DaviviendaHCS\Scripts\EMS_SERVER.txt
SET /p contingenciapath=<C:\WorkSpaceRB\DaviviendaHCS\Scripts\ContingenciaPathPrefix.txt
SET "EMSUser=admin"
SET /P message=Por favor indique la fecha a realizar la contingencia: 
java -jar %jarlocation% -server %ems_server% -user %EMSUser% -queue %queuecontingencia% %message%
ECHO SE HA ENVIADO EL MENSAJE %message% A LA QUEUE %queuecontingencia% POR FAVOR ESPERE AL REDEDOR DE 20 MINUTOS Y REVISE LA RUTA '%contingenciapath%%message%'
PAUSE

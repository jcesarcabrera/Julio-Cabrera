#!/bin/bash
QueueDestinoUsos=Davivienda_Transactions_Contingency
QueueEnvioUsos=Davivienda_Transactions_Manual
EMSServer=tcp://localhost:7222
Opciones=0

function menu(){
echo "EJECUCION DE CONTIGENCIA DE USOS Y NOVEDADES DAVIVIENDA"
echo ".................................................."
echo "SELECCIONE UNA DE LAS OPCIONES DIPSPONIBLES"
echo "."
echo "1 - ENVIO DE USOS DE FORMA MANUAL AL BANCO"
echo "2 - ENVIO DE USOS POR CONTINGENCIA AL BANCO"
echo "3 - ENVIO DE NOVEDADES AL FCS"
echo "4 - ENVIO DE RESPUESTA DE NOVEDADES AL BANCO"
echo "5 - SALIR"
echo "."
echo "OPCIONES 1, 2, 3, 4 O 5" 
}

function enviousos(){
clear
echo "SE ENVIARA PETICION PARA GENERAR Y ENVIAR ARCHIVO DE USOS HACIA EL BANCO DAVIVIENDA"
emsuser="EMSUser=admin"
java -jar tibjmsMsgProducer.jar -server "$EMSServer" -user admin -queue "$QueueEnvioUsos" ""
echo "SE HA ENVIADO PETICION PARA GENERAR Y ENVIAR ARCHIVO DE USOS HACIA EL BANCO DAVIVIENDA"
}

function contingencias(){
clear
echo "SE ENVIARA EL MENSAJE PARA GENERAR EL ARCHIVO DE CONTINGENCIA DAVIVIENDA"
echo "Indique la fecha para consultar y enviar contingencia de usos" 
read mensaje
emsuser="EMSUser=admin"
java -jar tibjmsMsgProducer.jar -server "$EMSServer" -user admin -queue "$QueueDestinoUsos" "$mensaje"
echo "SE HA ENVIADO EL MENSAJE "$mensaje" PARA GENERAR LA CONTINGENCIA DE USOS DAVIVIENDA POR FAVOR REVISE EL TRACING EN EL ADMINISTRATOR PARA ASEGURAR LA EJECUCION CORRECTA DEL SERVICIO"
}

while [ "$Opciones" != 5 ]
do
menu

if [ "$Opciones" = 1 ]; then 
enviousos
menu

elif [ "$Opciones" = 2 ]; then 
contingencias
menu

elif [ "$Opciones" = 3 ]; then 
echo "SE ENVIAN NOVEDADES AL FCS"
java -jar tibjmsMsgProducer.jar -server "$EMSServer" -user admin -queue ContingencyNovelty.Queue.Send ""
echo "SE HA REALIZADO ENVIO DE NOVEDADES AL FCS"
menu

elif [ "$Opciones" = 4 ]; then 
echo "SE ENVIA RESPUESTA DE NOVEDADES AL BANCO DAVIVIENDA"
java -jar tibjmsMsgProducer.jar -server "$EMSServer" -user admin -queue ContingencyNovelty.Queue.Reply ""
echo "SE HA REALIZADO ENVIO DE RESPUESTA DE NOVEDADES AL BANCO DAVIVIENDA"
menu

elif [ "$Opciones" = 5 ]; then 
echo "FINALIZA EL SCRIPT"
fi
read Opciones
done
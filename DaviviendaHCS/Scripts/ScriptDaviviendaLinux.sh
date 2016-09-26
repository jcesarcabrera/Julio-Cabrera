#!/bin/bash
QueueDestinoUsos=Davivienda_Transactions_Contingency
QueueDestinoNovedades=1
QueueDestinoRespuestaNovedades=1
EMSServer=tcp://192.168.54.194:7222
Opciones=0

function contingencias(){
cd "/home/jcabrera/TIBCO/Scripts/"
echo "SE ENVIARA EL MENSAJE PARA GENERAR EL ARCHIVO DE CONTINGENCIA DAVIVIENDA"
echo "Indique la fecha para consultar y enviar contingencia de usos" 
read mensaje
emsuser="EMSUser=admin"
java -jar tibjmsMsgProducer.jar -server "$EMSServer" -user admin -queue "$QueueDestinoUsos" "$mensaje"
echo "SE HA ENVIADO EL MENSAJE "$mensaje" PARA GENERAR LA CONTINGENCIA DE USOS DAVIVIENDA POR FAVOR REVISE EL TRACING EN EL ADMINISTRATOR PARA ASEGURAR LA EJECUCION CORRECTA DEL SERVICIO"
}

function menu(){
echo "EJECUCION DE CONTIGENCIA DE USOS Y NOVEDADES DAVIVIENDA"
echo ".................................................."
echo "Seleccione una de las opciones disponibles"
echo "."
echo "1 - Envío de usos por contingencia al Banco"
echo "2 - Envio de novedades a FCS"
echo "3 - Envio de respuesta de novedades al Banco"
echo "4 - Salir"
echo "."
echo "Opciones 1, 2, 3, o 4" 
}

menu

while [ "$Opciones" != 4 ]
do
menu

if [ "$Opciones" = 1 ]; then 
contingencias
menu

elif [ "$Opciones" = 2 ]; then 
echo "Se selecciona la opcion 2"
menu

elif [ "$Opciones" = 3 ]; then 
echo "Se ha seleccionado la opcion 3"
menu

elif [ "$Opciones" = 4 ]; then 
echo "Adios salir"
fi
read Opciones
done

———–Start Code———–
ECHO OFF 
ECHO Envio de mensaje por JMS TIBCO. 
PAUSE 
ECHO OFF cd C:\tibco\ems\8.1\samples\java
cd "C:\tibco\ems\8.1\samples\java\" "C:\tibco\tibcojre64\1.7.0\bin\java\tibjmsMsgProducer -server tcp://54.213.153.61:7222 -user admin -queue prueba.test Prueba1"
ECHO ruta actual: %cd%
ECHO Se ejecuta el comando para invocar la queue
PAUSE
ECHO OFF 
PAUSE
ECHO Se ejecuto el comando. 
PAUSE 
———–End Code———–
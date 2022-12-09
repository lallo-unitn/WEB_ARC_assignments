rem THIS BATCH FILE IS USED TO DEPLOY SERVER AND CLIENT CODE

@echo off

javac src/main/java/it/unitn/disi/web/rg209272/demormi/rmiinterface/RMIInterface.java src/main/java/it/unitn/disi/web/rg209272/demormi/rmiserver/ServerOperation.java src/main/java/it/unitn/disi/web/rg209272/demormi/rmiclient/ClientOperation.java

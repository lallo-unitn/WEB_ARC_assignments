package it.unitn.disi.web.ass1.RG;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunReverse extends Thread{
    String output = null;
    public RunReverse(String input){
        try {
            String readLine;
            //String command = "java 'C:\\WEB PROJECTS\\Reverse\\src\\it\\unitn\\disi\\ass1_reverse\\RG209272\\Reverse.java'";
            //REVERSE IS TREATED AS SEPARATE PROCESS, AS ASKED BY ASSIGNMENT SPECIFICATIONS
            String command = "java Reverse\\src\\it\\unitn\\disi\\web\\ass1_reverse\\RG\\Reverse.java";

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("powershell.exe", command + " " + input);
            Process p = processBuilder.start();

            BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((readLine = bri.readLine()) != null) {
                output = readLine;
            }
            bri.close();
        } catch (Exception e) {
            output = e.getMessage();
        }
    }
}

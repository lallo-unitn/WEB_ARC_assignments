package it.unitn.disi.web.ass1.RG;

import java.io.PrintStream;

public class HttpStatusPrinter {

    PrintStream printStream;

    public HttpStatusPrinter(PrintStream printStream){
        this.printStream = printStream;
    }

    public void printStatus(HttpStatus.Status status, Exception e, String request){
        switch (status) {
            case BAD_REQUEST -> printBadRequest(printStream, request);
            case NOT_FOUND -> printNotFound(e, printStream, request);
            default -> throw new UnsupportedOperationException("Operation is not supported");
        }
    }
    private void printNotFound(Exception e, PrintStream printStream, String path){
        printStream.print("HTTP/1.1 404 Not Found \r\n\r\n");
        //printStream.print("404 Not Found: Couldn't locate resource " + path);
        System.out.println("404 Not Found: " + path);
        try {
            System.out.println("ERROR: " + e.getMessage());
        }catch (NullPointerException nullPointerException){
            System.out.println("ERROR during printing: " + nullPointerException.getMessage());
        }
    }

    private void printBadRequest(PrintStream printStream, String reason){
        printStream.print("HTTP/1.1 400 Bad Request\r\n\r\n");
        //printStream.print("400 Bad Request: " +reason + "\n");
        System.out.println("400 Bad Request: " + reason);
    }
}

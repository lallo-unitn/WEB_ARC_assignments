package it.unitn.disi.web.ass1.RG;

import java.util.StringTokenizer;

public class RequestParser {
    String parsedInput = null;
    String parsedRequest = null;
    String path = null;
    Boolean isReqIndex = true;

    Boolean reqIsValid = true;

    public RequestParser(String request, HttpStatusPrinter httpStatusPrinter) {
        StringTokenizer stringTokenizer = new StringTokenizer(request);
        if ((stringTokenizer.countTokens() >= 2) && stringTokenizer.nextToken().equals("GET")) {
            if ((request = stringTokenizer.nextToken()).startsWith("/")) {
                request = request.substring(1);
            }
            if (request.endsWith("/") || request.equals("")) {
                request = request + "index.html";
                parsedRequest = request;
            }

            String[] test = request.split("\\?");
            path = test[0];
            System.out.println("path: " + test[0]);
            // GET PARAMETER
            if (test[0].equals("process/reverse")) {
                isReqIndex = false;
                System.out.println("INTO THE PROCESS");
                try {
                    String[] req = request.split("\\?");
                    String[] par = req[1].split("par1=");
                    parsedInput = par[1];
                    parsedInput = parsedInput.replace('+',  ' ');
                    parsedInput = parsedInput.replace("%20", " ");
                    System.out.println("PARSED INPUT = " + parsedInput);
                } catch (Exception e) {
                    httpStatusPrinter.printStatus(HttpStatus.Status.BAD_REQUEST, null, "Parameter is not named par1");
                    reqIsValid = false;
                    System.out.println("ERROR: " + e.getMessage());
                }
                System.out.println("STRINGA = " + parsedInput);
            }
            else if(!test[0].equals("index.html")){
                httpStatusPrinter.printStatus(HttpStatus.Status.NOT_FOUND, null, path);
                reqIsValid = false;
            }
        } else {
            httpStatusPrinter.printStatus(HttpStatus.Status.BAD_REQUEST, null, "Not a GET request");
            reqIsValid = false;
        }
    }
}

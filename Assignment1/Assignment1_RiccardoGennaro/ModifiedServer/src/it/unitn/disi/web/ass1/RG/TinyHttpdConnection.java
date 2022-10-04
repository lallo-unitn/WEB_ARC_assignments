package it.unitn.disi.web.ass1.RG;

import java.io.*;
import java.net.Socket;

public class TinyHttpdConnection extends Thread
{
    Socket socket;
    private HttpStatusPrinter httpStatusPrinter = null;
    private PrintStream printStream = null;
    private OutputStream outputStream = null;
    private String reverseOutput = null;
    RequestParser requestParser = null;

    public TinyHttpdConnection(Socket s)
    {
        socket = s;
        setPriority(NORM_PRIORITY - 1);
        start();
    }

    @Override
    public void run()
    {
        System.out.println("==========");
        System.out.println("Connection on port: " + socket.getPort());

        BufferedReader bufferedReader = null;

        try
        {
            // OPEN SOCKET I/O
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = socket.getOutputStream();
            printStream = new PrintStream(outputStream);
            httpStatusPrinter = new HttpStatusPrinter(printStream);

            // READ REQUEST
            String request = bufferedReader.readLine();
            if (request == null) return;
            System.out.println("Request: " + request);

            // READ HEADERS
            String header = null;

            do
            {
                header = bufferedReader.readLine();
                System.out.println("Header: " + header);
            } while (header != null && header.length() > 0);

            //PARSE THE REQUEST
            requestParser = new RequestParser(request, httpStatusPrinter);
            if(!requestParser.reqIsValid) return;
            String input = requestParser.parsedInput;
            request = requestParser.parsedRequest;

            //CHECK IF THE PROCESS IS REQUESTED. IF SO, RUN IT.
            if(!requestParser.isReqIndex){
                //CHECK IF INPUT IS NULL
                try {
                    if (input == null) {
                        throw new Exception();
                    }
                }catch(Exception e){
                    httpStatusPrinter.printStatus(HttpStatus.Status.BAD_REQUEST, null, request);
                    return;
                }

                // CALL EXTERNAL REVERSE PROCESS & SEND RESULTS
                RunReverse reverse = new RunReverse(input);

                reverseOutput = reverse.output;
                System.out.println("OUTPUT = " + reverseOutput);
            }

            //PREPARE & SEND RESPONSE
            sendResponse(requestParser.isReqIndex, request);

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            try {
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("I/O error on close" + e);
            }
        }
    }


    private void sendResponse(boolean isReqIndex, String request){
        try {
            if(request == null && reverseOutput == null){
                throw new Exception();
            }
            if (isReqIndex) {
                FileInputStream fis = new FileInputStream("ModifiedServer\\Documents\\" + request);
                int responseLength = fis.available();
                // SENDING HEADERS
                printStream.print("HTTP/1.1 200 OK\r\n");
                printStream.print("Content-Length: " + responseLength + "\r\n");
                printStream.print("Content-Type: text/html\r\n");
                printStream.print("\r\n");
                // SENDING CONTENT
                byte[] data = new byte[responseLength];
                fis.read(data);
                outputStream.write(data);
                fis.close();
            } else {
                int responseLength = reverseOutput.length();
                // SENDING HEADERS
                printStream.print("HTTP/1.1 200 OK\r\n");
                printStream.print("Content-Length: " + responseLength + "\r\n");
                printStream.print("Content-Type: text/html\r\n");
                printStream.print("\r\n");
                // SENDING CONTENT
                byte[] data = reverseOutput.getBytes();
                outputStream.write(data);
            }
        }catch (Exception e){
            httpStatusPrinter.printStatus(HttpStatus.Status.NOT_FOUND, e, requestParser.path);
        }
    }
}

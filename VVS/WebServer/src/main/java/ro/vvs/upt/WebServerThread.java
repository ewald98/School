package ro.vvs.upt;

import ro.vvs.upt.config.Configuration;
import ro.vvs.upt.config.ConfigurationManager;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class WebServerThread extends Thread {

    private Socket clientSocket;
    private static final String CRLF = "\r\n";

    WebServerThread(Socket clientSoc) {
        clientSocket = clientSoc;
        start();
    }

    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        BufferedOutputStream dataOut = null;

        String fileRequested = null;

        Configuration config = ConfigurationManager.getInstance().getCurrentConfig();
        try {

            System.out.println(" !!! New Communication Thread Started, on " + clientSocket.getInetAddress());

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());      // for header
            dataOut = new BufferedOutputStream(clientSocket.getOutputStream()); // for requested data

            String input = in.readLine();
            StringTokenizer parse = new StringTokenizer(input);
            String method = parse.nextToken().toUpperCase();
            fileRequested = parse.nextToken().toLowerCase();

            if (!method.equals("GET") && !method.equals("HEAD")) {
//                throw new RuntimeException("501: Method not implemented" + method);

                File file = new File(config.getWebroot(), config.getErrorFile());
                int fileLength = (int) file.length();
                String contentMimeType = "text/html";
                byte[] fileData = readFileData(file, fileLength);

                out.println("HTTP/1.1 404 Error:Not found/Method not implemented");
                out.println("Server: JHTTP Server xDD");
                out.println("Content-type: " + contentMimeType);
                out.println("Content-length: " + fileLength);
                out.println();
                out.flush();

                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();
            } else {
                if (fileRequested.endsWith("/")) {
                    fileRequested += config.getDefaultFile();
                }

                File file = new File(config.getWebroot(), fileRequested);
                int fileLength = (int) file.length();
                String content = getContentType(fileRequested);

                if (method.equals("GET")) {
                    byte[] fileData = readFileData(file, fileLength);

                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: JHTTP Server xDD");
                    out.println("Content-type: " + content);
                    out.println("Content-length: " + fileLength);
                    out.println();
                    out.flush();

                    dataOut.write(fileData, 0, fileLength);
                    dataOut.flush();
                }

                System.out.println("File" + fileRequested + " of type " + content + " returned");

            }

//            String html = "<html><head><title>Java HTTP</title></head><body><h1>This page was server by my Java server</h1></body></html>";
//
//            String response =
//                    "HTTP/1.1 200 OK" + CRLF +  // Status line
//                            "Content-Length: " + html.getBytes().length + CRLF +  // Header (content length)
//                            CRLF +
//                            html +
//                            CRLF + CRLF;
//
//            os.write(response.getBytes());

        } catch (FileNotFoundException e) {

            try {
                File file = new File(config.getWebroot(), config.getErrorFile());
                int fileLength = (int) file.length();
                String contentMimeType = "text/html";
                byte[] fileData = readFileData(file, fileLength);

                out.println("HTTP/1.1 404 Error:Not found/Method not implemented");
                out.println("Server: JHTTP Server xDD");
                out.println("Content-type: " + contentMimeType);
                out.println("Content-length: " + fileLength);
                out.println();
                out.flush();

                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();
            } catch (IOException e1) {

            }


        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            System.exit(1);
        } finally {
            try {
                in.close();
                out.close();
                dataOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) { }
            }
        }
    }

    private byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];

        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null)
                fileIn.close();
        }

        return fileData;
    }

    // return supported MIME Types
    private String getContentType(String fileRequested) {
        if (fileRequested.endsWith(".htm") || fileRequested.endsWith(".html"))
            return "text/html";
        else
            return "text/plain";
    }

}

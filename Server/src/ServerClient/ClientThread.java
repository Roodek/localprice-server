package ServerClient;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {

    private DataInputStream is = null;
    private PrintStream os = null;
    private Socket clientSocket = null;
    private final ClientThread[] threads;
    private int maxClientsCount;

    public ClientThread(Socket clientSocket, ClientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    public void run() {
        int maxClientsCount = this.maxClientsCount;
        ClientThread[] threads = this.threads;

        try {
            //tworzenie stramow odczytu i wpisu dla klienta
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
            int length;
            switch (is.readInt()){
                case 1:// warunek dla otrzymania zdjecia
                    length = is.readInt();         // read length of incoming message

                    if (length > 0) {
                        byte[] message = new byte[length];
                        is.readFully(message, 0, message.length); // read the message

                        String filePath = ImageCoder.decoder(message);
                        System.out.println("zdjecie");
                        System.out.println("DONE!");

                        Tesseract tess = new Tesseract();//odczytywanie paragonu poprzez tesseracta
                        String outFile = filePath.substring(0,filePath.length()-4);
                        File fileAfterRead = tess.getContent(filePath,outFile);


                        outFile+=".txt";


                        ReadFile reader = new ReadFile();
                        reader.readFile(outFile);
                        break;
                    }
                case 2://warunek dla pojedynczego rekordu wpisanego recznie
                    length = is.readInt();         // read length of incoming message

                    if (length > 0) {
                        byte[] message = new byte[length];
                        is.readFully(message, 0, message.length); // read byteArray to message

                        JSONObject jsonObject = JsonCoder.arrayToJson(message);
                        Record record = new Record();
                        record.JsonToRecord(jsonObject);
                        System.out.println("DONE!");
                        System.out.println();
                        break;
                    }
            }
            //wyzeruj thread klienta
            for (int i = 0; i < maxClientsCount; i++) {
                if (threads[i] == this) {
                    threads[i] = null;
                }
            }

            //zamknij streamy
            is.close();
            os.close();
            clientSocket.close();
        } catch (IOException e) {
        }
    }
}


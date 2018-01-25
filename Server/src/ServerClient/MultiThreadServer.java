package ServerClient;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;


public class MultiThreadServer {

    // The server socket.
    private static ServerSocket serverSocket = null;
    // The client socket.
    private static Socket clientSocket = null;

    //liczba maksymalnej ilosci klientow rownoczesnie
    private static final int maxClientsCount = 50;
    private static final ClientThread[] threads = new ClientThread[maxClientsCount];

    public static void main(String args[]) {


        int portNumber = 2222;
        if (args.length < 1) {
            System.out
                    .println("Usage: java MultiThreadServer <portNumber>\n"
                            + "Now using port number=" + portNumber);
        } else {
            portNumber = Integer.valueOf(args[0]).intValue();
        }

        //tworzenie socketa servera na porcie 2222
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println(e);
        }

    /*
     tworzenie socketa dla kazdego klienta
     */
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                int i = 0;

                for (i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new ClientThread(clientSocket, threads)).start();
                        break;
                    }
                }


                PrintStream os = new PrintStream(clientSocket.getOutputStream());

                if (i == maxClientsCount) {//handling client
                    os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Server too busy. Try later.");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}


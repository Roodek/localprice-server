package ServerClient;

import org.json.JSONObject;

import java.io.*;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

import java.net.Socket;

/**
 * Created by kurocho on 25.11.2017.
 */

public class ConnectionFromClient {

    final static String serverUrl = "localhost";
    final static int port = 2222;
    Socket socket = null;
    public ConnectionFromClient() {
        super();
    }

    public void send(byte[] imageArray, int steering) {//wysylanie wiadomosci przez klienta

        byte[] message = imageArray;

        DataOutputStream socketOutputStream = null;

        try {

            socket = new Socket(); //przykladowy port i host
            socket.connect(new InetSocketAddress(serverUrl,port));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socketOutputStream = new DataOutputStream(socket.getOutputStream());
            socketOutputStream.writeInt(steering);
            socketOutputStream.writeInt(message.length);


            socketOutputStream.write(message);




            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendImage(byte[] imageArray)throws IOException{
        byte[] message = imageArray;
        socket = new Socket(); //przykladowy port i host
        socket.connect(new InetSocketAddress(serverUrl,port));

        DataOutputStream socketOutputStream ;


        socketOutputStream = new DataOutputStream(socket.getOutputStream());

        socketOutputStream.writeInt(message.length);
        //System.out.println(message.length);
        socketOutputStream.write(message);

        socket.close();

    }
    public void sendMessage(String message)throws IOException{

        socket = new Socket(); //przykladowy port i host
        socket.connect(new InetSocketAddress(serverUrl,port));


        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintStream os = new PrintStream(socket.getOutputStream());
        os.println(message);
        os.flush();
        System.out.println(in.readLine());
        os.close();

        socket.close();

    }
}
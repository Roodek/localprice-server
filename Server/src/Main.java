import ServerClient.ConnectionFromClient;
import ServerClient.ImageCoder;

import java.io.IOException;

public class Main {
    public static  void main(String[] args){
        ConnectionFromClient client = new ConnectionFromClient();
        ImageCoder coder = new ImageCoder();

            client.send(ImageCoder.encoder("C:\\Users\\user\\Desktop\\imgtest\\test.jpg"),1);

    }
}

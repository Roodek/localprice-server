package ServerClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args){
        ReadFile reader = new ReadFile();
        reader.readFile();
    }

    public void readFile(){
        String file = "C:\\Users\\user\\Desktop\\imgtest\\out.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}

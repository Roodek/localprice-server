package ServerClient;

import java.io.*;
import java.util.Base64;

public class ImageCoder {


    public static byte[] encoder(String imagePath) {

        File file = new File(imagePath);
        byte imageData[] = new byte[(int) file.length()];
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            imageData = new byte[(int) file.length()];
            imageInFile.read(imageData);

        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return imageData;
    }

    public static String decoder(String base64Image, String pathFile) {


        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return pathFile;
    }
    public static String decoder(byte[] imageByteArray) {//funkcja dekoduje byte[] na zdjecie i zwraca sciezke do zdjecia

        String pathFile = "C:\\Users\\user\\Desktop\\imgtest\\"+System.currentTimeMillis()+".jpg";


        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {

            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return pathFile;
    }
}
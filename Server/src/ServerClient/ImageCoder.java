package ServerClient;

import java.io.*;
import java.util.Base64;

public class ImageCoder {
    public static String main(String[] args) {
        String imagePath = "C:\\Users\\user\\Desktop\\imgtest\\img1.jpg";
        System.out.println("=================Encoder Image to Base 64!=================");
        byte[] imageData = encoder(imagePath);
        System.out.println("Base64ImageString = " +imageData);

        System.out.println("=================Decoder Base64ImageString to Image!=================");
        decoder(imageData);

        System.out.println("DONE!");
        return imagePath;
    }

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
    public static String decoder(byte[] imageByteArray) {

        String pathFile = "C:\\Users\\user\\Desktop\\imgtest\\"+System.currentTimeMillis()+".jpg";


        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            // Converting a Base64 String into Image byte array
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return pathFile;
    }
}
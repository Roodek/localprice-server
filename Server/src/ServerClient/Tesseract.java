package ServerClient;

import java.io.File;
import java.io.IOException;

public class Tesseract {


    public File getContent(String filePath, String outPath){
        final String dosCommand = "D:\\Tesseract-OCR\\tesseract";
        try {
            System.out.println("tesseract started");
            final Process process = Runtime.getRuntime().exec(
                    dosCommand + " " + filePath + " " + outPath);
            process.waitFor();
            System.out.println("tesseract completed");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //outPath+="txt";
        System.out.println(outPath+".txt");
        File outputFile = new File(outPath+".txt");

        return outputFile;
    }

}

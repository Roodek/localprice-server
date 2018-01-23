package ServerClient;

import java.io.File;
import java.io.IOException;

public class Tesseract {


    public File getContent(String filePath, String outPath){
        final String dosCommand = "D:\\Tesseract-OCR\\tesseract";
        try {
            final Process process = Runtime.getRuntime().exec(
                    dosCommand + " " + filePath + " " + outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File outputFile = new File(outPath);
        return outputFile;
    }

}

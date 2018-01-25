package ServerClient;

import java.io.File;
import java.io.IOException;

public class Tesseract {


    public File getContent(String filePath, String outPath){
        final String dosCommand = "D:\\Tesseract-OCR\\tesseract";
        try {
            System.out.println("1");
            final Process process = Runtime.getRuntime().exec(
                    dosCommand + " " + filePath + " " + outPath);
            System.out.println("3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //outPath+="txt";
        System.out.println(outPath+".txt");
        File outputFile = new File(outPath+".txt");
        System.out.println(outputFile.canRead());
        return outputFile;
    }

}

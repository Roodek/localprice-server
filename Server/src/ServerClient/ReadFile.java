package ServerClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    public static void main(String[] args){
        ReadFile reader = new ReadFile();
        //reader.readFile();
    }

    public Record readFile(String filePath){//odczytywanie pliku powstalego dzieki tesseractowi
        ArrayList<Record> products = new ArrayList<Record>();
        Record finalRecord = new Record();


        String file = filePath;//"C:\\Users\\user\\Desktop\\imgtest\\out2.txt";
        file = "C:\\Users\\user\\Desktop\\imgtest\\1516879458440.txt";//czytanie konkretnego pliku tekstowego bedacego wynikiem odczytu zdjecia



        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            boolean read = false;
            String shopName = br.readLine();
            String shopAddres = br.readLine();

            while ((line = br.readLine()) != null) {
                if(line.contains("PARAGON FISKALNY")){
                    System.out.println("jest");
                    read = true;
                    continue;
                }

                if (line.toUpperCase().contains("SPRZED")){
                    finalRecord = new Record(products);
                    System.out.println("koniec czytania");
                    break;
                }
                if(read == true){
                    products.add(new Record(extractName(line),extractPrice(line)[0],extractPrice(line)[1],shopName,shopAddres));
                    System.out.println(line);
                    //System.out.println(extractName(line));
                    //System.out.println(extractPrice(line));
                    extractPrice(line);
                }

                System.out.println(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        /*
        for (Record i : products){
            i.show();
        }*/
        return finalRecord;
    }
    public String extractName(String line){//pozyskiwanie nazwy produktu
        String result = new String();
        for(int i = 0 ; i <line.length();i++){
            if(line.charAt(i) == ','){
                break;
            }
            result+=line.charAt(i);
        }
        result = result.substring(0,result.length()-3);

        return result;
    }
    public double[] extractPrice(String line){//funkcja zwraca 2 ceny -detaliczna(extractPrice(line)[0]) oraz koncowa(extractPrice()line[0])

        String productPrice = new String();
        String finalPrice = new String();
        int index=0;
        int counter = 0;
        for(int i = line.length()-1 ; i>-1;i--){
            if(line.charAt(i) == ',' || line.charAt(i) =='.'){
                counter+=1;
                if(counter == 2){
                    index = i+3;
                    productPrice = line.substring(i+1,i+3);
                    finalPrice = line.substring(index+1,line.length()-2);
                }
            }
            if(line.charAt(i) == 'x' || line.charAt(i) =='*'){
                productPrice = line.substring(i+1,index);
                break;
            }
        }


        return new double[]{Double.parseDouble(productPrice.replace(",", ".")),
                Double.parseDouble(finalPrice.replace(",","."))};
    }
}

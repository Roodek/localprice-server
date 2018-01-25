package ServerClient;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Record {//klasa zawiera dane pojedynczego wpisu do bazy danych
    private double price;
    private double finalPrice;
    private String productName;
    private String shopName;
    private String addres;
    private double ammount = finalPrice/price;
    private ArrayList<Record> productsList;

    public Record(){}

    public Record(String name, double price,double finalPrice, String shop, String addres){

        this.productName = name;
        this.price=price;
        this.finalPrice = finalPrice;
        this.shopName=shop;
        this.addres=addres;
    }

    public  Record(ArrayList<Record> productsList) {
        this.productsList = productsList;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public void setShop(String shop) {
        this.shopName = shop;
    }

    public void setName(String name){this.productName = name;}

    public  void show(){//wypisuje dane w obiekcie
        System.out.println("nazwa produktu " +productName);
        System.out.println("cena: "+price);
        System.out.println("cena finalna: "+finalPrice);
        System.out.println("iosc: "+ammount);
        System.out.println("nazwa sklepu: "+shopName);
        System.out.println("adres: "+addres);

    }

    public void JsonToRecord(JSONObject jsonObject){
        shopName = jsonObject.getString("shopName");
        productName = jsonObject.getString("productName");
        price = jsonObject.getDouble("productPrice");
        addres = jsonObject.getString("shopLocation");
        //show();
    }
    public JSONObject RecordToJson(Record record){//tworzy obiekt JSON z obiektu typu rekord
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("shopName",shopName);
        jsonObject.append("productName",productName);
        jsonObject.append("productPrice",price);
        jsonObject.append("shopLocation",addres);

        return jsonObject;
    }
    public JsonObject createJsonReceipt(){//tworzenie obiektu Json o konstrukcji paragonu
        JSONArray ja = new JSONArray();
        JSONObject jo = new JSONObject();


        for(Record i : productsList){
            jo = new JSONObject();
            ja.put(jo.append("name",i.productName).append("price",i.finalPrice).append("ammount",i.ammount));
        }
        String date = new String();
        Map<String,String> config = new HashMap<String, String>();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        JsonObject value = factory.createObjectBuilder()
                .add("receipt",factory.createObjectBuilder()
                    .add("date",date)
                    .add("shop",factory.createObjectBuilder()
                        .add("address",addres)
                        .add("name",shopName))
                    .add("products",factory.createArrayBuilder((JsonArray) ja)))
                .build();
        System.out.println(value.get("shop"));
        return value;
    }


}

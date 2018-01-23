package ServerClient;

import org.json.JSONObject;

public class Record {
    double price;
    String productName;
    String shopName;
    String addres;

    public Record(){}

    public Record(String name, double price, String shop, String addres){

        this.productName = name;
        this.price=price;
        this.shopName=shop;
        this.addres=addres;
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

    public  void show(){
        System.out.println(productName);
        System.out.println(price);
        System.out.println(shopName);
        System.out.println(addres);

    }

    public void JsonToRecord(JSONObject jsonObject){
        shopName = jsonObject.getString("shopName");
        productName = jsonObject.getString("productName");
        price = jsonObject.getDouble("productPrice");
        addres = jsonObject.getString("shopLocation");
        show();
    }


}

package ServerClient;

import org.json.JSONObject;


public class JsonCoder {

    public static JSONObject arrayToJson(byte[] byteArray){
        String buforToJson = new String(byteArray);
        JSONObject JsonObject = new JSONObject(buforToJson);
        //System.out.println(JsonObject.toString());
        return JsonObject;
    }
}

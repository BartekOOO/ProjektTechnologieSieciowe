package Models;

import Interfaces.IData;
import Services.JSONService.JSONService;
import Services.Kodek.Kodek;

import java.util.Dictionary;
import java.util.Hashtable;

public class Token implements IData {

    String Token = "";

    @Override
    public String GetClassName() {
        return "Token";
    }

    @Override
    public String ToJSONBody() {
        StringBuilder result = new StringBuilder("{"+ "\n");
        result.append("\"token\":"+'"').append(this.Token).append('"'+","+ "\n");
        result.append("}");
        return result.toString();
    }

    public void SetToken(String token){
        try {
            this.Token = Kodek.Encrypt(token);
        }catch (Exception ex){
            this.Token = "";
        }
    }


    @Override
    public void ReadDataFromJSON(String JSONData) {
        JSONService JSONBody = new JSONService(JSONData);
        if(JSONBody.GetJSONFieldValue("className").equals("Token")){
            JSONService JSONDataChild = new JSONService(JSONBody.GetJSONFieldValue("data"));
            this.Token = JSONDataChild.GetJSONFieldValue("token");
        }
    }

}

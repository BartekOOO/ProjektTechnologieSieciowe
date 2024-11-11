package Models;

import Interfaces.IData;
import Services.JSONService.JSONService;
import Services.Kodek.Kodek;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.Hashtable;

public class Token implements IData {

    private int UserId = 0;
    private String Token = "";
    private LocalDateTime ExpirationDate;

    @Override
    public String GetClassName() {
        return "Token";
    }

    @Override
    public String ToJSONBody() {
        StringBuilder result = new StringBuilder("{"+ "\n");
        result.append("\"userId\":").append(this.UserId).append(","+ "\n");
        result.append("\"expirationDate\":\"").append(this.ExpirationDate).append("\","+ "\n");
        result.append("\"token\":"+'"').append(this.Token).append('"'+""+ "\n");
        result.append("}");
        return result.toString();
    }

    public Token(){
        this.UserId = 0;
        this.ExpirationDate = LocalDateTime.now();
        this.Token = "";
    }

    public Token(int UserId,String UserName,LocalDateTime ExpirationDate){
        this.UserId = UserId;
        this.ExpirationDate = ExpirationDate;
        this.Token = Kodek.GenerateUserToken(UserId+"",UserName,ExpirationDate);
    }


    @Override
    public void ReadDataFromJSON(String JSONData) {
        JSONService JSONBody = new JSONService(JSONData);
        if(JSONBody.GetJSONFieldValue("className").equals("Token")){
            JSONService JSONDataChild = new JSONService(JSONBody.GetJSONFieldValue("data"));
            this.Token = JSONDataChild.GetJSONFieldValue("token");
            this.UserId = Integer.parseInt(JSONDataChild.GetJSONFieldValue("userId"));
            this.ExpirationDate = LocalDateTime.parse(JSONDataChild.GetJSONFieldValue("expirationDate"));
        }
    }

}

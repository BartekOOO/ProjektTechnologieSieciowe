package Models;

import Interfaces.IData;
import Interfaces.IDeleteData;
import Interfaces.IInsertData;
import Interfaces.IUpdateData;
import Services.JSONService.JSONService;
import Services.Kodek.Kodek;
import Services.SQLService.DataRow;

import java.util.Dictionary;
import java.util.Hashtable;

public class User implements IUpdateData, IInsertData, IDeleteData, IData {
    private int Id;
    private String UserName;
    private String Email;
    private String Password;

    public User(){

    }

    public User(DataRow row){
        this.Id = row.getFieldValue("PUS_Id");
        this.Email = row.getFieldValue("PUS_EMAIL");
        this.UserName = row.getFieldValue("PUS_UserName");
        this.Password = row.getFieldValue("PUS_Password");
    }

    public User(int Id,String UserName,String Password,String Email){
        this.Id = Id;
        this.Email = Email;
        this.UserName = UserName;
        SetPassword(Password);
    }




    public void SetPassword(String password){
        try {
            this.Password = Kodek.Encrypt(password);
        }
        catch (Exception ex){
            this.Password = "";
        }
    }

    public String GetPassword(){
        try{
            return Kodek.Decrypt(this.Password);
        }
        catch (Exception ex){
            return "";
        }
    }


    @Override
    public Dictionary<String, Object> GetDeleteParameters() {
        Dictionary<String,Object> result = new Hashtable<>();
        result.put("@Id",this.Id);
        return result;
    }

    @Override
    public Dictionary<String, Object> GetInsertParameters() {
        Dictionary<String,Object> params = new Hashtable<>();
        params.put("@UserName",this.UserName);
        params.put("@Email",this.Email);
        params.put("@Password",this.Password);
        return params;
    }

    @Override
    public Dictionary<String, Object> GetUpdateParameters() {
        Dictionary<String,Object> params = this.GetInsertParameters();
        params.put("@Id",this.Id);
        return params;
    }

    @Override
    public String GetClassName() {
        return "User";
    }

    @Override
    public String ToJSONBody() {
        StringBuilder result = new StringBuilder("{");
        result.append("\"id\":").append(this.Id).append(",");
        result.append("\"userName\":\"").append(this.UserName).append("\",");
        result.append("\"email\":\"").append(this.Email).append("\",");
        result.append("\"password\":\"").append(this.Password).append("\"");
        result.append("}");
        return result.toString();
    }

    @Override
    public void ReadDataFromJSON(ResponseData responseData) {
        JSONService JSONBody = new JSONService(responseData.ToJSONBody());
        if(JSONBody.getJSONFieldValue("className").equals("User")){
            JSONService JSONData = new JSONService(JSONBody.getJSONFieldValue("data"));
            this.Id = Integer.parseInt(JSONData.getJSONFieldValue("id"));
            this.Email = JSONData.getJSONFieldValue("email");
            this.UserName = JSONData.getJSONFieldValue("userName");
            this.Password = JSONData.getJSONFieldValue("password");
        }
    }


}

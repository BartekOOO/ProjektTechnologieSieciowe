package Models;

import Interfaces.IDeleteData;
import Interfaces.IInsertData;
import Interfaces.IUpdateData;
import Services.Kodek.Kodek;
import Services.SQLService.DataRow;

import java.util.Dictionary;
import java.util.Hashtable;

public class User implements IUpdateData, IInsertData, IDeleteData {
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
    public int GetId() {
        return this.Id;
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
}

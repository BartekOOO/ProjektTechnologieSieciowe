package Models;

import Services.SQLService.DataRow;

public class User {
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

}

package Models;

import Interfaces.IData;
import Interfaces.IInsertData;

import java.sql.Date;
import java.util.Dictionary;
import java.util.Hashtable;

public class Message implements IData, IInsertData {
    public int Id;
    public int ReceiverId;
    public int SenderId;
    public Date Date;
    public String Message;

    @Override
    public String GetClassName() {
        return "Message";
    }

    @Override
    public String ToJSONBody() {
        StringBuilder result = new StringBuilder("{");
        result.append("\"senderId\":").append(this.SenderId).append(",");
        result.append("\"receiverId\":").append(this.ReceiverId).append(",");
        result.append("\"message\":\"").append(this.Message).append("\"");
        result.append("}");
        return result.toString();
    }

    @Override
    public void ReadDataFromJSON(ResponseData responseData) {

    }

    @Override
    public Dictionary<String, Object> GetInsertParameters() {
        Dictionary<String,Object> result = new Hashtable<>();
        result.put("@SenderId",this.SenderId);
        result.put("@ReceiverId",this.ReceiverId);
        result.put("@Message",this.Message);
        return  result;
    }
}

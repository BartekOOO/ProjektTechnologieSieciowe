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
        StringBuilder result = new StringBuilder("{"+ "\n");
        result.append("\"senderId\":").append(this.SenderId).append(","+ "\n");
        result.append("\"receiverId\":").append(this.ReceiverId).append(","+ "\n");
        result.append("\"message\":\"").append(this.Message).append("\""+ "\n");
        result.append("}");
        return result.toString();
    }

    @Override
    public void ReadDataFromJSON(String JSONData) {

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

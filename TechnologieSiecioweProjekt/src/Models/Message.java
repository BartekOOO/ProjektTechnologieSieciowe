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
        String result = "[{";
        result = result + "senderId:"+this.SenderId+",";
        result = result + "receiverId:"+this.ReceiverId+",";
        result = result + "message:"+this.Message+",";
        result = result + "}]";
        return result;
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

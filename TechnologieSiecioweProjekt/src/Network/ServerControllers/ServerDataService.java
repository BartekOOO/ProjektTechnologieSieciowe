package Network.ServerControllers;

import Models.*;
import Services.SQLService.SQLService;

import java.util.Dictionary;
import java.util.Hashtable;

public class ServerDataService {

    public static ResponseData ProcessData(RequestData requestData){
        ResponseData result = new ResponseData();
        if(requestData.className.equals("User")) result = UserController.ProcessUser(requestData);
        if(requestData.className.equals("Message")) result = MessageController.ProcessMessage(requestData);
        return result;
    }




}

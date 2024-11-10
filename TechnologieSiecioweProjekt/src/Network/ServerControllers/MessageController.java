package Network.ServerControllers;

import Models.*;
import Services.SQLService.SQLService;

import java.util.Dictionary;
import java.util.Hashtable;

public class MessageController {

    public static ResponseData ProcessMessage(RequestData requestData){
        ResponseData result = new ResponseData();

        SQLService sqlService = new SQLService(new ConfigD());
        Dictionary<String,Object> parameters = new Hashtable<>();
        switch (requestData.method){
            case Method.Post:

                break;
            case Method.Get:

                break;
            case Method.Delete:

                break;
            case Method.Put:

                break;
            default:

                break;
        }
        return result;
    }
}

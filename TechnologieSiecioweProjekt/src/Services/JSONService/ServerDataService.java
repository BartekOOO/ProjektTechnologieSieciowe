package Services.JSONService;

import Models.*;
import Services.SQLService.SQLService;

import java.util.Dictionary;
import java.util.Hashtable;

public class ServerDataService {


    public static ResponseData ProcessData(RequestData requestData){
        ResponseData result = new ResponseData();

        SQLService sqlService = new SQLService(new ConfigD());
        Dictionary<String,Object> parameters = new Hashtable<>();
        switch (requestData.method){
            case Method.Post:
                parameters = requestData.data.GetInsertParameters();
                sqlService.ExecuteStoredProcedureWithResult("PROJEKT.InsertUser",parameters);
                result.setCode(ResponseCode.OK);
                result.setJSONResponse(new EmptyData());
                result.setStatus("Success");
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

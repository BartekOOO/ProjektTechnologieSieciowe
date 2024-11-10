package Network.ServerControllers;

import Models.*;
import Services.SQLService.DataRow;
import Services.SQLService.DataTable;
import Services.SQLService.SQLService;

import java.util.*;

public class UserController {
    public static ResponseData ProcessUser(RequestData requestData){
        ResponseData result = new ResponseData();

        SQLService sqlService = new SQLService(new ConfigD());
        Dictionary<String,Object> parameters = new Hashtable<>();
        switch (requestData.method){
            case Method.Post:
                parameters = requestData.data.GetInsertParameters();
                Dictionary<String, Object> CheckParams = new Hashtable<>();

                User user = new User();
                user.ReadDataFromJSON(requestData.ToJSONBody());
                CheckParams.put("@UserName",user.getUserName());

                DataTable CheckUser = sqlService.ExecuteStoredProcedureWithResult("PROJEKT.UserExists",CheckParams);

                DataRow row = CheckUser.GetRows().get(0);

                if((Integer) row.getFieldValue("Liczba")==0) {
                    result.setCode(ResponseCode.FORBIDDEN);
                    result.setStatus("Uzytkownik już istnieje");
                    result.setJSONResponse(new EmptyData());
                    return result;
                }

                if(user.getUserName().isEmpty()){
                    result.setCode(ResponseCode.FORBIDDEN);
                    result.setStatus("Nie podano nazwy użytkownika");
                    result.setJSONResponse(new EmptyData());
                    return result;
                }

                if(user.GetPassword().isEmpty()){
                    result.setCode(ResponseCode.FORBIDDEN);
                    result.setStatus("Nie podano hasła");
                    result.setJSONResponse(new EmptyData());
                    return result;
                }

                sqlService.ExecuteStoredProcedureWithResult("PROJEKT.InsertUser",parameters);
                result.setCode(ResponseCode.OK);
                result.setJSONResponse(new EmptyData());
                result.setStatus("Pomyślnie dodano nowego użytkownika");
                break;
            case Method.Get:

                break;
            case Method.Delete:
                parameters = requestData.data.GetDeleteParameters();
                sqlService.ExecuteStoredProcedureWithResult("PROJEKT.DeleteData",parameters);
                result.setCode(ResponseCode.OK);
                result.setJSONResponse(new EmptyData());
                result.setStatus("Pomyślnie usunięto konto");
                break;
            case Method.Put:

                break;
            case Method.LogIn:

                break;
            case Method.GetList:

                break;
            case Method.LogOut:

                break;
            default:

                break;
        }
        return result;
    }
}

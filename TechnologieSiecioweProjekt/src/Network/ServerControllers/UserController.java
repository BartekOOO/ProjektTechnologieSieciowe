package Network.ServerControllers;

import Models.*;
import Services.Kodek.Kodek;
import Services.SQLService.DataRow;
import Services.SQLService.DataTable;
import Services.SQLService.SQLService;

import java.time.LocalDateTime;
import java.util.*;

public class UserController {
    public static ResponseData ProcessUser(RequestData requestData){
        ResponseData result = new ResponseData();
        User user = new User();
        user.ReadDataFromJSON(requestData.ToJSONBody());
        SQLService sqlService = new SQLService(new ConfigD());
        Dictionary<String,Object> parameters = new Hashtable<>();
        switch (requestData.method){
            case Method.Post:
                parameters = user.GetInsertParameters();
                Dictionary<String, Object> CheckParams = new Hashtable<>();

                CheckParams.put("@UserName",user.GetUserName());
                DataTable CheckUser = sqlService.ExecuteStoredProcedureWithResult("PROJEKT.UserExists",CheckParams);

                if(CheckUser.GetRows().size()>0) {
                    result.setCode(ResponseCode.FORBIDDEN);
                    result.setStatus("Uzytkownik już istnieje");
                    result.setJSONResponse(new EmptyData());
                    return result;
                }

                if(user.GetUserName().isEmpty()){
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
                parameters = user.GetDeleteParameters();
                sqlService.ExecuteStoredProcedureWithResult("PROJEKT.DeleteData",parameters);
                result.setCode(ResponseCode.OK);
                result.setJSONResponse(new EmptyData());
                result.setStatus("Pomyślnie usunięto konto");
                break;
            case Method.Put:

                break;
            case Method.LogIn:
                Date data = new Date();
                Dictionary<String,Object> CheckLoginParams = new Hashtable<>();
                CheckLoginParams.put("@UserName",user.GetUserName());
                try {
                    CheckLoginParams.put("@Password", Kodek.Encrypt(user.GetPassword()));
                }catch (Exception ex){
                    result.setCode(ResponseCode.BAD_REQUEST);
                    result.setStatus("Błedne dane logowania");
                    result.setJSONResponse(new EmptyData());
                }
                DataTable CheckUserExist = sqlService.ExecuteStoredProcedureWithResult("PROJEKT.UserExists",CheckLoginParams);

                if(CheckUserExist.GetRows().isEmpty()){
                    result.setCode(ResponseCode.BAD_REQUEST);
                    result.setStatus("Błedne dane logowania");
                    result.setJSONResponse(new EmptyData());
                }
                else{
                    DataRow userRow = CheckUserExist.GetRows().get(0);
                    user.SetId(userRow.getFieldValue("PUS_Id"));
                    Token token = new Token(user.GetId(),user.GetUserName(),LocalDateTime.now());
                    result.setCode(ResponseCode.OK);
                    result.setStatus("Udało sie zalogowac");
                    result.setJSONResponse(token);
                    result.SetCLassName("Token");
                }
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

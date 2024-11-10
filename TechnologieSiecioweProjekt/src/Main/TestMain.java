package Main;

import Models.*;
import Services.SQLService.DataRow;
import Services.SQLService.DataTable;
import Services.SQLService.SQLService;

import java.util.Dictionary;
import java.util.Hashtable;

public class TestMain {

    public static void main(String[] args) {
        SQLService service = new SQLService(new ConfigD());

        //podajesz nazwę procedury np PROJEKT.GetUsers
        String query = "PROJEKT.DeleteUser";

        User user = new User(4,"Nowy","Hasloedytowany","emeilo");

        System.out.println(user.GetInsertParameters());

        //Test działania na bazie danych
        //DataTable result = service.ExecuteStoredProcedureWithResult(query,user.GetDeleteParameters());

        System.out.println(user.ToJSONBody());

        Message message = new Message();
        message.Message = "testowa wiadomosc";
        message.SenderId = 1;
        message.ReceiverId = 2;

        System.out.println(message.ToJSONBody());

        RequestData requestData = new RequestData(Method.Post,"tokenowy",user);
        System.out.println(requestData.ToJSONBody());

        ResponseData respone = new ResponseData(ResponseCode.OK,user,"Success");

        user.ReadDataFromJSON(respone);

        System.out.println(user.GetInsertParameters());

    }


}

package Main;

import Models.ConfigD;
import Models.User;
import Services.SQLService.DataRow;
import Services.SQLService.DataTable;
import Services.SQLService.SQLService;

import java.util.Dictionary;
import java.util.Hashtable;

public class TestMain {

    public static void main(String[] args) {
        SQLService service = new SQLService(new ConfigD());

        String query = "PROJEKT.DeleteUser";

        User user = new User(4,"Nowy","Hasloedytowany","emeilo");

        System.out.println(user.GetInsertParameters());


        DataTable result = service.ExecuteStoredProcedureWithResult(query,user.GetDeleteParameters());

    }


}

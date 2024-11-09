package Main;

import Models.ConfigD;
import Services.SQLService.DataRow;
import Services.SQLService.DataTable;
import Services.SQLService.SQLService;

import java.util.Dictionary;
import java.util.Hashtable;

public class TestMain {

    public static void main(String[] args) {
        SQLService service = new SQLService(new ConfigD());

        String query = "PROJEKT.GetUsers";
        Dictionary<String,Object> params = new Hashtable<>();

        DataTable result = service.ExecuteStoredProcedureWithResult(query,params);

        for(DataRow row : result.GetRows()){
            String id = row.getFieldValue("PUS_UserName");
            System.out.println(id);
        }

    }


}

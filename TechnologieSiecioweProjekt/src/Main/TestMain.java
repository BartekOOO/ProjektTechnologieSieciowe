package Main;

import Models.ConfigD;
import Services.DataRow;
import Services.DataTable;
import Services.SQLService;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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

package Services.SQLService;


import java.util.ArrayList;
import java.util.List;

public class DataTable {
    private List<DataRow> Rows;

    public DataTable(){
        Rows = new ArrayList<>();
    }

    public void AddDataRow(DataRow row){
        Rows.add(row);
    }

    public List<DataRow> GetRows(){
        return Rows;
    }
}

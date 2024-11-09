package Services.SQLService;

import java.util.ArrayList;
import java.util.List;

public class DataRow{
    private List<Object> fields;
    private List<String> fieldNames;

    public DataRow(){
        fields = new ArrayList<>();
        fieldNames = new ArrayList<>();
    }

    public <T> T getFieldValue(String fieldName){
        for(int i=0;i<fields.size();i++){
            if(fieldName.equals(fieldNames.get(i))){
                Object field = fields.get(i);
                return (T)field;
            }
        }
        return null;
    }

    public void AddField(String fieldName,Object object){
        fieldNames.add(fieldName);
        fields.add(object);
    }
}

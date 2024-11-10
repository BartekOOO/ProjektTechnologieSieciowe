package Models;

import Interfaces.IData;

import java.util.Dictionary;
import java.util.Hashtable;

public class EmptyData implements IData {
    @Override
    public String GetClassName() {
        return "EmptyData";
    }

    @Override
    public String ToJSONBody() {
        StringBuilder result = new StringBuilder("{"+ "\n");
        result.append("}");
        return result.toString();
    }

    @Override
    public void ReadDataFromJSON(String JSONData) {

    }

    @Override
    public Dictionary<String, Object> GetDeleteParameters() {
        return new Hashtable<>();
    }

    @Override
    public Dictionary<String, Object> GetInsertParameters() {
        return new Hashtable<>();
    }

    @Override
    public Dictionary<String, Object> GetUpdateParameters() {
        return new Hashtable<>();
    }
}

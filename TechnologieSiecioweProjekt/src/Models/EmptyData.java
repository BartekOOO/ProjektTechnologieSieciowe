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


}

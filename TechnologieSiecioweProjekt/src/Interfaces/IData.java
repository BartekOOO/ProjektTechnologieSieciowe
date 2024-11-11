package Interfaces;

import Models.ResponseData;

public interface IData {
    String GetClassName();
    String ToJSONBody();
    void ReadDataFromJSON(String JSONData);
}

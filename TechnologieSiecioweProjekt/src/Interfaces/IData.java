package Interfaces;

import Models.ResponseData;

public interface IData extends IInsertData, IDeleteData, IUpdateData {
    String GetClassName();
    String ToJSONBody();
    void ReadDataFromJSON(String JSONData);
}

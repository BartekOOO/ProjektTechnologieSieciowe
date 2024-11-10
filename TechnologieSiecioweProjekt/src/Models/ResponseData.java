package Models;

import Interfaces.IData;

public class ResponseData {
    private ResponseCode code;
    private IData JSONResponse;
    private String Status;

    public ResponseData(ResponseCode code,IData JSONResponse, String Status){
        this.code = code;
        this.JSONResponse = JSONResponse;
        this.Status = Status;
    }

    public String ToJSONBody(){
        StringBuilder result = new StringBuilder("{");
        result.append("\"code\":").append(this.code.ordinal()).append(",");
        result.append("\"className\":\"").append(this.JSONResponse.GetClassName()).append("\",");
        result.append("\"data\":").append(this.JSONResponse.ToJSONBody()).append(",");
        result.append("\"status\":\"").append(this.Status).append("\"");
        result.append("}");
        return result.toString();
    }

}

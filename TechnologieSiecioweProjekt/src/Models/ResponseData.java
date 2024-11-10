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
        StringBuilder result = new StringBuilder("{"+ "\n");
        result.append("\"code\":").append(this.code.ordinal()).append(","+ "\n");
        result.append("\"className\":\"").append(this.JSONResponse.GetClassName()).append("\","+ "\n");
        result.append("\"data\":").append(this.JSONResponse.ToJSONBody()).append(","+ "\n");
        result.append("\"status\":\"").append(this.Status).append("\""+ "\n");
        result.append("}");
        return result.toString();
    }

}

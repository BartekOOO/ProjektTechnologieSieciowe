package Models;

import Interfaces.IData;

public class RequestData {
    public Method method;
    public String token;
    public String className;
    public IData data;

    public RequestData(){

    }

    public RequestData(Method method, String token, IData data){
        this.method = method;
        this.token = token;
        this.data = data;
        this.className = data.GetClassName();
    }

    public String ToJSONBody() {
        StringBuilder result = new StringBuilder("{");
        result.append("\"method\":").append(method.ordinal()).append(",");
        result.append("\"token\":\"").append(token).append("\",");
        result.append("\"data\":").append(data.ToJSONBody()).append(",");
        result.append("\"className\":\"").append(className).append("\"");
        result.append("}");
        return result.toString();
    }
}

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

    @Override
    public String toString() {
        return "Models.RequestData{" +
                "method=" + method +
                ", loginToken='" + token + '\'' +
                ", query='" + data.toString() + '\'' +
                '}';
    }
}

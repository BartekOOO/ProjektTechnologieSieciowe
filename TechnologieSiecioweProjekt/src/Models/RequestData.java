package Models;

import Interfaces.IData;

public class RequestData {
    public Method method;
    public int id;
    public String token;
    public String className;
    public IData data;

    public RequestData(){

    }

    public RequestData(int id, Method method, String token, IData data){
        this.id = id;
        this.method = method;
        this.token = token;
        this.data = data;
        this.className = data.GetClassName();
    }

    @Override
    public String toString() {
        return "Models.RequestData{" +
                "method=" + method +
                ", id=" + id +
                ", loginToken='" + token + '\'' +
                ", query='" + data.toString() + '\'' +
                '}';
    }
}

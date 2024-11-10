package Models;

import Interfaces.IData;
import Services.JSONService.JSONService;

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
        StringBuilder result = new StringBuilder("{"+ "\n");
        result.append("\"method\":").append(method.ordinal()).append(","+ "\n");
        result.append("\"token\":\"").append(token).append("\","+ "\n");
        result.append("\"data\":").append(data.ToJSONBody()).append(","+ "\n");
        result.append("\"className\":\"").append(className).append("\""+ "\n");
        result.append("}");
        return result.toString();
    }

    public void ReadDataFromJSON(String data) {
        JSONService JSONBody = new JSONService(data);

        this.token = JSONBody.GetJSONFieldValue("token");
        this.className = JSONBody.GetJSONFieldValue("className");
        this.token = JSONBody.GetJSONFieldValue("token");
        String methodString = JSONBody.GetJSONFieldValue("method");
        String dataString = JSONBody.GetJSONFieldValue("data");

        try{
            this.method = Method.fromInt(Integer.parseInt(methodString));
        }catch (Exception ex){
            this.method = Method.Post;
        }

        try {
            if (className == null || className.isEmpty()) {
                throw new IllegalArgumentException("className cannot be null or empty");
            }

            Class<?> newClass = Class.forName("Models."+className);

            if (!IData.class.isAssignableFrom(newClass)) {
                throw new IllegalArgumentException("Class does not implement IData: " + className);
            }

            IData dataResult = (IData) newClass.getConstructor().newInstance();

            dataResult.ReadDataFromJSON(data);

            this.data = dataResult;


        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | IllegalArgumentException e) {
            System.err.println("Błąd przy ładowaniu klasy lub rzutowaniu: " + e.getMessage());
        } catch (Exception ex) {
            System.err.println("Inny błąd: " + ex.getMessage());
        }

    }
}

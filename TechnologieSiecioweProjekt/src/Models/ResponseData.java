package Models;

import Interfaces.IData;
import Services.JSONService.JSONService;
import com.sun.net.httpserver.Authenticator;

public class ResponseData {
    private ResponseCode code;
    private String ClassName;
    private IData JSONResponse;
    private String Status;

    public ResponseData(ResponseCode code,String ClassName,IData JSONResponse, String Status){
        this.code = code;
        this.ClassName = ClassName;
        this.JSONResponse = JSONResponse;
        this.Status = Status;
    }

    public ResponseData(){
        code = ResponseCode.OK;
        JSONResponse = new EmptyData();
        Status = "Success";
        ClassName =JSONResponse.GetClassName();
    }

    public void ReadDataFromJSON(String data) {
        JSONService JSONBody = new JSONService(data);

        this.Status = JSONBody.GetJSONFieldValue("status");

        String codeString = JSONBody.GetJSONFieldValue("code");
        String dataString = JSONBody.GetJSONFieldValue("data");

        try{
            this.code = ResponseCode.fromInt(Integer.parseInt(codeString));
        }catch (Exception ex){
            this.code = ResponseCode.NOT_FOUND;
        }

        try {
            if (ClassName == null || ClassName.isEmpty()) {
                throw new IllegalArgumentException("ClassName cannot be null or empty");
            }

            Class<?> newClass = Class.forName("Models."+ClassName);

            if (!IData.class.isAssignableFrom(newClass)) {
                throw new IllegalArgumentException("Class does not implement IData: " + ClassName);
            }

            IData dataResult = (IData) newClass.getConstructor().newInstance();

            dataResult.ReadDataFromJSON(data);

            this.JSONResponse = dataResult;


        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | IllegalArgumentException e) {
            System.err.println("Błąd przy ładowaniu klasy lub rzutowaniu: " + e.getMessage());
        } catch (Exception ex) {
            System.err.println("Inny błąd: " + ex.getMessage());
        }

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

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public void setJSONResponse(IData JSONResponse) {
        this.JSONResponse = JSONResponse;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

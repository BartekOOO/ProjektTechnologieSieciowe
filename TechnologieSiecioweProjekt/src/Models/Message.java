package Models;

import Interfaces.IData;

public class Message implements IData {
    public String message;

    @Override
    public String GetClassName() {
        return "Models.Message";
    }

    @Override
    public String ToJSONBody() {
        return null;
    }
}

package Models;

public enum Method {
    Post(0),
    Put(1),
    Delete(2),
    LogIn(3),
    LogOut(4),
    GetList(5),
    Get(6);

    private int value;

    Method(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Method fromInt(int value) {
        for (Method status : Method.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("");
    }
}

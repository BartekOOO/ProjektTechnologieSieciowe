package Models;

public enum ResponseCode {
    OK(200, "OK"),
    CREATED(201, "Utworzono"),
    ACCEPTED(202, "Zaakceptowano"),

    BAD_REQUEST(400, "Złe żądanie"),
    UNAUTHORIZED(401, "Nieautoryzowany"),
    FORBIDDEN(403, "Zabronione"),
    NOT_FOUND(404, "Nie znaleziono"),

    INTERNAL_SERVER_ERROR(500, "Błąd serwera wewnętrznego"),
    NOT_IMPLEMENTED(501, "Nie zaimplementowano"),
    BAD_GATEWAY(502, "Zły brama"),
    SERVICE_UNAVAILABLE(503, "Usługa niedostępna");

    private final int code;
    private final String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return code + " " + description;
    }
}

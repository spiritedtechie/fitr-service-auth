package resource.model;

public enum ErrorCode {

    BAD_REQUEST(400), UNAUTHORIZED(401), NOT_FOUND(404), CONFLICT(409);

    private final int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
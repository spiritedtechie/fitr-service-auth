package resource.model;

public enum ErrMsg {
    SIGNUP_FAILED(ErrorCode.BAD_REQUEST),
    AUTH_FAILED(ErrorCode.BAD_REQUEST),
    INVALID_TOKEN(ErrorCode.BAD_REQUEST);

    private final ErrorCode value;

    ErrMsg(ErrorCode value) {
        this.value = value;
    }

    public ErrorCode getValue() {
        return value;
    }
}

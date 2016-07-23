package resource.model.response;

public enum ErrMsg {
    SIGNUP_FAILED(ErrorCode.BAD_REQUEST),
    LOGIN_FAILED(ErrorCode.BAD_REQUEST);

    private final ErrorCode value;

    ErrMsg(ErrorCode value) {
        this.value = value;
    }

    public ErrorCode getValue() {
        return value;
    }
}

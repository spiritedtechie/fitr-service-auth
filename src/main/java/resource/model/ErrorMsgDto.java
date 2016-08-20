package resource.model;

public class ErrorMsgDto {

    private String error;

    public ErrorMsgDto(String errorMessage) {
        error = errorMessage;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

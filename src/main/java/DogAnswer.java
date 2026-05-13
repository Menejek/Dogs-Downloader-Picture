import com.fasterxml.jackson.annotation.JsonProperty;

public class DogAnswer {

    String status;
    String message;
    String code;

    public DogAnswer(@JsonProperty("status") String status,
                     @JsonProperty("message") String message,
                     @JsonProperty("code") String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "DogAnswer{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

package trademap.com.post_challenge.infra.exceptionHandlers.ResponseAPIRest;

import java.time.Instant;

public class ResponseAPIRestBuilder {

    Instant timestamp;
    int code;
    String name;
    String error;

    private ResponseAPIRestBuilder() {

    }

    public static ResponseAPIRestBuilder aResponseAPIRest() {
        ResponseAPIRestBuilder responseAPIRestBuilder = new ResponseAPIRestBuilder();
        defaultData(responseAPIRestBuilder);
        return responseAPIRestBuilder;
    }

    private static void defaultData(ResponseAPIRestBuilder responseAPIRestBuilder){
        responseAPIRestBuilder.code = 404;
        responseAPIRestBuilder.error = "NOT FOUND";
        responseAPIRestBuilder.name = "NOT FOUND";
        responseAPIRestBuilder.timestamp = Instant.now();
    }

    public ResponseAPIRestBuilder withCode(int code){
        this.code = code;
        return this;
    }

    public ResponseAPIRestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ResponseAPIRestBuilder withTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ResponseAPIRestBuilder withError(String error) {
        this.error = error;
        return this;
    }

    public ResponseAPIRest build(){
        return new ResponseAPIRest(timestamp, name, code, error);
    }
}

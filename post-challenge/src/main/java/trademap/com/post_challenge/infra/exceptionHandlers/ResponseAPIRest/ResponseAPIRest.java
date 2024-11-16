package trademap.com.post_challenge.infra.exceptionHandlers.ResponseAPIRest;

import java.time.Instant;

public record ResponseAPIRest (
    Instant timestamp,
    String name,
    int code,
    String error
) {
}

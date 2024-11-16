package trademap.com.post_challenge.controllers.DTOs.response;

import java.time.Instant;
import java.time.LocalDateTime;

public record ResponsePostDTO(
    String id,
    String title,
    String description,
    String body,
    Instant createdAt,
    Instant updatedAt) {
}

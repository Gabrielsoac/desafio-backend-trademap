package trademap.com.post_challenge.controllers.DTOs.response;

import java.util.Set;

public record ResponsePageblePostDTO (
    Set<ResponsePostDTO> postContent,
    int number,
    int size,
    int totalPages,
    int totalElements,
    int numberOfElements,
    boolean last,
    boolean first,
    boolean empty,
    boolean sorted,
    boolean unsorted
    )
{}

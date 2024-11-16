package trademap.com.post_challenge.controllers;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import trademap.com.post_challenge.controllers.DTOs.request.RequestPostDTO;
import trademap.com.post_challenge.controllers.DTOs.request.RequestUpdatePostDTO;
import trademap.com.post_challenge.controllers.DTOs.response.ResponsePageblePostDTO;
import trademap.com.post_challenge.controllers.DTOs.response.ResponsePostDTO;
import trademap.com.post_challenge.domain.entities.Post;
import trademap.com.post_challenge.domain.exceptions.DateFormatException;
import trademap.com.post_challenge.services.PostService;
import trademap.com.post_challenge.services.Impl.PostServiceImpl;

import java.net.URI;
import java.sql.Timestamp;
import java.time.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;

    public PostController(PostServiceImpl postService){
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<ResponsePostDTO> createPost(@RequestBody RequestPostDTO data){
        
        Post post = postService.createPost(data.title(), data.description(), data.body());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).body(createResponsePostDTO(post));
    }

    @GetMapping
    public ResponseEntity<ResponsePageblePostDTO> getAllPosts(
            @RequestParam(required = false) Optional<LocalDate> initialDate,
            @RequestParam(required = false) Optional<LocalDate> finalDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Instant initialDateFormattedInstant = (initialDate.isEmpty()) ? null :
                initialDate.get().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant finalDateFormattedInstant = (finalDate.isEmpty()) ? null :
                finalDate.get().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant();

        Page<Post> posts = postService.getAllPosts(initialDateFormattedInstant, finalDateFormattedInstant, pageable);
        ResponsePageblePostDTO postsDTO = createResponsePageablePostDTO(posts);
        return ResponseEntity.ok().body(postsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePostDTO> getPost(@PathVariable String id){
        Post post = postService.getPost(id);

        return ResponseEntity.ok().body(createResponsePostDTO(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePostDTO> updatePost(@PathVariable String id, @RequestBody RequestUpdatePostDTO data){

        Post post = postService.getPost(id);

        Post updatedPost = postService.updatePost(post, data.title(), data.description(), data.body());
        return ResponseEntity.ok().body(createResponsePostDTO(updatedPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id){
        Post post = postService.getPost(id);
        postService.deletePost(post);

        return ResponseEntity.ok().build();
    }

    private ResponsePostDTO createResponsePostDTO(Post post){
        return new ResponsePostDTO(
            post.getId(),
            post.getTitle(),
            post.getDescription(),
            post.getBody(),
            post.getCreatedAt(),
            post.getUpdatedAt());
    }
    private ResponsePageblePostDTO createResponsePageablePostDTO(Page<Post> postsPageable){
        Set<ResponsePostDTO> postContent = new HashSet<>();
        for (Post post : postsPageable.getContent()){
            ResponsePostDTO postDTO = createResponsePostDTO(post);
            postContent.add(postDTO);
        }
        int number = postsPageable.getNumber();
        int size = postsPageable.getSize();
        int totalPages = postsPageable.getTotalPages();
        int totalElements = postsPageable.getNumberOfElements();
        int numberOfElements = postsPageable.getNumberOfElements();
        boolean last = postsPageable.isLast();
        boolean first = postsPageable.isFirst();
        boolean empty = postsPageable.isEmpty();
        boolean sorted = postsPageable.getSort().isSorted();
        boolean unsorted = postsPageable.getSort().isUnsorted();

        return new ResponsePageblePostDTO(postContent, number, size, totalPages, totalElements, numberOfElements,
                last, first, empty, sorted, unsorted);
    }
}

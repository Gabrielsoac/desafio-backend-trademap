package trademap.com.post_challenge.services.Impl;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import trademap.com.post_challenge.domain.entities.Post;
import trademap.com.post_challenge.domain.exceptions.DateFormatException;
import trademap.com.post_challenge.domain.exceptions.PostNotFoundException;
import trademap.com.post_challenge.repositories.PostRepository;
import trademap.com.post_challenge.services.PostService;

@Service
public class PostServiceImpl implements PostService{
    
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(String title, String description, String body){

        validateData(title, body);

        if(description == null) {
            description = "";
        }

        Post post = new Post(title, description, body);

        postRepository.save(post);

        return post;
    }

    public Post getPost(String id){

        Optional<Post> postOptional = postRepository.findById(id);
        
        if(postOptional.isPresent()){
            return postOptional.get();
        }

        throw new PostNotFoundException("Post not found");
    }

    @Override
    public Page<Post> getAllPosts(Instant initialDate, Instant finalDate, Pageable pageable) {
        return postRepository.findAllByCreatedAtBetween(initialDate, finalDate, pageable);
    }
    
    @Override
    @Transactional
    public Post updatePost(Post post, String title, String description, String body) {

        validateData(title, body);

        if(description == null) {
            description = "";
        }

        if (!post.getTitle().equals(title)){
            post.setTitle(title);
            post.setUpdatedAtNow();
        }

        if(!post.getDescription().equals(description)){
            post.setDescription(description);
            post.setUpdatedAtNow();
        }

        if(!post.getBody().equals(body)){
            post.setBody(body);
            post.setUpdatedAtNow();
        }
        return post; 
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    private void validateData(String title, String body){
        if (title == null) throw new IllegalArgumentException("Title cannot be null");
        if (title.isEmpty()) throw new IllegalArgumentException("Title cannot be empty");

        if (body == null) throw new IllegalArgumentException("Body cannot be null");
        if (body.isEmpty()) throw new IllegalArgumentException("Body cannot be empty");
    }
}

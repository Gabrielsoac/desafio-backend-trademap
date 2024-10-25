package trademap.com.post_challenge.services;

import org.springframework.stereotype.Service;

import trademap.com.post_challenge.entities.Post;
import trademap.com.post_challenge.repositories.PostRepository;

@Service
public class PostService {
    
    private PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Post createPost(String title, String description, String body){

        if (title == null) throw new IllegalArgumentException("Title cannot be null");
        if (title.isEmpty()) throw new IllegalArgumentException("Title cannot be empty");

        if (description == null) throw new IllegalArgumentException("Description cannot be null");
        if (description.isEmpty()) throw new IllegalArgumentException("Description cannot be empty");
        
        if (body == null) throw new IllegalArgumentException("Body cannot be null");
        if (body.isEmpty()) throw new IllegalArgumentException("Body cannot be empty");

        Post post = new Post(title, description, body);

        postRepository.save(post);

        return post;
    }

    

}

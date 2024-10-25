package trademap.com.post_challenge.services.Impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import trademap.com.post_challenge.domain.entities.Post;
import trademap.com.post_challenge.domain.exceptions.PostNotFoundException;
import trademap.com.post_challenge.repositories.PostRepository;
import trademap.com.post_challenge.services.PostService;

@Service
public class PostServiceImpl implements PostService{
    
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
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

    public Post getPost(String id){

        Optional<Post> postOptional = postRepository.findById(id);
        
        if(postOptional.isPresent()){
            return postOptional.get();
        }

        throw new PostNotFoundException("Post not found!");
    }

    @Override
    public Post updatePost(String id, String title, String description, String body) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePost'");
    }

    @Override
    public void deletePost(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }

    

}

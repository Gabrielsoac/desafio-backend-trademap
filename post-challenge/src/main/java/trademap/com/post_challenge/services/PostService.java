package trademap.com.post_challenge.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import trademap.com.post_challenge.domain.entities.Post;

public interface PostService {

    Post createPost(String title, String description, String body);
    Post getPost(String id);
    Page<Post> getAllPosts(Pageable pageable);
    Post updatePost(Post post, String title, String description, String body);
    void deletePost(Post post);   
}

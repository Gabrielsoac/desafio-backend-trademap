package trademap.com.post_challenge.services;

import trademap.com.post_challenge.domain.entities.Post;

public interface PostService {

    Post createPost(String title, String description, String body);
    Post getPost(String id);
    Post updatePost(String id, String title, String description, String body);
    void deletePost(String id);   
}

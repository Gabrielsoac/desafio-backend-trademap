package trademap.com.post_challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import trademap.com.post_challenge.entities.Post;

public interface PostRepository extends JpaRepository<Post, String> {
}

package trademap.com.post_challenge.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import trademap.com.post_challenge.domain.entities.Post;

public interface PostRepository extends JpaRepository<Post, String> {

    Page<Post>findAll(Pageable pageable);
}

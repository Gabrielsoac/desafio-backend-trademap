package trademap.com.post_challenge.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import trademap.com.post_challenge.domain.entities.Post;

import java.time.Instant;

public interface PostRepository extends JpaRepository<Post, String> {

    @Query("SELECT p FROM Post p " +
            "WHERE (COALESCE(:initialDate, NULL) IS NULL OR p.createdAt >= :initialDate) " +
            "AND (COALESCE(:finalDate, NULL) IS NULL OR p.createdAt <= :finalDate)")
    Page<Post> findAllByCreatedAtBetween(
            @Param("initialDate") Instant initialDate,
            @Param("finalDate") Instant finalDate,
            Pageable pageable);
}

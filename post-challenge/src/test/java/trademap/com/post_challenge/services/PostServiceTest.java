package trademap.com.post_challenge.services;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import trademap.com.post_challenge.builders.PostBuilder;
import trademap.com.post_challenge.domain.entities.Post;
import trademap.com.post_challenge.domain.exceptions.PostNotFoundException;
import trademap.com.post_challenge.repositories.PostRepository;
import trademap.com.post_challenge.services.Impl.PostServiceImpl;

@SpringBootTest
public class PostServiceTest {
    
    PostRepository postRepository;
    PostService postService;

    @BeforeEach
    void setUp(){
        postRepository = Mockito.mock(PostRepository.class);
        postService = new PostServiceImpl(postRepository);
    }

    @Test
    @DisplayName("Should to create a new post with sucess")
    void test(){

        Post post = postService.createPost("Test", "TestDescription", "Lorem Ipsum");

        Assertions.assertAll("Post Data",
        () -> Assertions.assertEquals("Test", post.getTitle()),
        () -> Assertions.assertEquals("TestDescription", post.getDescription()),
        () -> Assertions.assertEquals("Lorem Ipsum", post.getBody()));
    }

    @ParameterizedTest
    @MethodSource(value = "getInvalidData")
    @DisplayName("Should to refuse create a new Post when data is invalid")
    void teste1(String title, String description, String body, String expectedMessage){

        String message = Assertions.assertThrows(IllegalArgumentException.class,
        () -> postService.createPost(title, description, body)).getMessage();

        Assertions.assertEquals(expectedMessage, message);
    }

    private static Stream<Arguments> getInvalidData(){
        return Stream.of(
            Arguments.of(null, "Description", "Lorem Ipsum", "Title cannot be null"),
            Arguments.of("", "Description", "Lorem Ipsum", "Title cannot be empty"),
            Arguments.of("Title", "Description", null, "Body cannot be null"),
            Arguments.of("Title", "Description", "", "Body cannot be empty"));
    }

    @Test
    @DisplayName("Should to get a post with sucess")
    void test2(){

        Mockito.when(postRepository.findById(Mockito.anyString()))
        .thenReturn(Optional.of(PostBuilder.aBuilder().build()));

        Post post = postService.getPost("1");

        Assertions.assertAll("Post Data",
        () -> Assertions.assertEquals("Title Test", post.getTitle()),
        () -> Assertions.assertEquals("Description Test", post.getDescription()),
        () -> Assertions.assertEquals("Body Test", post.getBody()),
        () -> Assertions.assertEquals("1", post.getId()));
    }

    @Test
    @DisplayName("Should to throw exception when post not found")
    void test3(){

        Mockito.when(postRepository.findById(Mockito.anyString()))
        .thenReturn(Optional.empty());

        Assertions.assertThrows(PostNotFoundException.class,
        () -> postService.getPost("1"));
    }

    @Test
    @DisplayName("Should to update a Post with sucess")
    void test4(){

        Post beforePost = PostBuilder.aBuilder().build(); 

        Post post = postService.updatePost(
            beforePost,
            "Updated title",
            "Updated description",
            "Updated body");

            Assertions.assertAll("Post Data",
            () -> Assertions.assertEquals("Updated title", post.getTitle()),
            () -> Assertions.assertEquals("Updated description", post.getDescription()),
            () -> Assertions.assertEquals("Updated body", post.getBody()),
            () -> Assertions.assertInstanceOf(Instant.class, post.getUpdatedAt()));
    }

    @Test
    @DisplayName("Should to delete a post with sucess")
    void test5(){

        Post post = PostBuilder.aBuilder().build();

        postService.deletePost(post);

        Mockito.verify(postRepository, Mockito.times(1))
        .delete(Mockito.any(Post.class));
    }
}

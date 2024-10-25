package trademap.com.post_challenge.services;

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

import trademap.com.post_challenge.entities.Post;
import trademap.com.post_challenge.repositories.PostRepository;

@SpringBootTest
public class PostServiceTest {
    
    PostRepository postRepository;
    PostService postService;

    @BeforeEach
    void setUp(){
        postRepository = Mockito.mock(PostRepository.class);
        postService = new PostService(postRepository);
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
            Arguments.of("Title", null, "Lorem Ipsum", "Description cannot be null"),
            Arguments.of("Title", "", "Lorem Ipsum", "Description cannot be empty"),
            Arguments.of("Title", "Description", null, "Body cannot be null"),
            Arguments.of("Title", "Description", "", "Body cannot be empty"));
    }

}

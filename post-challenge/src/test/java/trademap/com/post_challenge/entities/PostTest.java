package trademap.com.post_challenge.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import trademap.com.post_challenge.builders.PostBuilder;

@SpringBootTest
public class PostTest {

    @Test
    @DisplayName("Should to create a Post with sucess")
    void test1(){

        Post post = PostBuilder.aBuilder().build();

        Assertions.assertAll("Post data",
        () -> Assertions.assertEquals("Body Test", post.getBody()),
        () -> Assertions.assertEquals("Description Test", post.getDescription()),
        () -> Assertions.assertEquals("1", post.getId())
        );
    } 

    @Test
    @DisplayName("Should to refuse update title when data is empty or null")
    void test2(){

        Post post = PostBuilder.aBuilder().build();
        String messageEmpty = Assertions.assertThrows(IllegalArgumentException.class,
        () -> post.setTitle("")).getMessage();

        String messageNull = Assertions.assertThrows(IllegalArgumentException.class,
        () -> post.setTitle(null)).getMessage();

        Assertions.assertEquals("Title cannot be empty", messageEmpty);
        Assertions.assertEquals("Title cannot be null", messageNull);
    }

    @Test
    @DisplayName("Should to refuse update description when data is empty or null")
    void test3(){

        Post post = PostBuilder.aBuilder().build();
        String messageEmpty = Assertions.assertThrows(IllegalArgumentException.class,
        () -> post.setDescription("")).getMessage();

        String messageNull = Assertions.assertThrows(IllegalArgumentException.class,
        () -> post.setDescription(null)).getMessage();

        Assertions.assertEquals("Description cannot be empty", messageEmpty);
        Assertions.assertEquals("Description cannot be null", messageNull);
    }

    @Test
    @DisplayName("Should to refuse update body when data is empty or null")
    void test4(){

        Post post = PostBuilder.aBuilder().build();
        String messageEmpty = Assertions.assertThrows(IllegalArgumentException.class,
        () -> post.setBody("")).getMessage();

        String messageNull = Assertions.assertThrows(IllegalArgumentException.class,
        () -> post.setBody(null)).getMessage();

        Assertions.assertEquals("Body cannot be empty", messageEmpty);
        Assertions.assertEquals("Body cannot be null", messageNull);
    }
}

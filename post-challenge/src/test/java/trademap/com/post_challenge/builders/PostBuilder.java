package trademap.com.post_challenge.builders;

import java.time.Instant;

import trademap.com.post_challenge.domain.entities.Post;

public class PostBuilder {
    
    private String id;
    private String title;
    private String description;
    private String body;
    private Instant createdAt;
    private Instant updatedAt;

    private PostBuilder(){

    }

    public static PostBuilder aBuilder(){

        PostBuilder postBuilder = new PostBuilder();
        return setDefaultData(postBuilder);
    }

    private static PostBuilder setDefaultData(PostBuilder postBuilder){
        postBuilder.body = "Body Test";
        postBuilder.createdAt = Instant.now();
        postBuilder.description = "Description Test";
        postBuilder.id = "1";
        postBuilder.title = "Title Test";
        return postBuilder;
    }

    public PostBuilder withId(String id){
        this.id = id;
        return this;
    }

    public PostBuilder withTitle(String title){
        this.title = title;
        return this;
    }

    public PostBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public PostBuilder withBody(String body){
        this.body = body;
        return this;
    }

    public PostBuilder withCreatedAt(Instant createdAt){
        this.createdAt = createdAt;
        return this;
    }

    public PostBuilder withUpdateAt(Instant updatedAt){
        this.updatedAt = updatedAt;
        return this;
    }

    public Post build(){
        return new Post(id, title, description, body,createdAt,updatedAt);
    }
}

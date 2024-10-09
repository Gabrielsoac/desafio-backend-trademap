package trademap.com.post_challenge.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "post")
@Table(name = "tb_post")
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    @Column(name = "post_description")
    private String description;

    private String body;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public Post(String id, String title, String description, String body, Instant createdAt, Instant updatedAt) {
        
        this.id = id;
        this.title = title;
        this.description = description;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Post(String title, String description, String body) {
        
        this.title = title;
        this.description = description;
        this.body = body;
        this.createdAt = Instant.now();
    }

    public Post() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null) throw new IllegalArgumentException("Title cannot be null");
        if(title.isEmpty()) throw new IllegalArgumentException("Title cannot be empty");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null) throw new IllegalArgumentException("Description cannot be null");
        if(description.isEmpty()) throw new IllegalArgumentException("Description cannot be empty");
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        if(body == null) throw new IllegalArgumentException("Body cannot be null");
        if(body.isEmpty()) throw new IllegalArgumentException("Body cannot be empty");
        this.body = body;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAtNow() {
        this.updatedAt = Instant.now();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

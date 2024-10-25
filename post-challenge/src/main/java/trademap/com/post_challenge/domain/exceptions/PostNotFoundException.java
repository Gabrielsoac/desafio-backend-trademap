package trademap.com.post_challenge.domain.exceptions;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String message){
        super(message);
    }
}

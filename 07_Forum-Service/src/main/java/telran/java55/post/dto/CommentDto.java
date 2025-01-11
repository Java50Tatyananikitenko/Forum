package telran.java55.post.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
	@Setter
	String user;
	String message;
	LocalDateTime dateCreated;
	@Builder.Default
    private Integer likes = 0;

}
//{
//    "user": "Stranger",
//    "message": "Awesome!!!",
//    "dateCreated": "2021-12-15T14:11:55",
//    "likes": 0
//}

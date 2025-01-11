package telran.java55.post.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "user", "dateCreated" })
public class Comment {
	String user;
	String message;
	LocalDateTime dateCreated = LocalDateTime.now();
	int likes = 0;
}

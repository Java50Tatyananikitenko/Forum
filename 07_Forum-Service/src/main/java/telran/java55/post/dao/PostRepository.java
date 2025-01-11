package telran.java55.post.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.java55.post.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	@Query("{ 'author': ?0 }")
    List<Post> findByAuthor(String author);

    @Query("{ 'tags': { $in: ?0 } }")
    List<Post> findByTags(Set<String> tags);

    @Query("{ 'dateCreated': { $gte: ?0, $lte: ?1 } }")
    List<Post> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
}

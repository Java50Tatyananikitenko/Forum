package telran.java55.post.service;

import java.util.List;
import java.util.Set;

import telran.java55.post.dto.CommentDto;
import telran.java55.post.dto.NewPostDto;
import telran.java55.post.dto.PostDto;

public interface PostService {

	PostDto addNewPost(String author, NewPostDto newPostDto);

	PostDto finfPostById(String id);

	PostDto removePost(String id);

	PostDto updatePost(String id, NewPostDto newPostDto);

	PostDto addComment(String id, CommentDto commentDto);

	PostDto addLike(String id);

	List<PostDto> getPostsByAuthor(String author);

	List<PostDto> getPostsByTags(Set<String> tags);

	List<PostDto> getPostsByPeriod(String startDate, String endDate);

}

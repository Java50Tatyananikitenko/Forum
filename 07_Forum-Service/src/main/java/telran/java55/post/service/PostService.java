package telran.java55.post.service;

import telran.java55.post.dto.NewPostDto;
import telran.java55.post.dto.PostDto;

public interface PostService {

	PostDto addNewPost(String author, NewPostDto newPostDto);

	PostDto findPostById(String id);

	PostDto removePost(String id);

	PostDto updatePost(String id, NewPostDto newPostDto);

}

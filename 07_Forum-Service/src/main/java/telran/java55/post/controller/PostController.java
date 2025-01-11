package telran.java55.post.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java55.post.dto.CommentDto;
import telran.java55.post.dto.NewPostDto;
import telran.java55.post.dto.PeriodRequest;
import telran.java55.post.dto.PostDto;
import telran.java55.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {
	
	final PostService postService ;
	
	@PostMapping("/post/{author}")
	public PostDto addNewPost(@PathVariable String author, @RequestBody NewPostDto newPostDto) {
		return postService.addNewPost(author,newPostDto);
	}
	
	@GetMapping("/post/{id}")
	public PostDto findPostById(@PathVariable String id) {
		return postService.finfPostById(id);
	}
	
	@DeleteMapping("/post/{id}")
	public PostDto removePostBy(@PathVariable String id) {
		return postService.removePost(id);
	}
	
	@PutMapping("/post/{id}")
	public PostDto updatePost(@PathVariable String id,NewPostDto newPostDto) {
		return postService.updatePost(id,newPostDto);  
	}
	 @PutMapping("/post/{id}/comment/{author}")
	    public PostDto addComment(@PathVariable String id, @PathVariable String author, @RequestBody CommentDto commentDto) {
	        commentDto.setUser(author);
	        return postService.addComment(id, commentDto);
	    }

	    @PutMapping("/post/{id}/like")
	    public PostDto addLike(@PathVariable String id) {
	        return postService.addLike(id);
	    }

	    @GetMapping("/posts/author/{author}")
	    public List<PostDto> getPostsByAuthor(@PathVariable String author) {
	        return postService.getPostsByAuthor(author);
	    }

	    @PostMapping("/posts/tags")
	    public List<PostDto> getPostsByTags(@RequestBody Set<String> tags) {
	        return postService.getPostsByTags(tags);
	    }

	    @PostMapping("/posts/period")
	    public List<PostDto> getPostsByPeriod(@RequestBody PeriodRequest periodRequest) {
	        return postService.getPostsByPeriod(periodRequest.getDateFrom(), periodRequest.getDateTo());
	    }

}

package telran.java55.post.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java55.post.dao.PostRepository;
import telran.java55.post.dto.CommentDto;
import telran.java55.post.dto.NewPostDto;
import telran.java55.post.dto.PostDto;
import telran.java55.post.dto.exceptions.PostNotFoundException;
import telran.java55.post.model.Comment;
import telran.java55.post.model.Post;
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	
	final ModelMapper modelMapper;
	final PostRepository postRepository;

	@Override
	public PostDto addNewPost(String author, NewPostDto newPostDto) {
		Post post = new Post(newPostDto.getTitle(), newPostDto.getContent(), author, newPostDto.getTags());
		post = postRepository.save(post);
		return modelMapper.map(post, PostDto.class) ;
	}

	@Override
	public PostDto finfPostById(String id) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto removePost(String id) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		postRepository.delete(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(String id,NewPostDto newPostDto) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		String content = newPostDto.getContent();
		if(content!=null) {
			post.setContent(content);
		}
		String title = newPostDto.getTitle();
		if(title!=null) {
			post.setTitle(title);
		}
		Set<String>tags = newPostDto.getTags();
		if(tags!=null) {
			tags.forEach(post::addTag);
		}
		post = postRepository.save(post);
		
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto addComment(String id, CommentDto commentDto) {
		 Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
	        Comment comment = new Comment(commentDto.getUser(), commentDto.getMessage(), commentDto.getDateCreated(),commentDto.getLikes());
	        comment.setLikes(commentDto.getLikes());
	        post.addComment(comment);
	        postRepository.save(post);
	        return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto addLike(String id) {
		 Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
	        post.addLike();
	        postRepository.save(post);
	        return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByAuthor(String author) {
		return postRepository.findByAuthor(author).stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostsByTags(Set<String> tags) {
		 return postRepository.findByTags(tags).stream()
	                .map(post -> modelMapper.map(post, PostDto.class))
	                .collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostsByPeriod(String startDate, String endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime start = LocalDate.parse(startDate, formatter).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endDate, formatter).atTime(23, 59, 59);
        return postRepository.findByDateCreatedBetween(start, end).stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
	}

	

	

}

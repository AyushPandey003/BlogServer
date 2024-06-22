package com.BlogApp.BlogServer.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.BlogServer.entity.Comment;
import com.BlogApp.BlogServer.entity.Post;
import com.BlogApp.BlogServer.repository.CommentRepository;
import com.BlogApp.BlogServer.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    public Comment createComment(Long postId, String postedBy,String content){
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()){

            Comment comment=new Comment();

            comment.setPost(optionalPost.get());
            comment.setContent(content);
            comment.setPostedBy(postedBy);

            comment.setCreatedAt(new Date());
            return commentRepository.save(comment);
        }
        throw new EntityNotFoundException("Post not found");

    }
    public List<Comment> getCommentsByPostId(Long postId){
        return commentRepository.findByPostId(postId);
    }
}

package com.project.assignment.controller;
import com.project.assignment.entity.Comment;
import com.project.assignment.repository.CommentRepository;
import com.project.assignment.repository.PostRepository;
import com.project.assignment.service.ViralityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ViralityService viralityService;

    @PostMapping
    public ResponseEntity<?> addComment(@PathVariable Long postId, @RequestBody Comment comment){
        return postRepository.findById(postId).map(post -> {
            if(comment.getDepthLevel()>20){
                return ResponseEntity.status(400).body("Max depth of 20 reached");
            }
            if(comment.getBotAuthor() !=null){

                boolean allowed = viralityService.isBotAllowedToComment(
                        postId,
                        comment.getBotAuthor().getId(),
                        post.getUserAuthor().getId()
                );
                if(!allowed){
                    return ResponseEntity.status(429).body("Too Many Requests: BOT limit reached or cooldown active.");
                }

                viralityService.updateScore(postId, "BOT");
            }else {
                viralityService.updateScore(postId, "COMMENT");
            }
            comment.setPost(post);
            return ResponseEntity.ok(commentRepository.save(comment));
        }).orElse(ResponseEntity.notFound().build());
    }
}

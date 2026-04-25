package com.project.assignment.service;

import com.project.assignment.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ViralityService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private NotificationService notificationService;

    public void updateScore(Long postId, String type){
        System.out.println("Updating score for post: " + postId);
        String key="post:" +postId+":virality_score";
        int points = switch(type){
            case "BOT" -> 1;
            case "LIKE" -> 20;
            case "COMMENT" -> 50;
            default -> 0;
        };
        Long newScore= redisTemplate.opsForValue().increment(key, points);

        if(newScore !=null && newScore>=100){
            postRepository.findById(postId).ifPresent(post -> {
                notificationService.sendViralNotifaction(
                        post.getUserAuthor().getId(),
                        postId,
                        newScore
                );
            });
        }
    }

    public boolean isBotAllowedToComment(Long postId, Long botId, Long humanId){
        String botCountKey="post:"+postId+":bot_count";
        String cooldownKey="cooldown:bot:"+botId+":human:"+humanId;

        Boolean canProceed = redisTemplate.opsForValue().setIfAbsent(cooldownKey,"active", Duration.ofMinutes(10));

        if (Boolean.FALSE.equals(canProceed)){
            return false;
        }
        Long currentBotReplies = redisTemplate.opsForValue().increment(botCountKey);

        if (currentBotReplies != null && currentBotReplies>100){
            return false;
        }
        return true;
    }
}

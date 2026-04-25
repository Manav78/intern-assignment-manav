package com.project.assignment.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Async
    public void sendViralNotifaction(Long userId, Long postId, Long score){
        try{
            Thread.sleep(2000);
            System.out.println(">>> [NOTIFICATION] User "+userId+": Your post "+postId+" is going viral! CURRENT SCORE: "+score);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}

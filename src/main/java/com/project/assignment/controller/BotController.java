package com.project.assignment.controller;

import com.project.assignment.entity.Bot;
import com.project.assignment.repository.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bots")
public class BotController {

    @Autowired
    private BotRepository botRepository;

    @PostMapping
    public Bot createBot(@RequestBody Bot bot){
        return botRepository.save(bot);
    }
}

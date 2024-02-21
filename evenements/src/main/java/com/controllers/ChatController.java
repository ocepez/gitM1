package com.controllers;


import com.dtos.ChatDto;
import com.dtos.DogDto;
import com.services.impl.ChatServiceImpl;
import com.services.impl.DogServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ChatServiceImpl chatService;

    public ChatController(ChatServiceImpl dogService) {
        this.chatService = dogService;
    }

    /**
     * <p>Get all dogs in the system</p>
     * @return List<DogDto>
     */
    @GetMapping
    public List<ChatDto> getChats() {
        return chatService.getAllChats();
    }

    /**
     * Method to get the dog based on the ID
     */
    @GetMapping("/{id}")
    public ChatDto getDog(@PathVariable Long id){
        return chatService.getChatById(id);
    }

    /**
     * Create a new Dog in the system
     */
    @PostMapping
    public ChatDto saveChat(final @RequestBody ChatDto dogDto){
        return chatService.saveChat(dogDto);
    }

    /**
     * Delete a dog by it's id
     */
    @DeleteMapping("/{id}")
    public Boolean deleteChat(@PathVariable Long id){
        return chatService.deleteChat(id);
    }

}

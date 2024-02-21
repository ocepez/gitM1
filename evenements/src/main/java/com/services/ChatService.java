package com.services;

import com.dtos.ChatDto;
import com.dtos.DogDto;

import java.util.List;

public interface ChatService {


    ChatDto saveChat(ChatDto dogDto);

    ChatDto getChatById(Long chatId);
    /**
     * Delete a dog by it's id
     */
    boolean deleteChat(Long chatId);

    /**
     * Get all the dogs
     */
    List<ChatDto> getAllChats();

}

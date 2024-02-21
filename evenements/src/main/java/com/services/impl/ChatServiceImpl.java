package com.services.impl;

import com.dtos.ChatDto;
import com.dtos.DogDto;
import com.entities.Chat;
import com.entities.Dog;
import com.repositories.ChatRepository;
import com.services.ChatService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service("chatService")
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository){
        this.chatRepository=chatRepository;
    }

    private Chat chatDtoToEntity(ChatDto chatDto){
        Chat chat= new Chat();
        chat.setId(chatDto.getId());
        chat.setName(chatDto.getName());
        chat.setRace(chatDto.getRace());
        chat.setSexe(chatDto.getSexe());

        return chat;
    }
    /**
     * Map dog entity to dog dto
     */
    private Dog chatDtoToEntity(DogDto dogDto){
        Dog dog = new Dog();
        dog.setName(dogDto.getName());
        dog.setId(dogDto.getId());
        dog.setRace(dogDto.getRace());
        return dog;
    }
    @Override
    public ChatDto saveChat(ChatDto chatDto) {

        Chat chat = chatDtoToEntity(chatDto);
        chat = chatRepository.save(chat);
        return null;
    }

    @Override
    public ChatDto getChatById(Long chatId) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Dog not found"));
        return chatDtoToEntity(chat);
    }
    @Override
    public boolean deleteChat(Long chatId) {
        return false;
    }

    @Override
    public List<ChatDto> getAllChats() {
        List<ChatDto> dogDtos = new ArrayList<>();
        List<Chat> chats = chatRepository.findAll();
        chats.forEach(chat -> {
            dogDtos.add(chatDtoToEntity(chat));
        });
        return dogDtos;
    }
}

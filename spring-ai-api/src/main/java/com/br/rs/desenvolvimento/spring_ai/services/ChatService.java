package com.br.rs.desenvolvimento.spring_ai.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ChatService {

  private final ChatClient chatClient;



  public ChatService(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory,
      RetrievalAugmentationAdvisor ragAdvisor,
      @Value("classpath:/prompts/prompt.st") Resource systemPrompt) {
    this.chatClient =
        chatClientBuilder.defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory), ragAdvisor)
            .defaultSystem(systemPrompt).build();
  }

  public String prompt(String chatId, String message) {
    ChatService.log.info("Usuário: {}", message);

    String response = this.chatClient.prompt().user(message)
        .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
            .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 50))
        .call().content();

    ChatService.log.info("Resposta do Chatbot: {}", response);

    return response;
  }

}

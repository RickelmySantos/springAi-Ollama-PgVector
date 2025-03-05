package com.br.rs.desenvolvimento.spring_ai.services;

import com.br.rs.desenvolvimento.spring_ai.core.modelo.PromptBase;
import com.br.rs.desenvolvimento.spring_ai.core.servicos.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ChatService extends BaseService<PromptBase> {

  // private final ChatClient chatClient;

  public ChatService(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory,
      @Value("classpath:/prompts/prompt.st") Resource systemPrompt, VectorStore vectorStore,
      RetrievalAugmentationAdvisor ragAdvisor) {
    super(chatClientBuilder, chatMemory, systemPrompt, vectorStore, ragAdvisor);
  }

  // public String prompt(String chatId, String message) {
  // ChatService.log.info("Usuário: {}", message);
  // String response = this.chatClient.prompt().user(message)
  // .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
  // .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 50))
  // .call().content();
  // ChatService.log.info("Resposta do Chatbot: {}", response);

  // return response;
  // }

  // public Flux<String> reactivePrompt(String chatId, String message) {

  // return Mono.fromCallable(() -> this.chatClient.prompt().user(message)
  // .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
  // .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 50))
  // .call().content())
  // .doOnNext(response -> ChatService.log.info("Resposta do Chatbot: {}", response)).flux();

  // }
}

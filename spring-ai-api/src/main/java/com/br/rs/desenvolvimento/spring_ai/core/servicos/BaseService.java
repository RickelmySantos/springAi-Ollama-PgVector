package com.br.rs.desenvolvimento.spring_ai.core.servicos;

import com.br.rs.desenvolvimento.spring_ai.core.modelo.PromptBase;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public abstract class BaseService<E extends PromptBase> implements BaseChatService<E> {

  protected final ChatClient chatClient;
  protected final VectorStore vectorStore;
  protected final RetrievalAugmentationAdvisor ragAdvisor;

  public BaseService(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory,
      Resource systemPrompt, VectorStore vectorStore, RetrievalAugmentationAdvisor ragAdvisor) {
    this.chatClient = chatClientBuilder.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
        .defaultSystem(systemPrompt).build();
    this.vectorStore = vectorStore;
    this.ragAdvisor = ragAdvisor;
  }

  @Override
  public String prompt(String chatId, String message) {
    BaseService.log.info("Usuário: {}", message);

    List<Document> documents = this.vectorStore.similaritySearch(message);
    if (documents.isEmpty()) {
      BaseService.log.warn("Nenhum documento relevante encontrado para a consulta: {}", message);
    } else {
      documents.forEach(
          doc -> BaseService.log.info("📄 Documento encontrado: {}", doc.getFormattedContent()));
    }

    String response = this.chatClient.prompt().user(message)
        .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
            .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 50)
            .advisors(this.ragAdvisor))
        .call().content();
    BaseService.log.info("Resposta do Chatbot: {}", response);
    return response;
  }

  @Override
  public Flux<String> reactivePrompt(String chatId, String message) {
    return Mono.fromCallable(() -> this.chatClient.prompt().user(message)
        .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
            .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 50)
            .advisors(this.ragAdvisor))
        .call().content())
        .doOnNext(response -> BaseService.log.info("Resposta do Chatbot: {}", response)).flux();
  }
}

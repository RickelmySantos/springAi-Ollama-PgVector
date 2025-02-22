package com.br.rs.desenvolvimento.spring_ai.controladores;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

  private final ChatClient chatClient;
  private final RetrievalAugmentationAdvisor retrievalAugmentationAdvisor;

  public ChatController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
    this.chatClient = chatClientBuilder.clone().build();
    this.retrievalAugmentationAdvisor = RetrievalAugmentationAdvisor.builder()
        .documentRetriever(VectorStoreDocumentRetriever.builder().vectorStore(vectorStore).build())
        .build();
  }

  @PostMapping
  public String prompt(@RequestBody String question) {

    return this.chatClient.prompt().advisors(this.retrievalAugmentationAdvisor).user(question)
        .call().content();
  }
}

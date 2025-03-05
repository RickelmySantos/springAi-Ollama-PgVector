package com.br.rs.desenvolvimento.spring_ai.configuracao;

import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemoryBeanConfig {

  @Bean
  public ChatMemory chatMemory() {
    return new InMemoryChatMemory();
  }

  @Bean
  RetrievalAugmentationAdvisor ragAdvisor(VectorStore vectorStore) {
    return RetrievalAugmentationAdvisor.builder().documentRetriever(VectorStoreDocumentRetriever
        .builder().similarityThreshold(0.50).vectorStore(vectorStore).build()).build();
  }

}

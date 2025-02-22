package com.br.rs.desenvolvimento.spring_ai.services;

import jakarta.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class EmbbeddingsParse {
  private static final Logger logger = LoggerFactory.getLogger(EmbbeddingsParse.class);

  private final VectorStore vectorStore;

  @Value("classpath:documents/biblioteca.md")
  Resource mdFile;

  public EmbbeddingsParse(VectorStore vectorStore) {
    this.vectorStore = vectorStore;
  }

  @PostConstruct
  void run() {
    List<Document> documents = new ArrayList<>();

    EmbbeddingsParse.logger.info("Loading .md files as Documents");

    var reader = new TextReader(this.mdFile);
    reader.getCustomMetadata().put("titulo", "Biblioteca");
    reader.setCharset(Charset.defaultCharset());
    documents.addAll(reader.get());

    EmbbeddingsParse.logger.info("Creating and storing Embeddings from Documents");

    this.vectorStore.add(new TokenTextSplitter().split(documents));
  }

}

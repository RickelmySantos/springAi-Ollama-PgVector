package com.br.rs.desenvolvimento.spring_ai.repositorios;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit {

  private final VectorStore vectorStore;

  private final Resource resource;

  public DataBaseInit(VectorStore vectorStore,
      @Value("classpath:documents/biblioteca.md") Resource resource) {
    this.vectorStore = vectorStore;
    this.resource = resource;
  }


  @PostConstruct
  void init() {
    List<Document> documents = this.loadMarkdown();
    var textSplit = new TokenTextSplitter();
    var transformerDocuments = textSplit.apply(documents);
    this.vectorStore.add(transformerDocuments);

  }

  List<Document> loadMarkdown() {

    MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
        .withHorizontalRuleCreateDocument(true).withIncludeCodeBlock(false)
        .withIncludeBlockquote(false).withAdditionalMetadata("filename", "biblioteca.md").build();

    MarkdownDocumentReader reader = new MarkdownDocumentReader(this.resource, config);
    return reader.get();
  }
}


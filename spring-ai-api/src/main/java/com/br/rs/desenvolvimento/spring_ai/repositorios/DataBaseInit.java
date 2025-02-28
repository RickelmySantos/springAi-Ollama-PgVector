package com.br.rs.desenvolvimento.spring_ai.repositorios;

import jakarta.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit {

  private final VectorStore vectorStore;

  @Value("classpath:documents/biblioteca.md")
  Resource mdFile;

  public DataBaseInit(VectorStore vectorStore) {
    this.vectorStore = vectorStore;
  }

  @PostConstruct
  void init() {
    List<Document> documents = new ArrayList<>();

    var textReader = new TextReader(this.mdFile);
    textReader.getCustomMetadata().put("filename", "mdFile");
    textReader.setCharset(Charset.defaultCharset());
    documents.addAll(textReader.get());

    var textSplit = new TokenTextSplitter();
    var transformerDocuments = textSplit.apply(documents);

    this.vectorStore.add(transformerDocuments);
  }

}


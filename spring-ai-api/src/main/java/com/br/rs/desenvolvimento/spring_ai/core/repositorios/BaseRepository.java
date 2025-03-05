package com.br.rs.desenvolvimento.spring_ai.core.repositorios;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;

@Slf4j
public abstract class BaseRepository<E extends Document> implements BaseChatRepository<E> {

  protected final VectorStore vectorStore;

  protected abstract List<E> loadDocuments();

  public BaseRepository(VectorStore vectorStore) {
    this.vectorStore = vectorStore;
  }

  @PostConstruct
  public void initDatabase() {
    List<E> documents = this.loadDocuments();
    var textSplit = new TokenTextSplitter();
    var transformerDocuments = textSplit.apply((List<Document>) documents);

    boolean exists =
        transformerDocuments.stream().map(Document::getFormattedContent).anyMatch(content -> {
          List<Document> results = this.vectorStore.similaritySearch(content);
          return results != null && !results.isEmpty();
        });

    if (!exists) {
      this.vectorStore.add(transformerDocuments);
      BaseRepository.log.info("{} documentos foram inseridos no banco.",
          transformerDocuments.size());
    } else {
      BaseRepository.log.info("Os documentos já existem no banco. Nenhuma inserção necessária.");
    }
  }


  @Override
  public void saveDocuments(List<E> documents) {
    if (documents.isEmpty()) {
      BaseRepository.log.info("Nenhum documento para inserir.");
      return;
    }

    boolean exists = documents.stream().map(Document::getFormattedContent).anyMatch(content -> {
      List<Document> results = this.vectorStore.similaritySearch(content);
      return results != null && !results.isEmpty();
    });

    if (!exists) {
      this.vectorStore.add((List<Document>) (List<?>) documents);
      BaseRepository.log.info("{} documentos foram inseridos no banco.", documents.size());
    } else {
      BaseRepository.log.info("Os documentos já existem no banco. Nenhuma inserção necessária.");
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<E> searchBySimilarity(String query) {
    return (List<E>) this.vectorStore.similaritySearch(query);
  }

  @Override
  public boolean exists(E document) {
    List<Document> results = this.vectorStore.similaritySearch(document.getFormattedContent());
    return results != null && !results.isEmpty();
  }
}

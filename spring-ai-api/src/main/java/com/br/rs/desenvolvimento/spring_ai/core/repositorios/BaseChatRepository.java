package com.br.rs.desenvolvimento.spring_ai.core.repositorios;

import java.util.List;

public interface BaseChatRepository<E> {

  void saveDocuments(List<E> documents);

  List<E> searchBySimilarity(String query);

  boolean exists(E document);

}

package com.br.rs.desenvolvimento.spring_ai.core.servicos;

import com.br.rs.desenvolvimento.spring_ai.core.modelo.PromptBase;
import reactor.core.publisher.Flux;

public interface BaseChatService<E extends PromptBase> {

  String prompt(String chatId, String message);

  Flux<String> reactivePrompt(String chatId, String message);
}

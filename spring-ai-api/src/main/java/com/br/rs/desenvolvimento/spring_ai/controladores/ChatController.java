package com.br.rs.desenvolvimento.spring_ai.controladores;

import com.br.rs.desenvolvimento.spring_ai.model.PromptRequest;
import com.br.rs.desenvolvimento.spring_ai.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

  private final ChatService chatService;


  @PostMapping("/{conversationId}")
  public String prompt(@PathVariable String conversationId, @RequestBody PromptRequest prompt) {
    return this.chatService.prompt(conversationId, prompt.getName());
  }

  @PostMapping("/reactive/{conversationId}")
  public Flux<String> reactivePrompt(@PathVariable String conversationId,
      @RequestBody PromptRequest prompt) {
    return this.chatService.reactivePrompt(conversationId, prompt.getName());
  }
}

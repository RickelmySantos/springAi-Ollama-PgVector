package com.br.rs.desenvolvimento.spring_ai.controladores;

import com.br.rs.desenvolvimento.spring_ai.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

  private final ChatService chatService;


  @PostMapping("/{conversationId}")
  public String prompt(@PathVariable String conversationId, @RequestBody String prompt) {
    return this.chatService.prompt(conversationId, prompt);
  }

}

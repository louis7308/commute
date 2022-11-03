package com.example.commute.domain.email.presentation;

import com.example.commute.domain.email.presentation.dto.request.EmailSentDto;
import com.example.commute.domain.email.service.MailCheckerService;
import com.example.commute.domain.email.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
@RequestMapping("email")
public class EmailController {
    private final MailSenderService mailSenderService;
    private final MailCheckerService mailCheckerService;
    @PostMapping
    public ResponseEntity<Void> authEmail(@RequestBody @Valid EmailSentDto emailSentDto) {
        mailSenderService.execute(emailSentDto);
        return ResponseEntity.ok().build();
    }
}
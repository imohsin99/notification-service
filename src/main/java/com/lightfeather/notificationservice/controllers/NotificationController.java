package com.lightfeather.notificationservice.controllers;

import com.lightfeather.notificationservice.dto.NotificationDTO;
import com.lightfeather.notificationservice.dto.SupervisorDTO;
import com.lightfeather.notificationservice.services.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/supervisors")
    public ResponseEntity<List<SupervisorDTO>> getAllSupervisors(){
        return ResponseEntity.ok(notificationService.getAllSupervisors());
    }

    @PostMapping("/submit")
    public ResponseEntity<NotificationDTO> submit(@RequestBody @Valid NotificationDTO notificationDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.createNotification(notificationDTO));
    }
}

package com.lightfeather.notificationservice.services;

import com.lightfeather.notificationservice.dto.NotificationDTO;
import com.lightfeather.notificationservice.dto.SupervisorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class NotificationService {

    private final WebClient webClient;

    public NotificationService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder
                .baseUrl("https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers")
                .build();
    }

    public List<SupervisorDTO> getAllSupervisors(){
        Flux<SupervisorDTO> supervisorDTOFlux = webClient.get().retrieve().bodyToFlux(SupervisorDTO.class);

        supervisorDTOFlux = supervisorDTOFlux
                .filter(supervisorDTO -> supervisorDTO.getJurisdiction().matches("^\\D+$"));

        Comparator<SupervisorDTO> comparator = Comparator
                .comparing(SupervisorDTO::getJurisdiction)
                .thenComparing(SupervisorDTO::getLastName)
                .thenComparing(SupervisorDTO::getLastName);

        return supervisorDTOFlux.sort(comparator).buffer().blockFirst();
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO){
        log.info("New notification created: firstName={}, lastName={}, email={}, phoneNumber={}, supervisor={}",
                notificationDTO.getFirstName(), notificationDTO.getLastName(),
                notificationDTO.getEmail(), maskPhoneNumber(notificationDTO.getPhoneNumber()),
                notificationDTO.getSupervisorDTO().getJurisdiction() + " - " +
                        notificationDTO.getSupervisorDTO().getLastName() + ", " + notificationDTO.getSupervisorDTO().getFirstName());
        return notificationDTO;
    }

    private String maskPhoneNumber(String phoneNumber){
        if (phoneNumber == null){
            return  null;
        }
        char[] chars = new char[phoneNumber.length()];
        Arrays.fill(chars, 0, phoneNumber.length() - 4, 'x');
        System.arraycopy(phoneNumber.toCharArray(), phoneNumber.length() -4, chars, phoneNumber.length() - 4, 4 );
        return new String(chars);
    }

}

package com.lightfeather.notificationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationDTO {

    @NotEmpty(message = "First name must be provided!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must only contain letters, no numbers.")
    private String firstName;

    @NotEmpty(message = "Last name must be provided!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must only contain letters, no numbers.")
    private String lastName;

    @Email(message = "Invalid email!")
    private String email;

    @Pattern(regexp = "^((1\\s?)?(\\d{3}|\\(\\d{3}\\))[\\s\\-]?\\d{3}[\\s\\-]?\\d{4})*$", message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message = "Supervisor must be given!")
    private SupervisorDTO supervisorDTO;
}

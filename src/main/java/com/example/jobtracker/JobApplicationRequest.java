package com.example.jobtracker;
import jakarta.validation.constraints.NotBlank;

public record JobApplicationRequest(
        @NotBlank String company,
        @NotBlank String role,
        @NotBlank String status) {
}

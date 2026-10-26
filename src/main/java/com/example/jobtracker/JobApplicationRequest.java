package com.example.jobtracker;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record JobApplicationRequest(
        @NotBlank String company,
        @NotBlank String role,
        @NotBlank @Pattern(regexp = "APPLIED|OFFER|INTERVIEW|REJECTED", message = "must be APPLIED, INTERVIEW, OFFER or REJECTED") String status) {
}

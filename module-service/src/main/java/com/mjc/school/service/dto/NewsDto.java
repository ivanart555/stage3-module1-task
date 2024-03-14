package com.mjc.school.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsDto {
    private Long id;
    @Pattern(regexp = "^.{5,30}$", message = "Title length must be from 5 to 30 characters")
    private String title;
    @Pattern(regexp = "^.{5,255}$", message = "Content length must be from 5 to 255 characters")
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateTime;
    @NotNull
    private Long authorId;
}

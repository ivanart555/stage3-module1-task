package com.mjc.school.repository.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class News {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateTime;
    private Long authorId;
}

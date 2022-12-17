package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.enums.TypeContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContentResponseDto {
    private Long id;
    private String contentUrl;
    private TypeContent typeContent;
}

package com.example.socialMediaApi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class PostDto {
    private Long id;
    private String header;
    private String text;
    private byte[] image;
    private String imageContentType;
}

package com.example.socialMediaApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "header")
    private String header;

    @Column(name = "text")
    private String text;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    public Post(String header, String text, byte[] image, String imageContentType, User user) {
        this.header = header;
        this.text = text;
        this.image = image;
        this.imageContentType = imageContentType;
        this.author = user;
    }
}

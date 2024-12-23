package org.example.mediaserver.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "video")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String url;
    private Integer durationSeconds;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

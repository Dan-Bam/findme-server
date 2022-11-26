package com.project.findme.domain.image.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.findme.domain.lost.domain.Lost;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_image_id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "lostId", nullable = false)
    private Lost lost;

    private String imageUrl;

}

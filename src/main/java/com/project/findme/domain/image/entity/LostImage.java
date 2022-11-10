package com.project.findme.domain.image.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.findme.domain.lost.entity.Lost;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "lostId", nullable = false)
    private Lost lost;

    private String imageUrl;

}

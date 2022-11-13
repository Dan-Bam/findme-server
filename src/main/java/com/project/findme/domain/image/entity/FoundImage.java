package com.project.findme.domain.image.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.findme.domain.found.entity.Found;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoundImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "found_image_id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "foundId", nullable = false)
    private Found found;

    private String imageUrl;

}

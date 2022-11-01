package com.project.findme.domain.image.entity;

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
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "lostId")
    private Lost lost;

    private String imageUrl;

}

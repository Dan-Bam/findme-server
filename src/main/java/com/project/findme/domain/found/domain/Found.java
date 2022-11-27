package com.project.findme.domain.found.domain;

import com.project.findme.domain.lost.type.Category;
import com.project.findme.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Found {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "found_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "found_tags", joinColumns = @JoinColumn(name = "userId"))
    private List<String> tags = new ArrayList<>();

    private boolean isSafe;

    private String place;

    private String latitude;

    private String longitude;

    public void updateFound(String title, String description, List<String> tags, Boolean isSafe, String place, String latitude, String longitude) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.isSafe = isSafe;
        this.place = place;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}

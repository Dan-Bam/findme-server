package com.project.findme.domain.found.entity;

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

    @ElementCollection
    @CollectionTable(name = "found_tags", joinColumns = @JoinColumn(name = "userId"))
    private List<String> tags = new ArrayList<>();

    private boolean safeTransaction;

    private String place;

    private String latitude;

    private String longitude;

    public void updateFound(String title, String description, Category category, List<String> tags, String place, String latitude, String longitude) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tags = tags;
        this.place = place;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}

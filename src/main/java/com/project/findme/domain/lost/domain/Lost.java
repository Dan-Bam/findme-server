package com.project.findme.domain.lost.domain;

import com.project.findme.domain.lost.type.Category;
import com.project.findme.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String description;

    private Category category;

    @ElementCollection
    @CollectionTable(name = "lost_tags", joinColumns = @JoinColumn(name = "userId"))
    private List<String> tags = new ArrayList<>();

    @Column(name = "is_safe")
    private boolean isSafe;

    private String place;

    private String latitude;

    private String longitude;

    public void updateLost(String title, String description, List<String> tags, boolean isSafe, String place, String latitude, String longitude) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.isSafe = isSafe;
        this.place = place;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}

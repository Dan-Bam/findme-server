package com.project.findme.domain.lost.entity;

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

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    @CollectionTable(name = "lost_tags", joinColumns = @JoinColumn(name = "userId"))
    private List<String> tags = new ArrayList<>();

    private boolean safeTransaction;

    private String latitude;

    private String longitude;

    public void updateLost(String title, String description, String latitude, String longitude, Category category, List<String> tags) {
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.tags = tags;
    }

}

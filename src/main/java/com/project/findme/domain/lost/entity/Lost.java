package com.project.findme.domain.lost.entity;

import com.project.findme.domain.image.entity.LostImage;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String title;

    private String description;

    private String place;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    @CollectionTable(name = "lost_tags", joinColumns = @JoinColumn(name = "userId"))
    private List<String> tags = new ArrayList<>();

    private boolean safeTransaction;

    public void updateLost(String title, String description, String place, Category category, List<String> tags) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.category = category;
        this.tags = tags;
    }

}

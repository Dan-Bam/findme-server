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
    private Long foundId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String title;

    private String description;

    private String place;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    @CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "userId"))
    private List<String> tags = new ArrayList<>();

    private boolean safeTransaction;
}

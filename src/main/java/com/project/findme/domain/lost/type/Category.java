package com.project.findme.domain.lost.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Category {

    ELECTRONIC("전자기기"),
    PRECIOUS_METAL("귀금속"),
    CLOTHING("의류"),
    HOUSEHOLD_GOODS("생활용품"),
    OTHER("기타")
    ;

    private final String name;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Category findByName(String name) {
        return Stream.of(Category.values())
                .filter(it -> it.name.equals(name))
                .findFirst()
                .orElse(null);
    }
}

package com.project.findme.domain.lost.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Category {

    RING("반지"),
    BRACE("팔"),
    NECKLACE("목걸이"),
    WATCH("시계"),
    PHONE("핸드폰"),
    LAPTOP("노트북"),
    BAG("가방"),
    WEARABLE("웨어러블"),
    EARPHONE("이어폰"),
    GLASSES("안경"),
    WALLET("지갑"),
    USB("usb"),
    JACKET("겉옷"),
    BOOK("책");

    private final String name;

    public static Category findName(String name) {
        return Arrays.stream(Category.values())
                .filter(category -> category.name.equals(name))
                .findFirst()
                .orElseThrow();
    }

}

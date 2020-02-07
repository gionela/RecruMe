package com.gr.RecruMe.models;

import java.util.HashMap;
import java.util.Map;

public enum SkillLevel {
    JUNIOR(1), 
    MID(2), 
    SENIOR(3);
    private int value;

    private static Map map = new HashMap<>();

    private SkillLevel(int value) {
        this.value = value;
    }

    static {
        for (SkillLevel skillLevel : SkillLevel.values()) {
            map.put(skillLevel.value, skillLevel);
        }
    }

    public static SkillLevel valueOf(int skillLevel) {
        return (SkillLevel) map.get(skillLevel);
    }

    public int getValue() {
        return value;
    }
}

package com.gr.RecruMe.models;

import java.util.HashMap;
import java.util.Map;

public enum EducationLevel {
    HIGHSCHOOL(1), 
    BACHELOR_DEGREE(2), 
    MASTER_DEGREE(3), 
    PHD(4);
    private int value;
    
    private static Map map = new HashMap<>();

    private EducationLevel(int value) {
        this.value = value;
    }

    static {
        for (EducationLevel educationLevel : EducationLevel.values()) {
            map.put(educationLevel.value, educationLevel);
        }
    }

    public static EducationLevel valueOf(int educationLevel) {
        return (EducationLevel) map.get(educationLevel);
    }

    public int getValue() {
        return value;
    }
}

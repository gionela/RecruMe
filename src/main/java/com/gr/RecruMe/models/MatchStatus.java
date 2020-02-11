package com.gr.RecruMe.models;

import java.util.HashMap;
import java.util.Map;

/**
 *EDW KAI PERA PREPEI NA TOU VALEIS DIKES SOU TIMES POY APOTHIKEYEI STIN VASI STIN PERIPTWSI POU THELW NA PROSTHESW METEPEITA
 * ALLO ENUMERATION.
 * EN PROKEIMENW DOULEYEIS ME TIS VASIS DEFAULT TIMI(AUTO -> DB.VALUE = 0)
 */
public enum MatchStatus {
    AUTO(1),
    MANUAL(2), 
    FINAL(3);
    private int value;

    private static Map map = new HashMap<>();

    private MatchStatus(int value) {
        this.value = value;
    }

    static {
        for (MatchStatus matchStatus : MatchStatus.values()) {
            map.put(matchStatus.value, matchStatus);
        }
    }

    public static MatchStatus valueOf(int matchStatus) {
        return (MatchStatus) map.get(matchStatus);
    }

    public int getValue() {
        return value;
    }
}

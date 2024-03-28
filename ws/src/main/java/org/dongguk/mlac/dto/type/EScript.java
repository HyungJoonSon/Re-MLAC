package org.dongguk.mlac.dto.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EScript {
    WEB_ATTACK_XSS("WEB_ATTACK_XSS"),
    WEB_ATTACK_SQL_INJECTION("WEB_ATTACK_SQL_INJECTION"),
    EXPLOITS("EXPLOITS"),
    BACKDOOR("BACKDOOR"),
    SHELLCODE("SHELLCODE"),
    INFILTRATION("INFILTRATION"),
    GENERIC("GENERIC"),

    ;

    private final String value;

    public static Boolean exists(String value) {
        return Arrays.stream(EScript.values())
                .anyMatch(eScript -> eScript.getValue().equals(value));
    }

    public static EScript find(String value) {
        return Arrays.stream(EScript.values())
                .filter(eScript -> eScript.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
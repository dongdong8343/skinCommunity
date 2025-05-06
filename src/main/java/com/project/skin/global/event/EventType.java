package com.project.skin.global.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventType {
    SIGN_UP("회원가입");

    private final String description;
}

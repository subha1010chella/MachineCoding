package com.MachineCoding.RateLimiter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckResponseDto {
    public boolean allowed;
    public int remaining;

    public CheckResponseDto(boolean _allowed, int _remaining) {
        allowed = _allowed;
        remaining = _remaining;
    }
}

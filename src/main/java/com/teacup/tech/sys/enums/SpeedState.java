package com.teacup.tech.sys.enums;

public enum SpeedState {

    OFF,  	// 0
    LOW, 	// 1
    MEDIUM, // 2
    HIGH; 	// 3

    public SpeedState getNextMode() {
        return values()[(ordinal()+1) % values().length];
    }
    public SpeedState getPreviousMode() {
        int totalModes = values().length ;
        return values()[(((ordinal()-1) % totalModes + values().length ) % values().length) ];
    }
}

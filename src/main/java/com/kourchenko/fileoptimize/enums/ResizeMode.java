package com.kourchenko.fileoptimize.enums;

public enum ResizeMode {
    AUTOMATIC, FIXED_WIDTH, FIXED_HEIGHT, FIXED_DIMENSIONS, DONT_RESIZE;

    public String toString() {
        switch (this) {
            case AUTOMATIC:
                return "AUTOMATIC";
            case FIXED_WIDTH:
                return "FIXED_WIDTH";
            case FIXED_HEIGHT:
                return "FIXED_HEIGHT";
            case FIXED_DIMENSIONS:
                return "FIXED_DIMENSIONS";
            case DONT_RESIZE:
                return "DONT_RESIZE";
        }
        return null;
    }

    public static ResizeMode resizeMode(String value) {
        if (value.equalsIgnoreCase(AUTOMATIC.toString())) {
            return ResizeMode.AUTOMATIC;
        } else if (value.equalsIgnoreCase(FIXED_WIDTH.toString())) {
            return ResizeMode.FIXED_WIDTH;
        } else if (value.equalsIgnoreCase(FIXED_HEIGHT.toString())) {
            return ResizeMode.FIXED_HEIGHT;
        } else if (value.equalsIgnoreCase(FIXED_DIMENSIONS.toString())) {
            return ResizeMode.FIXED_DIMENSIONS;
        } else if (value.equalsIgnoreCase(DONT_RESIZE.toString())) {
            return ResizeMode.DONT_RESIZE;
        }
        return FIXED_WIDTH;
    }
}

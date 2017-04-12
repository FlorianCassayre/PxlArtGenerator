package me.cassayre.florian.pxlartgenerator;

import java.awt.*;

public enum PxlsColor
{
    WHITE(255, 255, 255),
    LIGHTGRAY(228, 228, 228),
    GRAY(136, 136, 136),
    BLACK(34, 34, 34),
    LIGHTPINK(255, 167, 209),
    RED(229, 0, 0),
    ORANGE(229, 149, 0),
    BROWN(160, 106, 66),
    YELLOW(229, 217, 0),
    LIGHTGREEN(148, 224, 68),
    GREEN(2, 190, 1),
    CYAN(0, 211, 221),
    LIGHTBLUE(0, 131, 199),
    BLUE(0, 0, 234),
    PINK(207, 110, 228),
    PURPLE(130, 0, 128);

    private final Color color;

    PxlsColor(int r, int g, int b)
    {
        this.color = new Color(r, g, b);
    }

    public Color color()
    {
        return color;
    }
}

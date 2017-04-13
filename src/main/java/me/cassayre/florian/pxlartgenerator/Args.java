package me.cassayre.florian.pxlartgenerator;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Args
{
    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = "--help", help = true, description = "Displays this menu")
    private boolean help;

    @Parameter(names = "-output-size", description = "The size of the pixels on the output image")
    private int outputSize = 1;

    @Parameter(names = {"-palette", "-p"}, description = "The palette to be used to draw the output image; it must be in hexadecimal")
    private String palette = "0123456789abcdef";

    @Parameter(names = {"-width", "-w"}, description = "The output image width")
    private Integer width = null;

    @Parameter(names = {"-height", "-h"}, description = "The output image height")
    private Integer height = null;

    public boolean help()
    {
        return help;
    }

    public List<String> parameters()
    {
        return parameters;
    }

    public int outputSize()
    {
        return outputSize;
    }

    public String palette()
    {
        return palette;
    }

    public Integer width()
    {
        return width;
    }

    public Integer height()
    {
        return height;
    }
}

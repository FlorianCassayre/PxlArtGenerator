package me.cassayre.florian.pxlartgenerator;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Args
{
    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = {"-source", "-src"}, description = "The source image location")
    private String sourceImage = null;

    @Parameter(names = {"-output", "-out"}, description = "The output image location")
    private String outputImage = null;

    @Parameter(names = "-output-size", description = "The size of the pixels on the output image")
    private int outputSize = 1;

    @Parameter(names = {"-color", "-c"}, description = "The colors to be used for the output image")
    private List<Integer> colors = new ArrayList<>();

    public List<String> parameters()
    {
        return parameters;
    }

    public String sourceImage()
    {
        return sourceImage;
    }

    public String outputImage()
    {
        return outputImage;
    }

    public int outputSize()
    {
        return outputSize;
    }

    public List<Integer> colors()
    {
        return colors;
    }
}

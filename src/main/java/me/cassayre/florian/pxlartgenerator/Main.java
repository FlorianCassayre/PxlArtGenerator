package me.cassayre.florian.pxlartgenerator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main
{
    public static void main(String[] args)
    {
        // args = "-src françois-hollande.png -out pxls.png -output-size 10 -c 0 -c 1 -c 2 -c 3".split(" ");

        final Args parameters = new Args();

        final JCommander commander = new JCommander();
        commander.addObject(parameters);
        commander.parse(args);

        try
        {
            checkArguments(parameters);
        }
        catch(ParameterException e)
        {
            System.out.println("Error: " + e.getMessage());
            return;
        }



        final BufferedImage source;
        try
        {
            source = ImageIO.read(new File(parameters.sourceImage()));
        }
        catch(IOException e)
        {
            System.out.println("Couldn't open source image. Does the file exist?");
            return;
        }

        final Set<PxlsColor> colors;

        if(parameters.colors().isEmpty())
        {
            colors = new HashSet<>(Arrays.asList(PxlsColor.values()));
        }
        else
        {
            colors = new HashSet<>();
            for(Integer integer : parameters.colors())
            {
                if(integer < 0 || integer >= PxlsColor.values().length)
                {
                    System.out.println("Illegal color index: " + integer);
                    return;
                }
                colors.add(PxlsColor.values()[integer]);
            }
        }

        final BufferedImage out = Utils.toPxlsImage(source, colors);

        final BufferedImage rendered = Utils.enlarge(out, parameters.outputSize());

        try
        {
            ImageIO.write(rendered, "png", new File(parameters.outputImage()));
            System.out.println("Created image '" + parameters.outputImage() + "'.");
        }
        catch(IOException e)
        {
            System.out.println("Couldn't write output image. Do you have the right to do so?");
            return;
        }
    }

    private static void checkArguments(Args args)
    {
        checkCondition(args.sourceImage() != null, "No source image was provided.");
        checkCondition(args.outputImage() != null, "No output path was provided.");
        checkCondition(args.outputSize() >= 1, "Output size must be strictly positive.");
    }

    private static void checkCondition(boolean condition, String message)
    {
        if(!condition)
            throw new ParameterException(message);
    }
}

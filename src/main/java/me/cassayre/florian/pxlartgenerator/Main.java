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
        // args = "-src françois-hollande.png -out pxls.png -p 0123 -w 50 -h 74".split(" ");

        final Args parameters = new Args();

        final JCommander commander = new JCommander();
        commander.addObject(parameters);

        try
        {
            commander.parse(args);
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

        final int width, height;

        if(parameters.width() == null && parameters.height() == null)
        {
            width = source.getWidth();
            height = source.getHeight();
        }
        else if(parameters.height() == null)
        {
            width = parameters.width();
            height = (int) Math.ceil((double) width * source.getHeight() / source.getWidth());
        }
        else if(parameters.width() == null)
        {
            height = parameters.height();
            width = (int) Math.ceil((double) height * source.getWidth() / source.getHeight());
        }
        else
        {
            width = parameters.width();
            height = parameters.height();
        }


        final BufferedImage scaled = Utils.resize(source, width, height);

        final Set<PxlsColor> colors;

        if(parameters.palette().isEmpty())
        {
            colors = new HashSet<>(Arrays.asList(PxlsColor.values()));
        }
        else
        {
            colors = new HashSet<>();
            for(char c : parameters.palette().toLowerCase().toCharArray())
            {
                final int value;
                if(c >= '0' && c <= '9')
                    value = c - '0';
                else
                    value = c - 'a' + 10;

                colors.add(PxlsColor.values()[value]);
            }
        }

        final BufferedImage out = Utils.toPxlsImage(scaled, colors);

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
        checkCondition(args.outputSize() > 0, "Output size must be strictly positive.");
        checkCondition(args.palette().matches("[0-9a-fA-F]*"), "The palette must be in hexademical.");
        checkCondition(args.width() == null || args.width() > 0, "The width must be strictly positive.");
        checkCondition(args.height() == null || args.height() > 0, "The height must be strictly positive.");
    }

    private static void checkCondition(boolean condition, String message)
    {
        if(!condition)
            throw new ParameterException(message);
    }
}

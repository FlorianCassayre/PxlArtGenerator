package me.cassayre.florian.pxlartgenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Set;

public final class Utils
{
    private Utils()
    {
    }

    public static BufferedImage enlarge(BufferedImage source, int pixelSize)
    {
        if(pixelSize < 1)
            throw new IllegalArgumentException("Pixel size must be strictly positive.");

        final BufferedImage out = new BufferedImage(source.getWidth() * pixelSize, source.getHeight() * pixelSize, BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < source.getWidth(); x++)
        {
            for(int y = 0; y < source.getHeight(); y++)
            {
                final int color = source.getRGB(x, y);

                for(int x1 = 0; x1 < pixelSize; x1++)
                {
                    for(int y1 = 0; y1 < pixelSize; y1++)
                    {
                        out.setRGB(x * pixelSize + x1, y * pixelSize + y1, color);
                    }
                }
            }
        }

        return out;
    }

    public static BufferedImage toPxlsImage(BufferedImage source, Set<PxlsColor> colors)
    {
        final BufferedImage out = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < out.getWidth(); x++)
        {
            for(int y = 0; y < out.getHeight(); y++)
            {
                final PxlsColor color = nearestColor(new Color(source.getRGB(x, y)), colors);

                out.setRGB(x, y, color.color().getRGB());
            }
        }

        return out;
    }

    public static PxlsColor nearestColor(Color color, Set<PxlsColor> colors)
    {
        if(colors.isEmpty())
            throw new IllegalArgumentException("Color set cannot be empty.");

        PxlsColor best = null;
        int distance = 0;

        for(PxlsColor pxlsColor : colors)
        {
            final int d = distance(color, pxlsColor.color());

            if(best == null || d < distance)
            {
                best = pxlsColor;
                distance = d;
            }
        }

        return best;
    }

    private static int distance(Color color1, Color color2)
    {
        return Math.abs(color1.getRed() - color2.getRed()) + Math.abs(color1.getGreen() - color2.getGreen()) + Math.abs(color1.getBlue() - color2.getBlue());
    }
}

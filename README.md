# PxlArtGenerator #

A light tool to convert images to pixel arts using the 16-colors palette available on [pxls.space](http://pxls.space).

![Mona Lisa](https://i.cassayre.me/20170413134346)

## Requirements ##

Java **1.7+** is required to run the program.

## Download ##

A compiled version can be downloaded in the [releases](#) section.

## Usage ##

    Usage: java -jar PxlArtGenerator.jar <source image> <output image> [options]
      Options:
        --help
          Displays this menu
        -height, -h
          The output image height
        -output-size
          The size of the pixels on the output image
          Default: 1
        -palette, -p
          The palette to be used to draw the output image; it must be in 
          hexadecimal 
          Default: 0123456789abcdef
        -width, -w
          The output image width

### Examples ###

Default options; the output size will be the same as the input, using all the available colors:

> `java -jar PxlArtGenerator.jar source.png output.png`


The output pixels will be 10 times larger (useful to avoid anti-aliasing when sharing the image):

> `java -jar PxlArtGenerator.jar source.png output.png -output-size 10`


The palette will only use white, light gray, gray and black:

> `java -jar PxlArtGenerator.jar source.png output.png -palette 0123`


The output image will be 20 pixels long (horizontally), and the height will be calculated according to the original aspect ratio:

> `java -jar PxlArtGenerator.jar source.png output.png -width 20`


The output image will be 50 x 50 pixels (warning, the aspect ratio can change!):

> `java -jar PxlArtGenerator.jar source.png output.png -width 50 -height 50`
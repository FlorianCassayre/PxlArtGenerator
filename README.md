# PxlArtGenerator #

A light tool to convert images to pixel arts using the 16-colors palette available on [pxls.space](http://pxls.space).

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
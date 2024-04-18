package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage image = new APImage("cyberpunk2077.jpg");
        image.draw();
        grayScale("cyberpunk2077.jpg");
        blackAndWhite("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg", 35);
        reflectImage("cyberpunk2077.jpg");
        rotateImage("cyberpunk2077.jpg");
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        int width = image.getWidth();
        int height = image.getHeight();
        for(int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                Pixel pixel = image.getPixel(x,y);
                int avg = getAverageColour(pixel);
                pixel.setRed(avg);
                pixel.setGreen(avg);
                pixel.setBlue(avg);
            }
        }
        image.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        int avg = (red+green+blue)/3;
        return avg;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        int width = image.getWidth();
        int height = image.getHeight();
        for(int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                Pixel pixel = image.getPixel(x,y);
                int avg = getAverageColour(pixel);
                if (avg<128){
                    pixel.setRed(0);
                    pixel.setGreen(0);
                    pixel.setBlue(0);
                }else{
                    pixel.setRed(255);
                    pixel.setGreen(255);
                    pixel.setBlue(255);
                }
            }
        }
        image.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage image = new APImage(pathToFile);
        int width = image.getWidth();
        int height = image.getHeight();
        for(int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                Pixel pixel = image.getPixel(x,y);
                Pixel left = pixel;
                Pixel below = pixel;
                if (x>0){
                    left = image.getPixel(x-1,y);
                }
                if (y<height-1){
                    below = image.getPixel(x,y+1);
                }
                int avg = getAverageColour(pixel);
                int avgLeft = getAverageColour(left);
                int avgBelow = getAverageColour(below);
                int dif1 = Math.abs(avg-avgLeft);
                int dif2 = Math.abs(avg-avgBelow);
                if (dif1>threshold||dif2>threshold){
                    pixel.setRed(0);
                    pixel.setGreen(0);
                    pixel.setBlue(0);
                }else{
                    pixel.setRed(255);
                    pixel.setGreen(255);
                    pixel.setBlue(255);
                }
            }
        }
        image.draw();

    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        int width = image.getWidth();
        int height = image.getHeight();
        APImage flipped = new APImage(width,height);
        for(int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                flipped.setPixel(x,y,(image.getPixel(width - x - 1, y)));
            }
        }
        flipped.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        int width = image.getWidth();
        int height = image.getHeight();
        APImage flipped = new APImage(height,width);
        int x = 0;
        int y = 0;
        for(int i=height-1;i>=0;i--){
            for (int j=width-1;j>=0;j--){
                Pixel pixel = image.getPixel(x,y);
                flipped.setPixel(i,j,pixel);
                x++;
            }
            y++;
            x=0;
        }
        flipped.draw();
    }

}

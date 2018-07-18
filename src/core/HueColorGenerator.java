package core;
import java.awt.Color;
/*
 * Class for generating the color for the Mandelbrot set.
 */
public class HueColorGenerator implements ColorGenerator {

    /*
     * @see ColorGenerator#getColorFor(int, int)
     */
    public Color getColorFor(int iterations, int maxIterations) {
        if (iterations == maxIterations) {
            return new Color(0x000000); // black
        } else {
            return new Color(Color.HSBtoRGB((float) Math.sqrt(iterations / (float) maxIterations), 1, 1));

        }
    }
}
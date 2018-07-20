package core;
import java.awt.Color;
/**
 * @author Johannes Zell
 * Class for generating the color for the Mandelbrot set.
 */
public class HueColorGenerator implements ColorGenerator {

    /**
     * @see ColorGenerator#getColorFor(int, int)
     * @return new Color
     */
    public Color getColorFor(int iterations, int maxIterations) {
        if (iterations == maxIterations) {
            return new Color(0x000000); // black
        } else {
            return new Color(Color.HSBtoRGB(((float) Math.sqrt(iterations / (float) maxIterations) * (float)2), 1, 1));
        }
    }
}
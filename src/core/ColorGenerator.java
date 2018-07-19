package core;
import java.awt.Color;

/**
 * @autor Johannes Zell
 * Color interface
 */
public interface ColorGenerator {

    /**
     * Generates a color depending on the given iterations
     */
    public Color getColorFor(int iterations, int maxIterations);

}

package core;
import java.awt.Color;

/*
 * Color interface
 */
public interface ColorGenerator {

    /*
     * Generates a color depending on the given iterations
     */
    public Color getColorFor(int iterations, int maxIterations);

}

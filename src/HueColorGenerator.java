import java.awt.Color;

public class HueColorGenerator implements ColorGenerator {


    public Color getColorFor(int iterations, int maxIterations) {
        if (iterations < 0) {
            throw new IllegalArgumentException("iterations may not be a negative value");
        }
        if (iterations > maxIterations) {
            throw new IllegalArgumentException("maxIterations may not be less than iterations");
        }

        if (iterations == maxIterations) {
            return new Color(0x000000); // black
        } else {
            return new Color(Color.HSBtoRGB((float) Math.sqrt(iterations / (float) maxIterations), 80, 1));
        }
    }

}
package gui;

/**
 * Class which converts the pictures coordinate system to the coordinate system of the complex number ones
 * @author Ronny Kohlhaus
 */
public class Viewport {

    private int sideLength;
    private double xMin, xMax, yMin, yMax, zoomFactor;

    /**
     * Constructor of the Viewport
     * @param sideLength Side length of the image
     */
    public Viewport( int sideLength, double xMin, double xMax, double yMin, double yMax, double zoomFactor ) {
        this.sideLength = sideLength;
        reset( xMin, xMax, yMin, yMax, zoomFactor );
    }

    /**
     * Resets the viewport variables, which is needed, if the mandelbrot is generated freshly
     * @param xMin Minimum of the imaginary axis
     * @param xMax Maximum of the imaginary axis
     * @param yMin Minimum of the real axis
     * @param yMax Maximum of the real axis
     * @param zoomFactor Factor which divides for zooming in
     */
    public void reset( double xMin, double xMax, double yMin, double yMax, double zoomFactor ) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.zoomFactor = zoomFactor;
    }

    /**
     * Zooms the image with a, from the start chosen, zoom factor into the desired direction
     * @param xCenter center-point of the x-axis to which the image gets zoomed
     * @param yCenter center-point of the y-axis to which the image gets zoomed
     */
    public void zoom( double xCenter, double yCenter ) {
        double[] centerCoords = this.getCoordinates( xCenter, yCenter );
        double newX, newY;
        
        newX = ( getXLength()/this.zoomFactor )/2;
        newY = ( getYLength()/this.zoomFactor )/2;
        
        this.xMin = centerCoords[0]-newX;
        this.yMin = centerCoords[1]-newY;
        this.xMax = centerCoords[0]+newX;
        this.yMax = centerCoords[1]+newY;
    }

    /**
     * Converts picture coordinates (pixels) into coordinates of the complex number system
     * @param x x-coordinate of the pixel
     * @param y y-coordinate of the pixel
     * @return returns the converted numbers which are stored in an array
     */
    public double[] getCoordinates( double x, double y ) {
        double viewPortX = this.xMin + ( ( x / (double) this.sideLength ) * getXLength());
        double viewPortY = this.yMin + ( ( y / (double) this.sideLength ) * getYLength());
        return new double[] { viewPortX, viewPortY };
    }

    double getxMin() {
        return xMin;
    }

    double getxMax() {
        return xMax;
    }

    double getyMin() {
        return yMin;
    }

    double getyMax() {
        return yMax;
    }

    private double getXLength() {
        return this.xMax-this.xMin;
    }

    private double getYLength() {
        return this.yMax-this.yMin;
    }
}

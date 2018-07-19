package gui;

/**
 * Class which converts the pictures coordinate system to the coordinate system of the complex number ones
 * @author Ronny Kohlhaus
 */

public class Viewport {

    private int sideLength;
    private double xMin, xMax, yMin, yMax, zoomFactor;
    private double total_x;
    private double total_y;
    private double viewPortX, viewPortY;

    /**
     * Constructor of the Viewport
     * @param sideLength Side length of the image
     */
    public Viewport( int sideLength, double xMin, double xMax, double yMin, double yMax, double zoomFactor ) {
        this.sideLength = sideLength;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.total_x = xMax - xMin;
        this.total_y = yMin - yMax;
        this.zoomFactor = zoomFactor;
    }

    public void zoom() {
        xMin += total_x / 30;
        yMin += total_y / 30;
        xMax -= total_x / 30;
        yMax -= total_y / 30;
    }

    public double[] getCoordinates(double x, double y) {
        this.viewPortX = this.xMin + ((x/(double) this.sideLength)*getXLength());
        this.viewPortY = this.yMin + ((x/(double) this.sideLength)*getXLength());
        return new double[] {viewPortX, viewPortY};
    }

    /**
     *
     * @return
     */
    public int getSideLength() {
        return sideLength;
    }

    public void setZoom(double zoomFactor) {

        this.zoomFactor = zoomFactor;
    }

    public double getxMin() {
        return xMin;
    }

    public double getxMax() {
        return xMax;
    }

    public double getyMin() {
        return yMin;
    }

    public double getyMax() {
        return yMax;
    }

    public double getXLength() {
        return this.xMax-this.xMin;
    }

    public double getYLength() {
        return this.yMax-this.yMin;
    }
}

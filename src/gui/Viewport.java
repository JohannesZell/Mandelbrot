package gui;

/**
 * Class which converts the pictures coordinate system to the coordinate system of the complex number ones
 * @author Ronny Kohlhaus
 */
public class Viewport {

    private int sideLength;
    private double xMin, xMax, yMin, yMax, zoomFactor;
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
        this.zoomFactor = zoomFactor;
    }

    public void zoom(double centerX, double centerY) {
        double[] centerCoordinates = this.getCoordinates(centerX, centerY);
        double newXRadius, newYRadius;
            newXRadius = (getXLength()/this.zoomFactor)/2;
            newYRadius = (getYLength()/this.zoomFactor)/2;

        this.xMin = centerCoordinates[0]-newXRadius;
        this.xMax = centerCoordinates[0]+newXRadius;
        this.yMin = centerCoordinates[1]-newYRadius;
        this.yMax = centerCoordinates[1]+newYRadius;
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

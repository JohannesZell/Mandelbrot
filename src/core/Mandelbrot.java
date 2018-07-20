package core;
import java.awt.image.BufferedImage;
/**
 * @autor Johannes Zell
 * class for generating Mandelbrot and Julia set
 */
public final class Mandelbrot {

    /**
     * constructor of the mandelbrot class
     */
    public Mandelbrot() {
    }
    /**
     *
     */
    public static final double infinity = 16;

    /**
     * Buffered image for the Mandelbrot set
     */
    private BufferedImage image;

    /**
     * maximum iterations
     */
    public static final int maxIterations = 100;

    /**
     * min value x-axis
     */
    private double xMin;

    /**
     * max value x-axis
     */
    private double xMax;

    /**
     * min value y-axis
     */
    private double yMin;

    /**
     * max value y-axis
     */
    private double yMax;

    /**
     * new ColorGenerator
     */
    private ColorGenerator colorGenerator;

    /**
     * Value to check if the Julia set should be generated
     */
    private boolean renderJulia;

    /**
     * Method that generates the Mandelbrot set
     */
    public void generateMandelbrot() {
        ComplexNum z = new ComplexNum(0, 0);
        ComplexNum c = new ComplexNum(0, 0);

        int iterations;
        int x, y;
        for (x = 0; x < image.getWidth(); x++) {
            for (y = 0; y < image.getHeight(); y++) {

                c.re = map(x, 0, image.getWidth(), xMin, xMax);
                c.im = map(y, 0, image.getHeight(), yMin, yMax);
                z.im = 0;
                z.re = 0;


                iterations = 0;
                while (iterations < maxIterations && z.norm() < infinity) {
                    z = (z.multiply(z)).add(c);
                    iterations++;
                }
                image.setRGB(x, y, colorGenerator.getColorFor(iterations, maxIterations).getRGB()); //sets the pixel with a calculated color
            }
        }
    }
    /**
     * Method that generates the Julia set
     */
    public void generateJulia(){
        ComplexNum z = new ComplexNum(0, 0);
        ComplexNum setJulia = new ComplexNum(-0.8342, 0.0145);
        int iterations;
        int x, y;
        for (x = 0; x < image.getWidth(); x++) {
            for (y = 0; y < image.getHeight(); y++) {

                z.re = map(x, 0, image.getWidth(), xMin, xMax);
                z.im = map(y, 0, image.getHeight(), yMin, yMax);

                iterations = 0;
                while (iterations < maxIterations && z.norm() < infinity) {
                    z = (z.multiply(z)).add(setJulia);
                    iterations++;
                }
                image.setRGB(x, y, colorGenerator.getColorFor(iterations, maxIterations).getRGB()); //sets the pixel with a calculated color
            }
        }
    }

    /**
     * Method that sets local variables and start a new Generating thread
     * @param image BufferedImage on which is drawn
     * @param xMin xMin value from the axis
     * @param xMax xMax value from the axis
     * @param yMin yMin value from the axis
     * @param yMax yMax value from the axis
     */
    public void generateMandelbrotImage(BufferedImage image, double xMin, double xMax, double yMin, double yMax) throws InterruptedException {
        this.image = image;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.renderJulia = false;
        this.colorGenerator = new HueColorGenerator(); //Generates a new colorGenerator object
        Thread thread = new Thread(new Generating()); //generates a new thread object
        thread.start();
        thread.join(); //Wait for the thread to finish
    }

    /**
     * Method that sets local variables and start a new thread
     * @param image BufferedImage on which is drawn
     * @param xMin xMin value from the axis
     * @param xMax xMax value from the axis
     * @param yMin yMin value form the axis
     * @param yMax yMax value from the axis
     */
    public void generateJuliaImage(BufferedImage image, double xMin, double xMax, double yMin, double yMax) throws InterruptedException{
        this.image = image;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.colorGenerator = new HueColorGenerator(); //Generates a new colorGenerator object
        Thread thread = new Thread(new Generating()); //generates a new thread object
        thread.start();
        thread.join(); //Wait for the thread to finish
    }

    /**
     * Maps the input value to the corresponding axis
     * @param in x or y value to be maped
     * @param inMin min value
     * @param inMax the height or width from the image
     * @param mapMin x or y min value from the axis
     * @param mapMax x or y max value from the axis
     * @return maped value
     */
    public double map(double in, double inMin, double inMax, double mapMin, double mapMax) {
        return (in - inMin) * (mapMax - mapMin) / (inMax - inMin) + mapMin;

    }

    /**
     * private class which implements the Runnable interface
     */
    private class Generating implements Runnable{
        /**
         * constructor
         */
        public Generating() {
        }

        /**
         * run method that is called when a new thread is started
         * @see java.lang.Runnable#run()
         */
        public void run() {
            if (renderJulia == true) {
                generateJulia(); //Calls the generateJulia method
            } else {
                generateMandelbrot(); //Calls the generateMandelbrot method
            }
        }
    }
}
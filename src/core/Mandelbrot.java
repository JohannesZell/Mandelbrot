package core;
import java.awt.image.BufferedImage;
/*
 * class for generating Mandelbrot and Julia set
 */
public final class Mandelbrot {

    /*
     * constructor of the mandelbrot class
     */
    public Mandelbrot() {
    }

    /*
     * maximum iterations
     */
    public static final int maxIterations = 100;

    /*
     *
     */
    public static final double infinity = 16;

    /*
     * Buffered image for the Mandelbrot set
     */
    private BufferedImage image;

    /*
     * complex number for the julia set
     */
    private ComplexNum setJulia;

    /*
     * min value x-axis
     */
    private double xMin;

    /*
     * max value x-axis
     */
    private double xMax;

    /*
     * min value y-axis
     */
    private double yMin;

    /*
     * max value y-axis
     */
    private double yMax;

    /*
     * new ColorGenerator
     */
    private ColorGenerator colorGenerator;


    //private ComplexNum juliaConstant;

    /*
     * Value to check if the Julia set should be generated
     */
    private boolean renderJulia;

    /*
     *
     */
    private class Generating implements Runnable{

        public Generating() {

        }
        /*
         * run method that is called when a new thread is started
         * @see java.lang.Runnable#run()
         */
        public void run() {
            if (renderJulia == true) {
                generateJulia();
            } else {
                generateMandelbrot();
            }
        }
    }

    /*
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
                image.setRGB(x, y, colorGenerator.getColorFor(iterations, maxIterations).getRGB());
            }
        }
    }
    /*
     * Method that generates the Julia set
     */
    public void generateJulia(){
        ComplexNum z = new ComplexNum(0, 0);
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
                image.setRGB(x, y, colorGenerator.getColorFor(iterations, maxIterations).getRGB());
            }
        }
    }

    /*
     * Method that sets local variables and start a new Generating thread
     */
    public void generateMandelbrotImage(BufferedImage image, double xMin, double xMax, double yMin, double yMax) throws InterruptedException {
        this.image = image;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.renderJulia = false;
        this.colorGenerator = new HueColorGenerator();
        Thread thread = new Thread(new Generating());
        thread.start();
        thread.join();
    }

    /*
     * Method that sets local variables and start a new Generating thread
     */
    public void generateJuliaImage(BufferedImage image, double xMin, double xMax, double yMin, double yMax, ComplexNum julia, ColorGenerator colorGenerator) {
        this.image = image;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.renderJulia = true;
        //this.juliaConstant = julia;
        this.colorGenerator = colorGenerator;
        this.setJulia = julia;
        Thread thread = new Thread(new Generating());
        thread.start();
    }

    public static double map(double in, double inMin, double inMax, double mapMin, double mapMax) {
        return (in - inMin) * (mapMax - mapMin) / (inMax - inMin) + mapMin;
    }


    public static double map(double in, double inMax, double mapMin, double mapMax) {
        return map(in, 0, inMax, mapMin, mapMax);
    }


}
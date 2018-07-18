package core;
import java.awt.image.BufferedImage;

public final class Mandelbrot {


    public Mandelbrot() {
    }


    private class RenderTask extends Thread {

        private int x0, y0, w, h;

        public RenderTask(int x0, int y0, int w, int h) {
            this.x0 = x0;
            this.y0 = y0;
            this.w = w;
            this.h = h;
        }


        public void run() {
            if (generateJuliaSet) {
                generatePartialJulia(x0, y0, w, h);
            } else {
                generatePartialMandelbrot(x0, y0, w, h);
            }
        }
    }


    private Thread[] tasks;


    public static final int maxIterations = 1000;


    public static final double maxNorm = 20;

    private BufferedImage image;

    private double xValueMin;

    private double xValueMax;

    private double yValueMin;

    private double yValueMax = 2;


    private ColorGenerator colorGenerator;

    private ComplexNum juliaConstant;

    private boolean generateJuliaSet;


    public boolean getIsGenerating() {
        // return this.isGenerating;
        if (tasks == null) {
            return false;
        }
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null && !tasks[i].isAlive()) {
                return true;
            }
        }
        return false;
    }


    private void generatePartialMandelbrot(int x0, int y0, int w, int h) {

        BufferedImage subImage = image.getSubimage(x0, y0, w, h);
        ComplexNum z = new ComplexNum(0, 0);
        ComplexNum c = new ComplexNum(0, 0);

        int iterations;
        int x, y;
        for (x = 0; x < subImage.getWidth(); x++) {
            for (y = 0; y < subImage.getHeight(); y++) {

                c.re = map(x0 + x, 0, image.getWidth(), xValueMin, xValueMax);
                c.im = map(y0 + y, 0, image.getHeight(), yValueMin, yValueMax);
                z.im = 0;
                z.re = 0;

                iterations = 0;
                while (iterations < maxIterations && z.norm() < maxNorm) {
                    // z => z² + c
                    z = (z.multiply(z)).add(c);
                    iterations++;
                }
                subImage.setRGB(x, y, colorGenerator.getColorFor(iterations, maxIterations).getRGB());
            }
        }
    }

    private void generatePartialJulia(int x0, int y0, int w, int h) {

        BufferedImage subImage = image.getSubimage(x0, y0, w, h);
        ComplexNum z = new ComplexNum(0, 0);

        int iterations;
        int x, y;
        for (x = 0; x < subImage.getWidth(); x++) {
            for (y = 0; y < subImage.getHeight(); y++) {

                z.re = map(x0 + x, 0, image.getWidth(), xValueMin, xValueMax);
                z.im = map(y0 + y, 0, image.getHeight(), yValueMin, yValueMax);

                iterations = 0;
                while (iterations < maxIterations && z.norm() < maxNorm) {
                    // z => z² + c
                    z = (z.multiply(z)).add(juliaConstant);
                    iterations++;
                }
                subImage.setRGB(x, y, colorGenerator.getColorFor(iterations, maxIterations).getRGB());
            }
        }

    }

    public void generateMandelbrotImage(BufferedImage image, double xValueMin, double xValueMax, double yValueMin, double yValueMax, ColorGenerator colorGenerator) {

///       if (getIsGenerating()) {
//            throw new IllegalThreadStateException(
//                    "Cannot generate a new image while Generator is already calculating an image.");
//        } Macht aktuell noch Probleme...


        this.image = image;
        this.xValueMin = xValueMin;
        this.xValueMax = xValueMax;
        this.yValueMin = yValueMin;
        this.yValueMax = yValueMax;
        this.colorGenerator = colorGenerator;
        System.out.println(xValueMax);

        this.generateJuliaSet = false;

        int numberOfTasks;
        // Getting the number of (virtual) processor cores on the machine
        numberOfTasks = Runtime.getRuntime().availableProcessors();

        tasks = new RenderTask[numberOfTasks];
        for (int i = 0; i < numberOfTasks; i++) {
            tasks[i] = new RenderTask(i * (image.getWidth() / numberOfTasks), 0, (image.getWidth() / numberOfTasks),
                    image.getHeight());
        }

        for (int i = 0; i < numberOfTasks; i++) {
            tasks[i].start();
        }

    }


    public void generateMandelbrotImage(BufferedImage image, double xValueMin, double xValueMax, double yValueMin,
                                        double yValueMax) {
         generateMandelbrotImage(image, xValueMin, xValueMax, yValueMin, yValueMax, new HueColorGenerator());
    }


    public void generateJuliaImage(BufferedImage image, double xValueMin, double xValueMax, double yValueMin,
                                   double yValueMax, ComplexNum juliaConstant, ColorGenerator colorGenerator) {

//        if (getIsGenerating()) {
//            throw new IllegalThreadStateException(
//                    "Cannot generate a new image while Generator is already calculating an image.");
//        }

        this.image = image;
        this.xValueMin = xValueMin;
        this.xValueMax = xValueMax;
        this.yValueMin = yValueMin;
        this.yValueMax = yValueMax;
        this.colorGenerator = colorGenerator;
        this.juliaConstant = juliaConstant;

        this.generateJuliaSet = true;

        int numberOfTasks;
        // Getting the number of (virtual) processor cores on the machine
        numberOfTasks = Runtime.getRuntime().availableProcessors();

        tasks = new RenderTask[numberOfTasks];
        for (int i = 0; i < numberOfTasks; i++) {
            tasks[i] = new RenderTask(i * (image.getWidth() / numberOfTasks), 0, (image.getWidth() / numberOfTasks),
                    image.getHeight());
        }

        for (int i = 0; i < numberOfTasks; i++) {
            tasks[i].start();
        }

    }


    public void generateJuliaImage(BufferedImage image, double xValueMin, double xValueMax, double yValueMin,
                                   double yValueMax, ComplexNum juliaConstant) {
        generateJuliaImage(image, xValueMin, xValueMax, yValueMin, yValueMax, juliaConstant, new HueColorGenerator());
    }


    public static double map(double in, double inMin, double inMax, double mapMin, double mapMax) {
        return (in - inMin) * (mapMax - mapMin) / (inMax - inMin) + mapMin;
    }


    public static double map(double in, double inMax, double mapMin, double mapMax) {
        return map(in, 0, inMax, mapMin, mapMax);
    }

}
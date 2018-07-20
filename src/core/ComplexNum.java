package core;
/**
 * class to manage the calculation with complex numbers
 */
public class ComplexNum {

    /**
     * Imaginary part of the complex number
     */
    public double im;
    /**
     * Real part of the complex number
     */
    public double re;

    /**
     * Constructor that generates a new complex number
     */
    public ComplexNum() {

        this(0, 0);
    }

    /**
     * Generates a new complex number
     */
    public ComplexNum(double re, double im) {
        this.re = re;
        this.im = im;
    }

    /**
     * returns sum of two complex numbers
     */
    public ComplexNum add(ComplexNum cn) {
        return new ComplexNum(this.re + cn.re, this.im + cn.im);
    }

    /**
     *  returns the product of two complex numbers
     */
    public ComplexNum multiply(ComplexNum cn) {
        return new ComplexNum(
                this.re * cn.re - this.im * cn.im,
                this.re * cn.im + this.im * cn.re
        );
    }

    /**
     * returns the norm of the complex number
     */
    public double norm() {
        return re * re + im * im;
    }

}
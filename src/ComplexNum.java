/*
 * Represents a complex number consisting of a imaginary, and a real part.
 */
public class ComplexNum {


    public double im;

    public double re;


    public ComplexNum(double re, double im) {
        this.re = re;
        this.im = im;
    }


    public ComplexNum() {
        this(0, 0);
    }


    public ComplexNum getCopy() {
        return new ComplexNum(this.re, this.im);
    }


    public ComplexNum add(ComplexNum other) {
        return new ComplexNum(this.re + other.re, this.im + other.im);
    }


    public ComplexNum multiply(ComplexNum other) {
        return new ComplexNum(
                this.re * other.re - this.im * other.im,
                this.re * other.im + this.im * other.re
        );
    }


    public double norm() {
        return re * re + im * im;
    }


    public double abs() {
        return Math.sqrt(this.norm());
    }

}
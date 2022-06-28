public class Complex {
    private double real;
    private double imaginary;

    public Complex() {
        this(0.0, 0.0);
    }

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public double abs() {
        return Math.hypot(this.real, this.imaginary);
    }

    public static double abs(Complex complex) {
        return Math.hypot(complex.getReal(), complex.getImaginary());
    }

    public Complex add(Complex complex2) {
        Complex complex1 = this;
        double newReal = complex1.getReal() + complex2.getReal();
        double newImaginary = complex1.getImaginary() + complex2.getImaginary();

        return new Complex(newReal, newImaginary);
    }

    public static Complex add(Complex complex1, Complex complex2) {
        double newReal = complex1.getReal() + complex2.getReal();
        double newImaginary = complex1.getImaginary() + complex2.getImaginary();

        return new Complex(newReal, newImaginary);
    }

    public Complex subtract(Complex complex2) {
        Complex complex1 = this;
        double newReal = complex1.getReal() - complex2.getReal();
        double newImaginary = complex1.getImaginary() - complex2.getImaginary();

        return new Complex(newReal, newImaginary);
    }

    public static Complex subtract(Complex complex1, Complex complex2) {
        double newReal = complex1.getReal() - complex2.getReal();
        double newImaginary = complex1.getImaginary() - complex2.getImaginary();

        return new Complex(newReal, newImaginary);
    }

    public Complex conjugate() {
        return new Complex(this.getReal(), -(this.getImaginary()));
    }

    public static Complex conjugate(Complex complexNumber) {
        return new Complex(complexNumber.getReal(), -(complexNumber.getImaginary()));
    }

    public Complex inverse() {
        double denominator = this.getReal() * this.getReal() + this.getImaginary() * this.getImaginary();

        return new Complex(this.getReal() / denominator, -(this.getImaginary() / denominator));
    }

    public static Complex inverse(Complex complexNumber) {
        double denominator = complexNumber.getReal() * complexNumber.getReal()
                + complexNumber.getImaginary() * complexNumber.getImaginary();

        return new Complex(complexNumber.getReal() / denominator, complexNumber.getImaginary() / denominator);
    }

    public Complex multiply(Complex complexNumber) {
        Complex tempComplex = this;

        double realPart = tempComplex.getReal() * complexNumber.getReal()
                - tempComplex.getImaginary() * complexNumber.getImaginary();
        double imaginaryPart = tempComplex.getReal() * complexNumber.getImaginary()
                + tempComplex.getImaginary() * complexNumber.getReal();

        return new Complex(realPart, imaginaryPart);
    }

    public static Complex multiply(Complex complex1, Complex complex2) {
        double realPart = complex1.getReal() * complex2.getReal()
                - complex1.getImaginary() * complex2.getImaginary();
        double imaginaryPart = complex1.getReal() * complex2.getImaginary()
                + complex1.getImaginary() * complex2.getReal();

        return new Complex(realPart, imaginaryPart);
    }

    public Complex multiply(double scalarValue) {
        return new Complex(this.getReal() * scalarValue, this.getImaginary() * scalarValue);
    }

    public Complex divide(double scalarValue) {
        return new Complex(this.getReal() / scalarValue, this.getImaginary() / scalarValue);
    }

    @Override
    public String toString() {
        if (imaginary == 0) {
            return Double.toString(real);
        } else if (real == 0) {
            return Double.toString(imaginary) + "i";
        } else if (imaginary < 0) {
            return Double.toString(real) + " - " + Double.toString(-imaginary) + "i";
        } else {
            return Double.toString(real) + " + " + Double.toString(imaginary) + "i";
        }
    }

}

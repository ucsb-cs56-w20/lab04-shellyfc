package edu.ucsb.cs56.ratcalc.model;

/**
 * A class to represent a rational number with a numerator and denominator
 *
 * @author P. Conrad for CS56 F16
 *
 */

public class Rational {

    private int num;
    private int denom;

    /**
     * greatest common divisor of a and b
     *
     * @param a first number
     * @param b second number
     * @return gcd of a and b
     */
    public static int gcd(int a, int b) {
        if (a == 0)
            return b;
        else if (b == 0)
            return a;
        else
            return gcd(b % a, a);
    }

    public Rational() {
        this.num = 1;
        this.denom = 1;
    }

    public Rational(int num, int denom) {
        if (denom == 0) {
            throw new IllegalArgumentException("denominator may not be zero");
        }
        this.num = num;
        this.denom = denom;
        if (num != 0) {
            int gcd = Rational.gcd(num, denom);
            this.num /= gcd;
            this.denom /= gcd;
        }
    }

    public String toString() {
        if (denom == 1 || num == 0)
            return "" + num;
        return num + "/" + denom;
    }

    public int getNumerator() {
        return this.num;
    }

    public int getDenominator() {
        return this.denom;
    }

    public Rational times(Rational r) {
        return new Rational(this.num * r.num, this.denom * r.denom);
    }

    public static Rational product(Rational a, Rational b) {
        return new Rational(a.num * b.num, a.denom * b.denom);
    }

    // public static Rational sum(Rational a, Rational b) {


    //     return new Rational(); // stub
    // }

    // public static Rational difference(Rational a, Rational b) {
    //     return new Rational(); // stub
    // }

    // public static Rational quotient(Rational a, Rational b) {
    //     return new Rational(); // stub
    // }

    public static int lcm(int a, int b) {

        int gcf = gcd(a, b);

        return Math.abs(a * b) / gcf;

    }


    public Rational plus(Rational r) {

        int cd = lcm(this.denom, r.denom);

        int r1 = cd / this.denom;

        int r2 = cd / r.denom;

        Rational nr = new Rational(this.num * r1 + r.num * r2, cd);

	if(nr.denom < 0) {

		nr.denom *= -1;

		nr.num *= -1;

	}

        return nr;

    }


    public static Rational sum(Rational a, Rational b) {

        return a.plus(b);

    }


    public Rational minus(Rational r) {

        Rational tmp = new Rational(-1, 1);

        Rational nr = this.plus(r.times(tmp));

        return nr;

    }


    public static Rational difference(Rational a, Rational b) {

        return a.minus(b);

    }


    public Rational reciprocalOf() {

        if (num == 0) {

            throw new ArithmeticException("Cannot have a denominator of 0");

        }

	Rational r = new Rational(this.denom, this.num);

	if(r.denom < 0) {

		r.num *= -1;

		r.denom *= -1;

	}

    	return r;

    }




    public Rational dividedBy(Rational r) {

	return this.times(r.reciprocalOf());

    }


    public static Rational quotient(Rational a, Rational b) {

        return a.dividedBy(b);

    }

    /**
     * For testing getters.
     *
     * @param args unused
     */

    public static void main(String[] args) {
        Rational r = new Rational(5, 7);
        System.out.println("r.getNumerator()=" + r.getNumerator());
        System.out.println("r.getDenominator()=" + r.getDenominator());
    }

}

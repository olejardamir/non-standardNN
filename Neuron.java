// Neuron class,  by Damir Olejar

// Idea is to have a neural network capable of generating the random data
// This DOES NOT conform any known standards and is entirely made up by me.
// This works simply by having a random-number generator that can be trained to generate random, pseudo random, or constants.
// The inputs can be 0-dimensional as well as 1-4 dimensional, except the decimals for optimizing, we can add more dimensions too (total of 8-dimensions).
// The inputs were chosen by guess and observation of the Java's Random class, so this can change.
// To make the neural net generate a random number, pass a null, otherwise use a lesser (or greater?) dimension with setters
// the variables were set to Random class constants, while nanotime is also set as a constant and not a current time.
// Nanotime can be set to a current time using a setter.

import java.util.concurrent.atomic.AtomicLong;

public class Neuron {

    //default values!
    private int bits1 = 22;
    private int bits2 = 27;
    private int bits3 = 21;
    private double DOUBLE_UNIT =  1.1102230246251565E-16;
    private long addend = 11l;
    private long mask = 281474976710655l;
    long multiplier = 35214903917l;
    long nanotime = 432905864460400l; //some constant
    private double decimals = 14;


    //zero dimensional input
    public double getAssymetricSignal0D(){
        double d = nextDouble(null,null,null,null);
        return (2*d)-1;
    }


    //one dimensional input
    public double getAssymetricSignal1D(Double x){
        x = checkForNull(x);
        double dx = x*Math.pow(10,decimals);
        double d = nextDouble(((Double)dx).longValue(),null,null, null);
        return (2*d)-1;
    }


    //A two dimensional input
    public double getAssymetricSignal2D(Double x, Double y){
        x = checkForNull(x);
        y = checkForNull(y);

        double dx = x*Math.pow(10,decimals);
        double dy = y*Math.pow(10,decimals);
        double d = nextDouble(((Double)dx).longValue(),((Double)dy).longValue(),null, null);
        return (2*d)-1;
    }

    //A two dimensional input
    public double getAssymetricSignal3D(Double x, Double y, Double z){
        x = checkForNull(x);
        y = checkForNull(y);
        z = checkForNull(z);

        double dx = x*Math.pow(10,decimals);
        double dy = y*Math.pow(10,decimals);
        double dz = z*Math.pow(10,decimals);

        double d = nextDouble(((Double)dx).longValue(),((Double)dy).longValue(),((Double)dz).longValue(), null);
        return (2*d)-1;
    }


    //A two dimensional input
    public double getAssymetricSignal4D(Double x, Double y, Double z, Double w){
        x = checkForNull(x);
        y = checkForNull(y);
        z = checkForNull(z);
        w = checkForNull(w);

        double dx = x*Math.pow(10,decimals);
        double dy = y*Math.pow(10,decimals);
        double dz = z*Math.pow(10,decimals);
        double dw = w*Math.pow(10,decimals);

        double d = nextDouble(((Double)dx).longValue(),((Double)dy).longValue(),((Double)dz).longValue(), ((Double)dw).longValue());
        return (2*d)-1;
    }


    private Double checkForNull(Double x) {
        if (x == null) {
            x = nextDouble(System.nanoTime(), 35214903917l, 281474976710655l,11l);
        }
        return (2*x)-1;
    }

    //THE MAIN LOGIC !!!
    private double nextDouble(
        Long nanoTime,
        Long multiplier,
        Long mask,
        Long addend
    ) {
        if(nanoTime==null) nanoTime = this.nanotime;
        if(multiplier==null) multiplier = this.multiplier;
        if(mask==null) mask = this.mask;


        AtomicLong seed = new AtomicLong(nanoTime);
        long oldseed, nextseed;
        do {
            oldseed = seed.get();
            nextseed = (oldseed * multiplier + addend) & mask;
        } while (!seed.compareAndSet(oldseed, nextseed));
        long oldseed1, nextseed1;
        do {
            oldseed1 = seed.get();
            nextseed1 = (oldseed1 * multiplier + addend) & mask;
        } while (!seed.compareAndSet(oldseed1, nextseed1));
        return (((long) ((int) (nextseed1 >>> (bits1))) << bits2) + (int) (nextseed >>> (bits3))) * DOUBLE_UNIT;
    }

//--------GETTERS AND SETTERS ARE BELOW---------------
    public int getBits1() {
        return bits1;
    }

    public void setBits1(int bits1) {
        this.bits1 = bits1;
    }

    public int getBits2() {
        return bits2;
    }

    public void setBits2(int bits2) {
        this.bits2 = bits2;
    }

    public int getBits3() {
        return bits3;
    }

    public void setBits3(int bits3) {
        this.bits3 = bits3;
    }

    public double getDOUBLE_UNIT() {
        return DOUBLE_UNIT;
    }

    public void setDOUBLE_UNIT(double DOUBLE_UNIT) {
        this.DOUBLE_UNIT = DOUBLE_UNIT;
    }

    public long getAddend() {
        return addend;
    }

    public void setAddend(long addend) {
        this.addend = addend;
    }

    public long getMask() {
        return mask;
    }

    public void setMask(long mask) {
        this.mask = mask;
    }

    public double getDecimals() {
        return decimals;
    }

    public void setDecimals(double decimals) {
        this.decimals = decimals;
    }

    public long getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(long multiplier) {
        this.multiplier = multiplier;
    }

    public long getNanotime() {
        return nanotime;
    }

    public void setNanotime(long nanotime) {
        this.nanotime = nanotime;
    }


}

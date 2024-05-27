package SAMPLE_TEST_2;

public class SAMPLETEST2 {
    private double XA, YA, VA, XB, YB, VB;

    public SAMPLETEST2(double XA, double YA, double VA, double XB, double YB, double VB) {
        this.XA = XA;
        this.YA = YA;
        this.VA = VA;
        this.XB = XB;
        this.YB = YB;
        this.VB = VB;
    }

    public double timeFromA(double XC) {
        double distance = Math.sqrt(Math.pow((XC - XA), 2) + Math.pow(YA, 2));
        return distance / VA;
    }

    private double timeFromB(double XC) {
        double distance = Math.sqrt(Math.pow((XC - XB), 2) + Math.pow(YB, 2));
        return distance / VB;
    }

    public double pointC() {
        double left = XA;
        double right = XB;
        double precision = 1e-7;

        while (right - left > precision) {
            double mid = (left + right) / 2.0;
            if (timeFromA(mid) < timeFromB(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return (left + right) / 2.0;
    }

    public static void main(String[] args) {
        SAMPLETEST2 secretSearch = new SAMPLETEST2(-1, 1, 1, 1, -1, 0.5);

        double XZ = 0;
        System.out.printf("Time from A to (%.6f, 0): %.6f%n", XZ, secretSearch.timeFromA(XZ));

        double XC = secretSearch.pointC();
        System.out.printf("Point C where both agents meet: %.6f%n", XC);
    }
}

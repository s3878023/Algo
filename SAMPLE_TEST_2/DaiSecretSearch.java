package SAMPLE_TEST_2;
import java.util.*;
public class DaiSecretSearch {
    private double XA, YA,VA, XB, YB, VB;

    public DaiSecretSearch(double XA, double YA, double VA, double XB, double YB, double VB) {
        this.XA = XA;
        this.YA = YA;
        this.VA = VA;
        this.XB = XB;
        this.YB = YB;
        this.VB = VB;
    }

    public double timeFromA(double XC) {
        double distance = Math.sqrt(Math.pow(XC - XA, 2) + Math.pow(YA, 2));
        return distance / VA;
    }

    public double timeFromB(double XC) {
        double distance = Math.sqrt(Math.pow(XC - XA, 2) + Math.pow(YA, 2));
        return distance / VA;
    }

    public double pointC() {
        double left = XA;
        double right = XB;
        double precisePoint = 1e-7;

        while (right - left > precisePoint) {
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
        DaiSecretSearch secretSearch = new DaiSecretSearch(-1, 1, 1, 1, -1, 0.5);
        double XZ = 0;
        System.out.printf("Time from A to", XZ, secretSearch.timeFromA(XZ));

        double XC = secretSearch.pointC();
        System.out.printf("Point C where they meet", XC);
    }
}

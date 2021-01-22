package edu.ib;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

import java.util.ArrayList;

/**
 * Klasa implementująca StepHandler
 */
public class Steps implements StepHandler {
    private ArrayList<Double> x=new ArrayList<>();
    private ArrayList<Double> v=new ArrayList<>();
    private ArrayList<Double> t=new ArrayList<>();

    /**
     * Getter listy tablicowej x
     * @return Lista tablicowa x
     */
    public ArrayList<Double> getX() {
        return x;
    }
    /**
     * Getter listy tablicowej v
     * @return Lista tablicowa v
     */
    public ArrayList<Double> getV() {
        return v;
    }
    /**
     * Getter listy tablicowej t
     * @return Lista tablicowa t
     */
    public ArrayList<Double> getT() {
        return t;
    }

    /**
     * Metoda dodająca do list warunki początkowe
     * @param t0 Czas początkowy
     * @param x0 Warunki początkowe
     * @param t Czas końcowy
     */
    @Override
    public void init(double t0, double[] x0, double t) {
        x.add(x0[0]);
        v.add(x0[1]);
        this.t.add(t0);
    }

    /**
     * Metoda przekazująca wartości kolejnychiteracji
     * @param interpolator Obiekt przechowujący informacje o wartościach w danej iteracji
     * @param isLast Czy jest ostatnia
     * @throws MaxCountExceededException
     */
    @Override
    public void handleStep(StepInterpolator interpolator, boolean isLast) throws MaxCountExceededException {
        double [] r = interpolator.getInterpolatedState();
        x.add(r[0]);
        v.add(r[1]);
        t.add(interpolator.getCurrentTime());
    }
}

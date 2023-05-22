package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class Label implements Comparable<Label> {

    private Node SommetCourant;
    private Boolean Marque;
    private double CoutRealise;
    private Arc Pere;

    public Label(Node SommetCourant, Boolean Marque, double CoutRealise, Arc Pere) {
        this.SommetCourant = SommetCourant;
        this.Marque = Marque;
        this.CoutRealise = CoutRealise;
        this.Pere = Pere;
    }

    public Node getSommetCourant() {
        return this.SommetCourant;
    }

    public Boolean isMarque() {
        return this.Marque;
    }

    public Arc getPere() {
        return this.Pere;
    }

    public double getCost() {
        return this.CoutRealise;
    }

    public void setMark(Boolean mark) {
        this.Marque = mark;
    }

    public void setCost(double d) {
        this.CoutRealise = d;
    }

    public void setPere(Arc pere) {
        this.Pere = pere;
    }

    public int compareTo(Label x) {
        return Double.compare(getTotalCost(), x.getTotalCost());

    }

    public double getTotalCost() {
        return this.CoutRealise;
    }

}

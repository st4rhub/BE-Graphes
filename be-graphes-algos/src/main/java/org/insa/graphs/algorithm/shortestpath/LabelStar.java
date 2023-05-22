package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class LabelStar extends Label {
    private double CoutDestination;

    public LabelStar(Node SommetCourant, Boolean Marque, Double Cout, Arc Pere, double coutDest) {
        super(SommetCourant, Marque, Cout, Pere);
        this.CoutDestination = coutDest;
    }

    public double getTotalCost() {
        return this.getCost() + CoutDestination;
    }
}
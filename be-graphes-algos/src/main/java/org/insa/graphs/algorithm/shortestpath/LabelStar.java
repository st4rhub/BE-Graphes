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
    private float CoutDestination;

    public LabelStar(float CoutDestination, Node SommetCourant, Boolean Marque, float CoutRealise, Arc Pere){
        super(SommetCourant, Marque, CoutRealise, Pere);
        this.CoutDestination = CoutDestination;
    }

    public void getTotalCost(){
        //what is it??
    }
}
package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class Label {
    
    public Node SommetCourant;
    public Boolean Marque;
    public int CoutRealise;
    public Node Pere;

    public Label (Node SommetCourant, Boolean Marque, int CoutRealise, Node Pere){
        this.SommetCourant = SommetCourant;
        this.Marque = Marque;
        this.CoutRealise = CoutRealise;
        this.Pere = Pere;
    }

}

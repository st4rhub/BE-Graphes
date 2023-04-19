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

    private Node SommetCourant;
    private Boolean Marque;
    private int CoutRealise;
    private Node Pere;

    public Label(Node SommetCourant, Boolean Marque, int CoutRealise, Node Pere) {
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

    public Node getPere() {
        return this.Pere;
    }

    public int getCost() {
        int cout = this.CoutRealise;
        return cout;
    }

}

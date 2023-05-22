package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public Graph graph;

    public Label newLabel(Node node, Boolean bool, double value, Arc father) {
        return new Label(node, bool, value, father);
    }

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    public double getCostArc(Arc arc, Mode mode) {
        if (mode == Mode.TIME) {
            return arc.getMinimumTravelTime();
        } else {
            return (double) arc.getLength();
        }
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        Mode mode = data.getMode();
        ShortestPathSolution solution = null;
        // Initialisation
        graph = data.getGraph();
        Label[] labels = new Label[graph.size()];
        for (int id = 0; id < graph.size(); id++) {
            Node node_i = graph.get(id);
            labels[id] = newLabel(node_i, false, Integer.MAX_VALUE, null);
        }
        Node s = data.getOrigin();
        labels[s.getId()].setCost(0);
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        tas.insert(labels[s.getId()]);
        notifyOriginProcessed(s);

        // Itération
        Node lastnode = labels[data.getOrigin().getId()].getSommetCourant();

        while (!(tas.isEmpty()) && lastnode != data.getDestination()) { // tant qu'l existe des sommets non marqués
            Label label_min = tas.deleteMin();
            Node x = label_min.getSommetCourant();
            labels[x.getId()].setMark(true);
            notifyNodeMarked(x);
            lastnode = x;
            List<Arc> successeurs_x = x.getSuccessors();
            for (int j = 0; j < successeurs_x.size(); j++) {
                Arc x_y = successeurs_x.get(j);
                Node y = x_y.getDestination();
                int id_y = y.getId();
                if (!(labels[id_y].isMarque())) { // si y n'est pas marqué
                    notifyNodeReached(y);
                    double cost_x_y = getCostArc(x_y, mode);
                    if (labels[id_y].getCost() > labels[x.getId()].getCost() + cost_x_y) {
                        labels[id_y].setPere(x_y);
                        if (labels[id_y].getCost() != Integer.MAX_VALUE) {
                            tas.remove(labels[id_y]);
                            labels[id_y].setCost(labels[x.getId()].getCost() + cost_x_y);
                            tas.insert(labels[id_y]);
                        } else {
                            labels[id_y].setCost(labels[x.getId()].getCost() + cost_x_y);
                            tas.insert(labels[id_y]);
                        }
                    }
                }
            }
        }

        // if pas de chemin a rajouter
        if (lastnode != data.getDestination()) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
            return solution;
        }
        notifyDestinationReached(lastnode);
        Label label_final = labels[lastnode.getId()];
        ArrayList<Arc> arcs_finaux = new ArrayList<Arc>();

        while (label_final.getPere().getOrigin() != data.getOrigin()) {
            arcs_finaux.add(label_final.getPere());
            label_final = labels[label_final.getPere().getOrigin().getId()];
        }
        arcs_finaux.add(label_final.getPere());

        Collections.reverse(arcs_finaux);
        Path path = new Path(graph, arcs_finaux);

        solution = new ShortestPathSolution(data, Status.OPTIMAL, path);

        return solution;
    }
}

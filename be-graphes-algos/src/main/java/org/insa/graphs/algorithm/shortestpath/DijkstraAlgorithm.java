package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // Initialisation
        Graph graph = data.getGraph();
        Label[] labels = new Label[graph.size()];
        for (int id = 0; id < graph.size(); id++) {
            Node node_i = graph.get(id);
            labels[id] = new Label(node_i, false, Integer.MAX_VALUE, null);
        }
        Node s = data.getOrigin();
        Label label_s = labels[s.getId()];
        label_s.setCost(0);
        BinaryHeap<Node> tas = new BinaryHeap<Node>();
        tas.insert(s);

        // Itération
        Node lastnode = labels[0].getSommetCourant();

        while (!(tas.isEmpty()) && lastnode != data.getDestination()) { // tant qu'l existe des sommets non marqués
            Node x = tas.deleteMin();
            Label label_x = labels[x.getId()];
            label_x.setMark(true);
            lastnode = x;
            List<Arc> successeurs_x = x.getSuccessors();
            for (int j = 0; j < successeurs_x.size(); j++) {
                Arc x_y = successeurs_x.get(j);
                Node y = x_y.getDestination();
                int id_y = y.getId();
                Label label_y = labels[id_y];
                if (!(label_y.isMarque())) { // si y n'est pas marqué
                    if (label_y.getCost() > label_x.getCost() + x_y.getLength()) {
                        label_y.setPere(x_y);
                        if (label_y.getCost() != Integer.MAX_VALUE) {
                            tas.remove(y);
                            label_y.setCost(label_x.getCost() + x_y.getLength());
                            tas.insert(y);
                        } else {
                            label_y.setCost(label_x.getCost() + x_y.getLength());
                            tas.insert(y);

                        }
                    }
                }
            }
        }

        // if pas de chemin a rajouter
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

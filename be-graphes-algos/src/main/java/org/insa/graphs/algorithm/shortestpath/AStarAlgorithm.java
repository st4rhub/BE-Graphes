package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public Label newLabel(Node node, Boolean bool, double value, Arc father) {
        double cout_dest = Point.distance(node_dest.getPoint(), node.getPoint());
        return new LabelStar(node, bool, value, father, cout_dest);
    }

    Node node_dest = null;

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
        node_dest = data.getDestination();
    }

}

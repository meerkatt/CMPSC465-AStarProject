import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Path{
    private final Node startingNode;
    private final Node goalNode;
    private Node currentNode;
    private double nodesVisited;


    public Path(Node start, Node end) {
        this.startingNode = start;
        this.goalNode = end;
        this.currentNode = start;
    }

    public Node getCurrentNode() {
        return currentNode;
    }


    public double distanceCalculator(int x1, int y1, int x2, int y2) {
        double xDifference = Math.abs(x1 - x2);
        double yDifference = Math.abs(y1 - y2);
        double distance = Math.sqrt((Math.pow(xDifference, 2) + Math.pow(yDifference, 2)));
        return distance;
    }

    public double gCostCalculator() {
        return nodesVisited;
    }
    
    public double hCostCalculator(Node current) {
        return distanceCalculator(current.getXCoord(), current.getYCoord(), goalNode.getXCoord(), goalNode.getYCoord());
    }
    
    public double totalCostCalculator(Node current) {
        return current.getGCost() + hCostCalculator(current);
    }

    public ArrayList<Node> traverse() {
        nodesVisited = 0;
        ArrayList<Node> availableNodes = new ArrayList<>();
        while(currentNode != goalNode) {
            for(int i = 0; i < 4; i++) {
                Node[] neighbors = currentNode.getNeighbors();
                Node element = neighbors[i];
                if(neighbors != null) {
                    if(element != null){
                        if(element.getTotalCost() == 0.0) {
                            if(element.getGCost() == 0.0) {
                                element.setGCost(gCostCalculator());
                                element.setPreviousNode(currentNode);
                            }
                            element.setHCost(hCostCalculator(element));
                            element.setTotalCost(totalCostCalculator(element));
                            availableNodes.add(element);
                        }
                    }
                }
            }

            // sort nodes
            // TODO: when sorting is removed it still works???
            Collections.sort(availableNodes, Comparator.naturalOrder());    

            if (availableNodes.isEmpty()) {
                return null;
            }
            currentNode = availableNodes.get(0);
            availableNodes.remove(0);
            nodesVisited = currentNode.getGCost();
        }

        ArrayList<Node> path = new ArrayList<>();
        
        while (currentNode != startingNode) {
            path.add(currentNode);
            currentNode = currentNode.getPreviousNode();
        }

        return path;
    }
}
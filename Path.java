import java.util.ArrayList;

public class Path{
    private Node startingNode;
    private Node goalNode;
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
        double xDifference = Math.abs(x1-x2);
        double yDifference = Math.abs(y1-y2);
        double distance = Math.sqrt((Math.pow(xDifference, 2) + Math.pow(yDifference,2)));
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

    public void swapNodes(ArrayList<Node> nodeList, int index1, int index2) {
        Node temp = nodeList.get(index1);
        nodeList.set(index1, nodeList.get(index2));
        nodeList.set(index2, temp);   
    }

    public void sortNodes(ArrayList<Node> nodeList) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < nodeList.size()-1; i++) {
                if (nodeList.get(i).compareTo(nodeList.get(i+1)) == 1) {
                    swapNodes(nodeList, i, i+1);
                    isSorted = false;
                    break;
                }
            }
        }
    }

    public ArrayList<Node> traverse() {
        nodesVisited = 0;
        ArrayList<Node> availableNodes = new ArrayList<Node>();
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
            sortNodes(availableNodes);
            if(availableNodes.isEmpty())
            {
                return null;
            }
            Node nextNode = availableNodes.get(0);
            currentNode = nextNode;
            nextNode = null;
            availableNodes.remove(0);
            nodesVisited = currentNode.getGCost();
        }
        ArrayList<Node> path = new ArrayList<Node>();
        while(currentNode != startingNode)
        {
            path.add(currentNode);
            currentNode = currentNode.getPreviousNode();
        }
        return path;
    }
}
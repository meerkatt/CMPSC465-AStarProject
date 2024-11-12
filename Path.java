import java.util.ArrayList;

public class Path{
    private Node startingNode;
    private Node goalNode;
    private Node currentNode;

    private ArrayList<Node> availableNodes;

    public Path(Node start, Node end)
    {
        this.startingNode = start;
        this.goalNode = end;
        this.currentNode = start;
        availableNodes = new ArrayList<Node>();
    }

    public double distanceCalculator(int x1, int y1, int x2, int y2)
    {
        double xDifference = Math.abs(x1-x2);
        double yDifference = Math.abs(y1-y2);
        double distance = Math.sqrt((Math.pow(xDifference, 2) + Math.pow(yDifference,2)));
        return distance;
    }

    public double gCostCalculator(Node current)
    {
        return distanceCalculator(current.getXCoord(), current.getYCoord(), startingNode.getXCoord(), startingNode.getYCoord());
    }
    
    public double hCostCalculator(Node current)
    {
        return distanceCalculator(current.getXCoord(), current.getYCoord(), goalNode.getXCoord(), goalNode.getYCoord());
    }
    
    public double totalCostCalculator(Node current)
    {
        return gCostCalculator(current) + hCostCalculator(current);
    }

    public void swapNodes(ArrayList<Node> nodeList, int index1, int index2)
    {
        Node temp = nodeList.get(index1);
        nodeList.set(index1, nodeList.get(index2));
        nodeList.set(index2, temp);   
    }

    public void sortNodes(ArrayList<Node> nodeList)
    {;
        boolean isSorted = false;
        while(!isSorted)
        {
            isSorted = true;
            for(int i = 0; i < nodeList.size()-1; i++)
            {
                if(nodeList.get(i).compareTo(nodeList.get(i+1)) == 1)
                {
                    swapNodes(nodeList, i, i+1);
                    isSorted = false;
                    break;
                }
            }
            
        }
        
        
    }

    public void traverse(){
        while(currentNode != goalNode)
        {
            for(Node element:currentNode.getNeighbors())
            {
                if(element != null)
                {
                    element.setGCost(gCostCalculator(element));
                    element.setHCost(hCostCalculator(element));
                    element.setTotalCost(totalCostCalculator(element));
                    availableNodes.add(element);
                }
            }
            sortNodes(availableNodes);

        }
    }

}
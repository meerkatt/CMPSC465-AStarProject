public class Node {
    private Node[] neighborNodes; // Order of Nodes: North, East, South, West

    private double gCost;
    private double hCost;
    private double totalCost;
    private int xCoord;
    private int yCoord;

    private Node previousNode;

    ////Constructors

    public Node()
    {
        this.neighborNodes = new Node[4];
        for(Node element:this.neighborNodes)
        {
            element = null;
        }
        this.previousNode = null;

    }

    public Node(Node[] neighbors, Node previous)
    {
        this.neighborNodes = neighbors;
        this.previousNode = previous;
    }

    //// Mutators

    public void setNeighbors(Node[] neighbors)
    {
        this.neighborNodes = neighbors;
    }

    public void setNeighbors(Node north, Node east, Node south, Node west)
    {
        this.neighborNodes = new Node[]{north, east, south, west};
    }

    public void setPreviousNode(Node previous)
    {
        this.previousNode = previous;
    }

    public void setXY(int x, int y)
    {
        this.xCoord = x;
        this.yCoord = y;
    }


    //// Accessors
    public Node[] getNeighbors()
    {
        return neighborNodes;
    }

    public double getGCost()
    {
        return gCost;
    }

    public double getHCost()
    {
        return hCost;
    }

    public double getTotalCost()
    {
        return totalCost;
    }

    public int getXCoord()
    {
        return xCoord;
    }

    public int getYCoord()
    {
        return yCoord;
    }

    public Node getPreviousNode()
    {
        return previousNode;
    }
}

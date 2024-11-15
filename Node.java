

public class Node implements Comparable{
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

    public Node(int x, int y)
    {
        this.xCoord = x;
        this.yCoord = y;
    }

    public Node(Node[] neighbors, Node previous)
    {
        this.neighborNodes = neighbors;
        this.previousNode = previous;
    }

    //// Comparator
    public int compareTo(Object otherObject)
    {
        Node otherNode = (Node) otherObject;
        if(this.totalCost < otherNode.getTotalCost())
        {
            return -1;
        }
        else if (this.totalCost > otherNode.getTotalCost()) 
        {
            return 1;
        }
        else{
            if(this.gCost < otherNode.getGCost())
            {
                return -1;
            }
            else if (this.gCost > otherNode.getGCost()) 
            {
                return 1;
            }
            else{
                
                if(this.hCost > otherNode.getHCost()) 
                {
                    return 1;
                }
                else if(this.hCost < otherNode.getHCost())
                {
                    return -1;
                }else
                {
                    return 0;
                }
            }
        }
        
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

    public void deleteNeighbors()
    {
        this.neighborNodes = new Node[4];
    }

    public void setPreviousNode(Node previous)
    {
        this.previousNode = previous;
    }

    public void setGCost(double gCostValue)
    {
        this.gCost = gCostValue;
    }

    public void setHCost(double hCostValue)
    {
        this.hCost = hCostValue;
    }

    public void setTotalCost(double totalCostValue)
    {
        this.totalCost = totalCostValue;
    }

    public void setXY(int x, int y)
    {
        this.xCoord = x;
        this.yCoord = y;
    }

    public void resetCost(){
        this.gCost = 0.0;
        this.hCost = 0.0;
        this.totalCost = 0.0;
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

    ////Methods
    public boolean isNeighbor(Node checkNode)
    {
        for(Node element:neighborNodes){
            if(element.getXCoord() == checkNode.getXCoord() && element.getYCoord() == checkNode.getYCoord())
            {
                return true;
            }
        }
        return false;
    }

}

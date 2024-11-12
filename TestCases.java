import java.util.ArrayList;

public class TestCases {
    public String creatingNodeTest()
    {
        Node test = new Node();
        return "Node Successfully Created!";
    }

    public String settingNodeNeighborsTest()
    {
        Node test = new Node();
        Node n = new Node();
        Node e = new Node();
        Node s = new Node();
        Node w = new Node();
        test.setNeighbors(n,e,s,w);
        Node[] neighborCheck = test.getNeighbors();
        for(Node element:neighborCheck)
        {
            if(element == null)
            {
                return "Not all neighbors were set";
            }
        }

        return "Neighbors were sucessfully set!";
    }

    public String settingNodeNeighborsConstructorTest()
    {
        Node test = new Node();
        Node n = new Node();
        Node e = new Node();
        Node s = new Node();
        Node w = new Node();
        test.setNeighbors(n,e,s,w);
        Node[] neighborCheck = test.getNeighbors();
        Node testNextNode = new Node(neighborCheck,test);
        Node[] secondNeighborCheck = testNextNode.getNeighbors();
        for(Node element:secondNeighborCheck)
        {
            if(element == null)
            {
                return "Not all neighbors were set";
            }
        }

        return "Neighbors were sucessfully set!";
    }

    public String sortingNodesTest()
    {


        
        Node n = new Node();
        n.setTotalCost(5);
        Node e = new Node();
        e.setTotalCost(3);
        Node s = new Node();
        s.setTotalCost(1);
        Node w = new Node();
        w.setTotalCost(4);
        ArrayList<Node> sortCheck = new ArrayList<Node>();
        sortCheck.add(n);
        sortCheck.add(e);
        sortCheck.add(s);
        sortCheck.add(w);
        sortNodes(sortCheck);
        String result = "";
        for(int i = 0; i< sortCheck.size(); i++)
        {
            result += sortCheck.get(i).getTotalCost();
            result += " ";
        }
        System.out.println();
        System.out.println("Actual: \n" + result);
        System.out.println("Expected: \n1.0 3.0 4.0 5.0 ");
        System.out.println();
        if(result.equals("1.0 3.0 4.0 5.0 "))
        {
            return "Successful";
        }else
        {
            return "Failed";
        }
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
}

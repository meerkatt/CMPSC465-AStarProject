public class TestCases {
    public String creatingNode()
    {
        Node test = new Node();
        return "Node Successfully Created!";
    }

    public String settingNodeNeighbors()
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

    public String settingNodeNeighborsConstructor()
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
}

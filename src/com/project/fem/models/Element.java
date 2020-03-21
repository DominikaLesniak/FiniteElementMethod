package com.project.fem.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Element {
    Node[] id;
    private double[][] matrixH;
    private double[][] matrixC;
    private double[][] matrixHbc;
    private double[] vectorP;
    public Element(Node node1, Node node2, Node node3, Node node4) {
        id = new Node[]{node1, node2, node3, node4};
    }

    public Node getNode(int nr) {
        return id[nr];
    }

    public String toString() {
        String result = "Element id: \n";
        for (Node node :id) {
            result+= node.toString()+"\n";
        }
        return result;
    }

    public int[] getNodesId() {
        int[] nodesId = new int[id.length];
        for (int i = 0; i < id.length; i++) {
            nodesId[i] = id[i].getId();
        }
        return nodesId;
    }

    /* List<Node> id;
     public Element(Node node1, Node node2, Node node3, Node node4) {
         this.id = Arrays.asList(node1, node2, node3, node4);
     }*/
}

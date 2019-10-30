package com.project.fem.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Element {
    Node[] id;
    public Element(Node node1, Node node2, Node node3, Node node4) {
        id = new Node[]{node1, node2, node3, node4};
    }

    public String toString() {
        String result = "Element id: \n";
        for (Node node :id) {
            result+= node.toString()+"\n";
        }
        return result;
    }

    /* List<Node> id;
     public Element(Node node1, Node node2, Node node3, Node node4) {
         this.id = Arrays.asList(node1, node2, node3, node4);
     }*/
}

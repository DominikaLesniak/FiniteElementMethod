package com.project.fem.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FemGrid {
    private Node[] nodes;
    private Element[] elements;

    public FemGrid(int nW, int nH) {
        nodes = new Node[nW*nH];
        elements = new Element[(nW-1)*(nH-1)];
    }

    public void printElement(int elementIndex) {
        if (elementIndex > elements.length) {
            System.err.println("Given index out of boundary of elements["+elements.length+"]");
        } else {
            System.out.println(elements[elementIndex].toString());
        }
    }
}

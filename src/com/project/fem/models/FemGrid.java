package com.project.fem.models;

import lombok.Getter;
import lombok.Setter;

import static com.project.fem.dataFeatures.GlobalFunctions.printMatrix;
import static com.project.fem.dataFeatures.GlobalFunctions.printVector;

@Getter
@Setter
public class FemGrid {
    private Node[] nodes;
    private Element[] elements;
    private double[][] globalHMatrix;
    private double[][] globalCMatrix;
    private double[] globalPVector;

    public FemGrid(int nW, int nH) {
        nodes = new Node[nW*nH];
        elements = new Element[(nW-1)*(nH-1)];
    }

    public Element getElement(int index) {
        return elements[index];
    }

    public void printElement(int elementIndex) {
        if (elementIndex > elements.length) {
            System.err.println("Given index out of boundary of elements["+elements.length+"]");
        } else {
            System.out.println(elements[elementIndex].toString());
        }
    }

    public void printGlobalMatrixes() {
        System.out.println("Matrix [H] = [H] + [Hbc]");
        printMatrix(globalHMatrix);
        System.out.println("\nMatrix [C]");
        printMatrix(globalCMatrix);
        System.out.println("\nVector [P]");
        printVector(globalPVector);
    }
}

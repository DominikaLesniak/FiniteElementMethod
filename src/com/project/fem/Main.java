package com.project.fem;

import com.project.fem.dataFeatures.*;
import com.project.fem.models.Element;
import com.project.fem.models.FemGrid;
import com.project.fem.models.GlobalData;
import com.project.fem.models.GlobalFunctions;

public class Main {

    public static void main(String[] args) {
        GlobalData globalData = new GlobalData();
        FemGridGenerator femGridGenerator = new FemGridGenerator();
        HMatrixGenerator hMatrixGenerator = new HMatrixGenerator();
        CMatrixGenerator cMatrixGenerator = new CMatrixGenerator();
        HbcGenerator hbcGenerator = new HbcGenerator();
        Aggregate aggregate = new Aggregate();

        globalData.getDataFromFile();
        FemGrid femGrid = femGridGenerator.generateFemGrid(globalData);
        //femGrid.printElement(11);

        for (int i = 0; i < femGrid.getElements().length; i++) {
            Element element = femGrid.getElement(i);
            hMatrixGenerator.countH(element, globalData);
            cMatrixGenerator.countC(element, globalData);
            hbcGenerator.generateHbc(element, globalData);
        }
        aggregate.aggregateMatrixes(femGrid);

        System.out.println("Matrix H:");
        GlobalFunctions.printMatrix(femGrid.getGlobalHMatrix());

        System.out.println("Matrix C:");
        GlobalFunctions.printMatrix(femGrid.getGlobalCMatrix());


/*
        Node node1 = new Node(0, 0, true);
        Node node2 = new Node(0.025, 0, true);
        Node node3 = new Node(0.025, 0.025, false);
        Node node4 = new Node(0, 0.025, false);
        Element element1 = new Element(node1, node2, node3, node4);

        double[][] H = hMatrixGenerator.countH(element1, globalData);
        System.out.println("Matrix H:");
        GlobalFunctions.printMatrix(H);

        double[][] C = cMatrixGenerator.countC(element1, globalData);
        System.out.println("Matrix C:");
        GlobalFunctions.printMatrix(C);

        Node node5 = new Node(0, 0, true);
        Node node6 = new Node(0.25, 0, true);
        Node node7 = new Node(0.25, 0.25, false);
        Node node8 = new Node(0, 0.25, false);
        Element element2 = new Element(node5, node6, node7, node8);
        double[][] hbc = hbcGenerator.generateHbc(element1, globalData);
        System.out.println("Matrix Hbc:");
        GlobalFunctions.printMatrix(hbc);
        */
    }
}

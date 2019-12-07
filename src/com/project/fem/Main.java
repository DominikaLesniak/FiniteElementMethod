package com.project.fem;

import com.project.fem.dataFeatures.CMatrixGenerator;
import com.project.fem.dataFeatures.FemGridGenerator;
import com.project.fem.dataFeatures.HMatrixGenerator;
import com.project.fem.models.*;

public class Main {

    public static void main(String[] args) {
        GlobalData globalData = new GlobalData();
        globalData.getDataFromFile();

        FemGridGenerator femGridGenerator = new FemGridGenerator();
        FemGrid femGrid = femGridGenerator.generateFemGrid(globalData);
        //femGrid.printElement(11);

        HMatrixGenerator hMatrixGenerator = new HMatrixGenerator();
        CMatrixGenerator cMatrixGenerator = new CMatrixGenerator();

        Node node1 = new Node(0, 0, true);
        Node node2 = new Node(0.025, 0, true);
        Node node3 = new Node(0.025, 0.025, false);
        Node node4 = new Node(0, 0.025, false);
        Element element = new Element(node1, node2, node3, node4);

        double[][] H = hMatrixGenerator.countH(element, 30);
        System.out.println("Matrix H:");
        GlobalFunctions.printMatrix(H);

        double[][] C = cMatrixGenerator.countC(element, 700, 7800);
        System.out.println("Matrix C:");
        GlobalFunctions.printMatrix(C);
    }
}

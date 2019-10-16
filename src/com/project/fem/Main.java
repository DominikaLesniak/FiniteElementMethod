package com.project.fem;

import com.project.fem.dataFeatures.FemGridGenerator;
import com.project.fem.models.FemGrid;

public class Main {

    public static void main(String[] args) {
        FemGridGenerator femGridGenerator = new FemGridGenerator();
        FemGrid femGrid = femGridGenerator.generateFemGrid();
        femGrid.printElement(11);
    }
}

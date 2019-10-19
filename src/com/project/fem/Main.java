package com.project.fem;

import com.project.fem.dataFeatures.FemGridGenerator;
import com.project.fem.models.FemGrid;
import com.project.fem.models.GlobalData;

public class Main {

    public static void main(String[] args) {
        //new GlobalData().readFileData();
        FemGridGenerator femGridGenerator = new FemGridGenerator();
        FemGrid femGrid = femGridGenerator.generateFemGrid();
        femGrid.printElement(11);
    }
}

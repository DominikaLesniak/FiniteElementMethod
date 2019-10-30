package com.project.fem;

import com.project.fem.dataFeatures.FemGridGenerator;
import com.project.fem.dataFeatures.GaussInterpolation;
import com.project.fem.models.FemGrid;
import com.project.fem.models.GlobalData;

public class Main {

    public static void main(String[] args) {
        GlobalData globalData = new GlobalData();
        globalData.getDataFromFile();

        FemGridGenerator femGridGenerator = new FemGridGenerator();
        FemGrid femGrid = femGridGenerator.generateFemGrid(globalData);
        //femGrid.printElement(11);

        GaussInterpolation gaussInterpolation = new GaussInterpolation();
        double[][] ksiDerivatives = gaussInterpolation.countKsiDerivatives(globalData.getN());
        double[][] niDerivatives = gaussInterpolation.countNiDerivatives(globalData.getN());
        double[][] shapeFunctionValues = gaussInterpolation.countShapeFunctionValues(globalData.getN());
    }
}

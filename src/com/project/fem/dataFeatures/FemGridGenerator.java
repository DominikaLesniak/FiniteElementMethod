package com.project.fem.dataFeatures;

import com.project.fem.models.Element;
import com.project.fem.models.FemGrid;
import com.project.fem.models.GlobalData;
import com.project.fem.models.Node;

public class FemGridGenerator {
    private GlobalData globalData;

    public FemGridGenerator(GlobalData globalData) {
        this.globalData = globalData;
    }

    public FemGrid generateFemGrid() {
        FemGrid femGrid = new FemGrid(globalData.getNW(), globalData.getNH());

        double deltaX = globalData.getWidth() / (globalData.getNW()-1);
        double deltaY = globalData.getHeight() / (globalData.getNH()-1);
        int index = 0;
        for (int i = 0; i < globalData.getNW(); i++) {
            for (int j = 0; j < globalData.getNH(); j++) {
                double x = deltaX * i;
                double y = deltaY * j;
                boolean BC;
                BC = (x == 0 || y == 0 || x == globalData.getWidth() || y == globalData.getHeight());
                femGrid.getNodes()[index] = new Node(x, y, BC, index, globalData.getInitialTemperature());
                index++;
            }
        }
        index = -1;
        int iterator = -1;
        for (int j = 0; j < globalData.getNW()-1; j++) {
            for (int i = 0; i < globalData.getNH()-1; i++) {
                iterator++;
                if(i == 0 && j>0){
                   iterator++;
                }
                index++;
                femGrid.getElements()[index] =
                        new Element(femGrid.getNodes()[iterator],
                                femGrid.getNodes()[iterator + globalData.getNH()],
                                femGrid.getNodes()[iterator + 1 + globalData.getNH()],
                                femGrid.getNodes()[iterator + 1]);
            }
        }
        return femGrid;
    }
}

package com.project.fem.dataFeatures;

import com.project.fem.models.Element;
import com.project.fem.models.FemGrid;

import java.util.Arrays;

import static com.project.fem.dataFeatures.GlobalFunctions.initializeMatrix;

public class Aggregate {
    private static int N = 4;

    public void aggregateMatrixes(FemGrid femGrid) {

        double[][] globalHMatrix = initializeMatrix(femGrid.getNodes().length);
        double[][] globalCMatrix = initializeMatrix(femGrid.getNodes().length);
        double[] globalPVector = new double[femGrid.getNodes().length];
        Arrays.fill(globalPVector, 0);

        for (Element element : femGrid.getElements()) {
            int[] id = element.getNodesId();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    globalHMatrix[id[i]][id[j]] += element.getMatrixH()[i][j] + element.getMatrixHbc()[i][j];
                    globalCMatrix[id[i]][id[j]] += element.getMatrixC()[i][j];
                }
                globalPVector[id[i]] += element.getVectorP()[i];
            }
        }
        femGrid.setGlobalHMatrix(globalHMatrix);
        femGrid.setGlobalCMatrix(globalCMatrix);
        femGrid.setGlobalPVector(globalPVector);


    }
}

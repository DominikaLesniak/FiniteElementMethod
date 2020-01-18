package com.project.fem.FEMCalculations;

import com.project.fem.models.FemGrid;
import com.project.fem.models.GlobalData;
import com.project.fem.models.Node;
import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.inversion.GaussJordanInverter;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class MatrixOperations {
    private GlobalData globalData;

    public MatrixOperations(GlobalData globalData) {
        this.globalData = globalData;
    }

    public void solveEquation(FemGrid femGrid) {
        Matrix finalMatrixH = calculateFinalMatrixH(femGrid);
        solveEquation(femGrid, finalMatrixH);
    }

    public void solveEquation(FemGrid femGrid, Matrix finalMatrixH) {
        Vector finalVectorP = calculateFinalVectorP(femGrid);

        Matrix inverseMatrixH = new GaussJordanInverter(finalMatrixH).inverse();
        Vector newTemperatures = inverseMatrixH.multiply(finalVectorP);

        for (int i = 0; i < femGrid.getNodes().length; i++) {
            femGrid.getNodes()[i].setTemperature(newTemperatures.get(i));
        }
        System.out.printf("Step min temp: %6f, max temp: %6f \n", newTemperatures.min(), newTemperatures.max());
    }

    public Matrix calculateFinalMatrixH(FemGrid femGrid) {
        Matrix matrixC = Matrix.from2DArray(femGrid.getGlobalCMatrix()).divide(globalData.getSimulationStepTime());
        return Matrix.from2DArray(femGrid.getGlobalHMatrix()).add(matrixC);
    }

    private Vector calculateFinalVectorP(FemGrid femGrid) {
        Matrix matrixC = Matrix.from2DArray(femGrid.getGlobalCMatrix()).divide(globalData.getSimulationStepTime());

        List<Double> nodesTemperatures = stream(femGrid.getNodes())
                .map(Node::getTemperature)
                .collect(toList());

        Vector vectorTo = Vector.fromCollection(nodesTemperatures);
        Vector vectorP = Vector.fromArray(femGrid.getGlobalPVector());

        return matrixC.multiply(vectorTo).subtract(vectorP);
    }
}

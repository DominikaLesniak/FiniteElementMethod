package com.project.fem.FEMCalculations;

import com.project.fem.models.FemGrid;
import com.project.fem.models.GlobalData;
import com.project.fem.models.Node;
import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.inversion.GaussJordanInverter;

import java.text.DecimalFormat;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class MatrixOperations {
    private GlobalData globalData;

    public MatrixOperations(GlobalData globalData) {
        this.globalData = globalData;
    }

    public void solveEquation(FemGrid femGrid) {
        Matrix finalMatrixH = calculateInversedFinalMatrixH(femGrid);
        solveEquation(femGrid, finalMatrixH);
    }

    public void solveEquation(FemGrid femGrid, Matrix inverseMatrixH) {
        Vector finalVectorP = calculateFinalVectorP(femGrid);

        //Matrix inverseMatrixH = new GaussJordanInverter(finalMatrixH).inverse();
        Vector newTemperatures = inverseMatrixH.multiply(finalVectorP);

        for (int i = 0; i < femGrid.getNodes().length; i++) {
            femGrid.getNodes()[i].setTemperature(newTemperatures.get(i));
        }
        DecimalFormat df = new DecimalFormat("#.###");
        double minTemp = newTemperatures.min();
        double maxTemp = newTemperatures.max();
        System.out.printf("min temp: %6s°C; max temp: %6s°C \n", df.format(minTemp), df.format(maxTemp));
    }

    public Matrix calculateInversedFinalMatrixH(FemGrid femGrid) {
        Matrix matrixC = Matrix.from2DArray(femGrid.getGlobalCMatrix()).divide(globalData.getSimulationStepTime());
        Matrix finalMatrixH = Matrix.from2DArray(femGrid.getGlobalHMatrix()).add(matrixC);
        Matrix inverseMatrixH = new GaussJordanInverter(finalMatrixH).inverse();
        return inverseMatrixH;
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

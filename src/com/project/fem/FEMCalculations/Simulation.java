package com.project.fem.FEMCalculations;

import com.project.fem.dataFeatures.*;
import com.project.fem.models.Element;
import com.project.fem.models.FemGrid;
import com.project.fem.models.GlobalData;
import com.project.fem.models.supportModels.DataSource;
import org.la4j.Matrix;

public class Simulation {
    private GlobalData globalData;
    private FemGridGenerator femGridGenerator;
    private HMatrixGenerator hMatrixGenerator;
    private CMatrixGenerator cMatrixGenerator;
    private HbcGenerator hbcGenerator;
    private Aggregate aggregate;
    private MatrixOperations matrixOperations;
    private FemGrid femGrid;

    public Simulation(DataSource dataSource) {
        globalData = new GlobalData();
        globalData.getDataFromFile(dataSource.getFilePath());
        femGridGenerator = new FemGridGenerator(globalData);
        hMatrixGenerator = new HMatrixGenerator(globalData);
        cMatrixGenerator = new CMatrixGenerator(globalData);
        hbcGenerator = new HbcGenerator(globalData);
        aggregate = new Aggregate();
        matrixOperations = new MatrixOperations(globalData);
        femGrid = femGridGenerator.generateFemGrid();

    }

    public void runSimulation() {
        double simulationTime = globalData.getSimulationTime();
        double simulationStepTime = globalData.getSimulationStepTime();
        double currentTime = 0.0;

        calculateElementMatrixes();
        aggregate.aggregateMatrixes(femGrid);
        //femGrid.printGlobalMatrixes();
        Matrix inversedFinalMatrixH = matrixOperations.calculateInversedFinalMatrixH(femGrid);

        int i = 1;
        while (currentTime < simulationTime) {
            currentTime += simulationStepTime;
            System.out.printf("Step %2d: elapsed time = %5ss; ", i, currentTime);
            matrixOperations.solveEquation(femGrid, inversedFinalMatrixH);
            i++;
        }
    }

    private void calculateElementMatrixes() {
        for (Element element : femGrid.getElements()) {
            hMatrixGenerator.countH(element);
            cMatrixGenerator.countC(element);
            hbcGenerator.generateHbc(element);
        }
    }
}

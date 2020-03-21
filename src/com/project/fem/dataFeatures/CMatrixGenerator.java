package com.project.fem.dataFeatures;

import com.project.fem.models.Element;
import com.project.fem.models.GlobalData;

import static com.project.fem.dataFeatures.GlobalFunctions.VxV;
import static com.project.fem.dataFeatures.GlobalFunctions.initializeMatrix;

public class CMatrixGenerator {
    private final int N = 4;
    private GaussInterpolation gaussInterpolation;
    private GlobalData globalData;

    public CMatrixGenerator(GlobalData globalData) {
        gaussInterpolation = new GaussInterpolation();
        this.globalData = globalData;
    }

    public double[][] countC(Element element) {
        double[][] valuesN = gaussInterpolation.getShapeFunctionsValues();
        double[][] C = initializeMatrix(N, N);

        for (int i = 0; i < N; i++) {
            double[][] jacobian = gaussInterpolation.countJacobian(i, element);
            double detJ = gaussInterpolation.countDetJ(jacobian);
            double[][] matrixNi = VxV(valuesN[i]);

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    C[j][k] += (matrixNi[j][k] * globalData.getSpecificHeat() * globalData.getDensity() * detJ);
                }
            }
        }
        element.setMatrixC(C);
        return C;
    }
}

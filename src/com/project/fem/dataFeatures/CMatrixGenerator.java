package com.project.fem.dataFeatures;

import com.project.fem.models.Element;

import static com.project.fem.models.GlobalFunctions.VxV;
import static com.project.fem.models.GlobalFunctions.initializeMatrix;

public class CMatrixGenerator {
    private final int N = 4;
    private GaussInterpolation gaussInterpolation;

    public CMatrixGenerator() {
        gaussInterpolation = new GaussInterpolation();
    }

    public double[][] countC(Element element, double c, double density) {
        double[][] valuesN = gaussInterpolation.countShapeFunctionValues();
        double[][] C = initializeMatrix(N, N);

        for (int i = 0; i < N; i++) {
            double[][] jacobian = gaussInterpolation.countJacobian(i, element);
            double detJ = gaussInterpolation.countDetJ(jacobian);
            double[][] matrixNi = VxV(valuesN[i]);

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    C[j][k] += (matrixNi[j][k] * c * density * detJ);
                }
            }
        }
        return C;
    }
}

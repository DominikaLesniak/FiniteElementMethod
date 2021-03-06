package com.project.fem.dataFeatures;

import com.project.fem.models.Element;
import com.project.fem.models.GlobalData;

import static com.project.fem.dataFeatures.GlobalFunctions.VxV;

public class HMatrixGenerator {
    private final int N = 4;
    private GaussInterpolation gaussInterpolation;
    private GlobalData globalData;

    public HMatrixGenerator(GlobalData globalData) {
        gaussInterpolation = new GaussInterpolation();
        this.globalData = globalData;
    }

    public double[][] countH(Element element) {
        double[][] ksiDerivatives = gaussInterpolation.getKsiDerivativesValues();
        double[][] etaDerivatives = gaussInterpolation.getEtaDerivativesValues();
        double[][] H = GlobalFunctions.initializeMatrix(N, N);

        for (int i = 0; i < N; i++) {
            double[][] jacobian = gaussInterpolation.countJacobian(i, element);
            double[][] reverseJacobian = gaussInterpolation.countReverseJacobian(i, element);
            double detJ = gaussInterpolation.countDetJ(jacobian);

            double[][] dNdX_dNdY = GlobalFunctions.initializeMatrix(N, 2);
            for (int k = 0; k < N; k++) {
                for (int j = 0; j < 2; j++) {
                    dNdX_dNdY[j][k] = ((reverseJacobian[j][0] * ksiDerivatives[i][k]) - (reverseJacobian[j][1] * etaDerivatives[i][k])) / detJ;
                }
            }
            double[][] xDerivMatrix = VxV(dNdX_dNdY[0]);
            double[][] yDerivMatrix = VxV(dNdX_dNdY[1]);

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    H[j][k] += ((xDerivMatrix[j][k] + yDerivMatrix[j][k]) * globalData.getConductivity() * detJ);
                }
            }
        }
        element.setMatrixH(H);
        return H;
    }
}

package com.project.fem.dataFeatures;

import com.project.fem.models.Element;
import com.project.fem.models.GlobalData;
import com.project.fem.models.Node;
import com.project.fem.models.supportModels.ElementSurfacePoints;
import com.project.fem.models.supportModels.GaussInterpolationNode;

import static com.project.fem.dataFeatures.GlobalFunctions.VxV;

public class HbcGenerator {
    private final static int N = 4;
    private GaussInterpolation gaussInterpolation;
    private GlobalData globalData;

    public HbcGenerator(GlobalData globalData) {
        gaussInterpolation = new GaussInterpolation();
        this.globalData = globalData;
    }

    public double[][] generateHbc(Element element) {
        ElementSurfacePoints surfacePoints = new ElementSurfacePoints();
        double[][] hbc = GlobalFunctions.initializeMatrix(4, 4);
        double[] P = {0, 0, 0, 0};

        for (int i = 0; i < N; i++) {
            Node node1 = element.getNode(i);
            Node node2 = element.getNode((i + 1) % N);

            GaussInterpolationNode[] wallNodes = surfacePoints.getWall(i);
            double[] valuesForNode1 = gaussInterpolation.countShapeFunctionValuesForNode(wallNodes[0]);
            double[] valuesForNode2 = gaussInterpolation.countShapeFunctionValuesForNode(wallNodes[1]);
            double l = Math.sqrt(Math.pow(node1.getX() - node2.getX(), 2) + Math.pow(node1.getY() - node2.getY(), 2));
            double detJ = l / 2;

            if (node1.isBC() && node2.isBC()) {
                double[][] hbc1 = VxV(valuesForNode1);
                double[][] hbc2 = VxV(valuesForNode2);

                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        hbc[j][k] += (hbc1[j][k] + hbc2[j][k]) * globalData.getAlpha() * detJ;
                    }
                }
                for (int j = 0; j < N; j++) {
                    P[j] -= (valuesForNode1[j] + valuesForNode2[j]) * globalData.getAlpha() * globalData.getAmbientTemperature() * detJ;
                }
            }
        }
        element.setMatrixHbc(hbc);
        element.setVectorP(P);
        return hbc;
    }
}

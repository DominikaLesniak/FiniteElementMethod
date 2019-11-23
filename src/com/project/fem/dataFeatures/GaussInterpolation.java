package com.project.fem.dataFeatures;

import com.project.fem.models.GaussInterpolationNode;
import com.project.fem.models.GlobalConstants;

import java.util.List;

public class GaussInterpolation {
    private final int N = 4;
    private final int nodesNumber = 2;

    public double[][] countKsiDerivatives() {
        List<GaussInterpolationNode> interpolationNodes = GlobalConstants.getInterpolationNodes(nodesNumber);
        double[][] ksiDerivatives = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                ksiDerivatives[i][j] = GlobalConstants.shapeFunctionKsiDerivative(j + 1, interpolationNodes.get(i).getNi());
            }
        }
        return ksiDerivatives;
    }

    public double[][] countNiDerivatives() {
        List<GaussInterpolationNode> interpolationNodes = GlobalConstants.getInterpolationNodes(nodesNumber);
        double[][] niDerivatives = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                niDerivatives[i][j] = GlobalConstants.shapeFunctionNiDerivative(j + 1, interpolationNodes.get(i).getKsi());
            }
        }
        return niDerivatives;
    }

    public double[][] countShapeFunctionValues() {
        List<GaussInterpolationNode> interpolationNodes = GlobalConstants.getInterpolationNodes(nodesNumber);
        double[][] values = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                values[i][j] = GlobalConstants.shapeFunction(j + 1, interpolationNodes.get(i).getKsi(), interpolationNodes.get(i).getNi());
            }
        }
        return values;
    }
}

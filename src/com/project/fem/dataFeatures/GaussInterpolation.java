package com.project.fem.dataFeatures;

import com.project.fem.models.GaussInterpolationNode;
import com.project.fem.models.GlobalConstants;

import java.util.List;

public class GaussInterpolation {
    private final int N = 4;

    public double[][] countKsiDerivatives(int nodesNumber) {
        List<GaussInterpolationNode> interpolationNodes = GlobalConstants.getInterpolationNodes(nodesNumber);
        double[][] ksiDerivatives = new double[N][N];
        for (int i = 0; i < N; i++) {
            int index = i < 2 ? 0 : 1;
            ksiDerivatives[i][0] = GlobalConstants.shapeFunctionKsiDerivative1(interpolationNodes.get(index).getPc());
            ksiDerivatives[i][1] = GlobalConstants.shapeFunctionKsiDerivative2(interpolationNodes.get(index).getPc());
            ksiDerivatives[i][2] = GlobalConstants.shapeFunctionKsiDerivative3(interpolationNodes.get(index).getPc());
            ksiDerivatives[i][3] = GlobalConstants.shapeFunctionKsiDerivative4(interpolationNodes.get(index).getPc());
        }
        return ksiDerivatives;
    }

    public double[][] countNiDerivatives(int nodesNumber) {
        List<GaussInterpolationNode> interpolationNodes = GlobalConstants.getInterpolationNodes(nodesNumber);
        double[][] niDerivatives = new double[N][N];
        for (int i = 0; i < N; i++) {
            int index = i == 0 || i == 3 ? 0 : 1;
            niDerivatives[i][0] = GlobalConstants.shapeFunctionNiDerivative1(interpolationNodes.get(index).getPc());
            niDerivatives[i][1] = GlobalConstants.shapeFunctionNiDerivative2(interpolationNodes.get(index).getPc());
            niDerivatives[i][2] = GlobalConstants.shapeFunctionNiDerivative3(interpolationNodes.get(index).getPc());
            niDerivatives[i][3] = GlobalConstants.shapeFunctionNiDerivative4(interpolationNodes.get(index).getPc());
        }
        return niDerivatives;
    }

    public double[][] countShapeFunctionValues(int nodesNumber) {
        List<GaussInterpolationNode> interpolationNodes = GlobalConstants.getInterpolationNodes(nodesNumber);
        double[][] values = new double[N][N];
        for (int i = 0; i < N; i++) {
            int indexKsi = i == 0 || i == 3 ? 0 : 1;
            int indexNi = i < 2 ? 0 : 1;
            values[i][0] = GlobalConstants.shapeFunction1(interpolationNodes.get(indexKsi).getPc(), interpolationNodes.get(indexNi).getPc());
            values[i][1] = GlobalConstants.shapeFunction2(interpolationNodes.get(indexKsi).getPc(), interpolationNodes.get(indexNi).getPc());
            values[i][2] = GlobalConstants.shapeFunction3(interpolationNodes.get(indexKsi).getPc(), interpolationNodes.get(indexNi).getPc());
            values[i][3] = GlobalConstants.shapeFunction4(interpolationNodes.get(indexKsi).getPc(), interpolationNodes.get(indexNi).getPc());
        }
        return values;
    }
}

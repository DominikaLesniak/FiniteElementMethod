package com.project.fem.dataFeatures;

import com.project.fem.models.Element;
import com.project.fem.models.GaussInterpolationNode;
import com.project.fem.models.GlobalFunctions;
import com.project.fem.models.Node;

import java.util.List;

import static com.project.fem.models.GlobalFunctions.initializeMatrix;

public class GaussInterpolation {
    private final int N = 4;
    private final int nodesNumber = 2;

    public double[][] countJacobian(int nr, Element element) {
        double[][] countEtaDerivatives = countEtaDerivatives();
        double[][] ksiDerivatives = countKsiDerivatives();
        double[][] jacobian = initializeMatrix(2, 2);

        for (int i = 0; i < N; i++) {
            Node node = element.getNode(i);
            jacobian[0][0] += node.getX() * ksiDerivatives[nr][i];
            jacobian[0][1] += node.getY() * ksiDerivatives[nr][i];
            jacobian[1][0] += node.getX() * countEtaDerivatives[nr][i];
            jacobian[1][1] += node.getY() * countEtaDerivatives[nr][i];
        }
        return jacobian;
    }

    public double[][] countReverseJacobian(int nr, Element element) {
        double[][] etaDerivatives = countEtaDerivatives();
        double[][] ksiDerivatives = countKsiDerivatives();
        double[][] reverseJacobian = initializeMatrix(2, 2);

        for (int i = 0; i < N; i++) {
            Node node = element.getNode(i);
            reverseJacobian[0][0] += node.getY() * etaDerivatives[nr][i];
            reverseJacobian[0][1] -= node.getY() * ksiDerivatives[nr][i];
            reverseJacobian[1][0] -= node.getX() * etaDerivatives[nr][i];
            reverseJacobian[1][1] += node.getX() * ksiDerivatives[nr][i];
        }
        return reverseJacobian;
    }

    public double countDetJ(double[][] jacobian) {
        return jacobian[0][0] * jacobian[1][1] - jacobian[1][0] * jacobian[0][1];
    }

    public double[][] countKsiDerivatives() {
        List<GaussInterpolationNode> interpolationNodes = GlobalFunctions.getInterpolationNodes(nodesNumber);
        double[][] ksiDerivatives = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ksiDerivatives[i][j] = GlobalFunctions.shapeFunctionKsiDerivative(j + 1, interpolationNodes.get(i).getEta());
            }
        }
        return ksiDerivatives;
    }

    public double[][] countEtaDerivatives() {
        List<GaussInterpolationNode> interpolationNodes = GlobalFunctions.getInterpolationNodes(nodesNumber);
        double[][] etaDerivatives = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                etaDerivatives[i][j] = GlobalFunctions.shapeFunctionEtaDerivative(j + 1, interpolationNodes.get(i).getKsi());
            }
        }
        return etaDerivatives;
    }

    public double[][] countShapeFunctionValues() {
        List<GaussInterpolationNode> interpolationNodes = GlobalFunctions.getInterpolationNodes(nodesNumber);
        double[][] values = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                values[i][j] = GlobalFunctions.shapeFunction(j + 1, interpolationNodes.get(i).getKsi(), interpolationNodes.get(i).getEta());
            }
        }
        return values;
    }

    public double[] countShapeFunctionValuesForNode(GaussInterpolationNode node) {
        double[] values = new double[4];
        for (int i = 0; i < 4; i++) {
            values[i] = GlobalFunctions.shapeFunction(i + 1, node.getKsi(), node.getEta());
        }
        return values;
    }
}

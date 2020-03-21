package com.project.fem.dataFeatures;

import com.project.fem.models.Element;
import com.project.fem.models.Node;
import com.project.fem.models.supportModels.GaussInterpolationNode;
import lombok.Getter;

import java.util.List;

import static com.project.fem.dataFeatures.GlobalFunctions.initializeMatrix;

@Getter
public class GaussInterpolation {
    private final int N = 4;
    private final int nodesNumber = 2;
    double[][] ksiDerivativesValues;
    double[][] etaDerivativesValues;
    double[][] shapeFunctionsValues;

    public GaussInterpolation() {
        ksiDerivativesValues = countKsiDerivatives();
        etaDerivativesValues = countEtaDerivatives();
        shapeFunctionsValues = countShapeFunctionValues();
    }

    public double[][] countJacobian(int nr, Element element) {
        double[][] jacobian = initializeMatrix(2, 2);

        for (int i = 0; i < N; i++) {
            Node node = element.getNode(i);
            jacobian[0][0] += node.getX() * ksiDerivativesValues[nr][i];
            jacobian[0][1] += node.getY() * ksiDerivativesValues[nr][i];
            jacobian[1][0] += node.getX() * etaDerivativesValues[nr][i];
            jacobian[1][1] += node.getY() * etaDerivativesValues[nr][i];
        }
        return jacobian;
    }

    public double[][] countReverseJacobian(int nr, Element element) {
        double[][] reverseJacobian = initializeMatrix(2, 2);

        for (int i = 0; i < N; i++) {
            Node node = element.getNode(i);
            reverseJacobian[0][0] += node.getY() * etaDerivativesValues[nr][i];
            reverseJacobian[0][1] -= node.getY() * ksiDerivativesValues[nr][i];
            reverseJacobian[1][0] -= node.getX() * etaDerivativesValues[nr][i];
            reverseJacobian[1][1] += node.getX() * ksiDerivativesValues[nr][i];
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

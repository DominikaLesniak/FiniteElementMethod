package com.project.fem.models;

import lombok.Getter;

import java.util.List;

import static java.util.Arrays.asList;

@Getter
public class GlobalConstants {
    private static final double N2_WEIGHT1 = 1.0;
    private static final double N2_NODE_VALUE1 = 0.57735;

    private static final double N3_WEIGHT1 = 5.0 / 9.0;
    private static final double N3_WEIGHT2 = 8.0 / 9.0;
    private static final double N3_NODE_VALUE1 = 0.77459;
    private static final double N3_NODE_VALUE2 = 0.0;

    public static double shapeFunction1(double ksi, double ni) {
        return (1 - ksi) * (1 - ni) / 4;
    }

    public static double shapeFunction2(double ksi, double ni) {
        return (1 + ksi) * (1 - ni) / 4;
    }

    public static double shapeFunction3(double ksi, double ni) {
        return (1 + ksi) * (1 + ni) / 4;
    }

    public static double shapeFunction4(double ksi, double ni) {
        return (1 - ksi) * (1 + ni) / 4;
    }

    public static double shapeFunctionKsiDerivative1(double ni) {
        return -(1 - ni) / 4;
    }

    public static double shapeFunctionKsiDerivative2(double ni) {
        return (1 - ni) / 4;
    }

    public static double shapeFunctionKsiDerivative3(double ni) {
        return (1 + ni) / 4;
    }

    public static double shapeFunctionKsiDerivative4(double ni) {
        return -(1 + ni) / 4;
    }

    public static double shapeFunctionNiDerivative1(double ksi) {
        return -(1 - ksi) / 4;
    }

    public static double shapeFunctionNiDerivative2(double ksi) {
        return -(1 + ksi) / 4;
    }

    public static double shapeFunctionNiDerivative3(double ksi) {
        return (1 + ksi) / 4;
    }

    public static double shapeFunctionNiDerivative4(double ksi) {
        return (1 - ksi) / 4;
    }

    public static List<GaussInterpolationNode> getInterpolationNodes(int nodesNumber) {
        switch (nodesNumber) {
            case 2:
                return asList(GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT1)
                                .pc(-N2_NODE_VALUE1)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT1)
                                .pc(N2_NODE_VALUE1)
                                .build());
            case 3:
                return asList(GaussInterpolationNode.builder()
                                .wpc(N3_WEIGHT1)
                                .pc(-N3_NODE_VALUE1)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N3_WEIGHT2)
                                .pc(N3_NODE_VALUE2)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N3_WEIGHT1)
                                .pc(N3_NODE_VALUE1)
                                .build());
            default:
                System.err.println(String.format("Interpolation nodes for n=%d are not available", nodesNumber));
                return null;
        }
    }

}

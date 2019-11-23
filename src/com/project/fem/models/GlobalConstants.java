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

    public static double shapeFunction(int nr, double ksi, double ni) {
        switch (nr) {
            case 1:
                return (1 - ksi) * (1 - ni) / 4;
            case 2:
                return (1 + ksi) * (1 - ni) / 4;
            case 3:
                return (1 + ksi) * (1 + ni) / 4;
            case 4:
                return (1 - ksi) * (1 + ni) / 4;
            default:
                throw new RuntimeException("wrong equation number chosen: " + nr);
        }
    }

    public static double shapeFunctionKsiDerivative(int nr, double ni) {
        switch (nr) {
            case 1:
                return -(1 - ni) / 4;
            case 2:
                return (1 - ni) / 4;
            case 3:
                return (1 + ni) / 4;
            case 4:
                return -(1 + ni) / 4;
            default:
                throw new RuntimeException("wrong equation number chosen: " + nr);
        }
    }

    public static double shapeFunctionNiDerivative(int nr, double ksi) {
        switch (nr) {
            case 1:
                return -(1 - ksi) / 4;
            case 2:
                return -(1 + ksi) / 4;
            case 3:
                return (1 + ksi) / 4;
            case 4:
                return (1 - ksi) / 4;
            default:
                throw new RuntimeException("wrong equation number chosen: " + nr);
        }
    }

    public static List<GaussInterpolationNode> getInterpolationNodes(int nodesNumber) {
        switch (nodesNumber) {
            case 2:
                return asList(GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT1)
                                .ksi(-N2_NODE_VALUE1)
                                .ni(-N2_NODE_VALUE1)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT1)
                                .ksi(N2_NODE_VALUE1)
                                .ni(-N2_NODE_VALUE1)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT1)
                                .ksi(N2_NODE_VALUE1)
                                .ni(N2_NODE_VALUE1)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT1)
                                .ksi(-N2_NODE_VALUE1)
                                .ni(N2_NODE_VALUE1)
                                .build());
            case 3:
                return asList(GaussInterpolationNode.builder()
                                .wpc(N3_WEIGHT1)
                                .ksi(-N3_NODE_VALUE1)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N3_WEIGHT2)
                                .ksi(N3_NODE_VALUE2)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N3_WEIGHT1)
                                .ksi(N3_NODE_VALUE1)
                                .build());
            default:
                System.err.println(String.format("Interpolation nodes for n=%d are not available", nodesNumber));
                return null;
        }
    }

}

package com.project.fem.models;

import lombok.Getter;

import java.util.List;

import static java.util.Arrays.asList;

@Getter
public class GlobalFunctions {
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

    public static double shapeFunctionEtaDerivative(int nr, double ksi) {
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
                                .eta(-N2_NODE_VALUE1)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT1)
                                .ksi(N2_NODE_VALUE1)
                                .eta(-N2_NODE_VALUE1)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT1)
                                .ksi(N2_NODE_VALUE1)
                                .eta(N2_NODE_VALUE1)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT1)
                                .ksi(-N2_NODE_VALUE1)
                                .eta(N2_NODE_VALUE1)
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

    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static double[][] initializeMatrix(int sizeX, int sizeY) {
        double[][] matrix = new double[sizeY][sizeX];
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                matrix[i][j] = 0.0;
            }
        }
        return matrix;
    }

    public static double[][] VxV(double[] vector) {
        double[][] matrix = GlobalFunctions.initializeMatrix(4, 4);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = (vector[i] * vector[j]);
            }
        }
        return matrix;
    }
}

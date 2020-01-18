package com.project.fem.models;

import lombok.Getter;

import java.text.DecimalFormat;
import java.util.List;

import static java.util.Arrays.asList;

@Getter
public class GlobalFunctions {
    static final double N2_WEIGHT = 1.0;
    static final double N2_NODE_VALUE = 0.57735;

    private static final double N3_WEIGHT1 = 5.0 / 9.0;
    private static final double N3_WEIGHT2 = 8.0 / 9.0;
    private static final double N3_NODE_VALUE1 = 0.77459;
    private static final double N3_NODE_VALUE2 = 0.0;

    public static double shapeFunction(int nr, double ksi, double eta) {
        switch (nr) {
            case 1:
                return (1 - ksi) * (1 - eta) / 4;
            case 2:
                return (1 + ksi) * (1 - eta) / 4;
            case 3:
                return (1 + ksi) * (1 + eta) / 4;
            case 4:
                return (1 - ksi) * (1 + eta) / 4;
            default:
                throw new RuntimeException("wrong equation number chosen: " + nr);
        }
    }

    public static double shapeFunctionKsiDerivative(int nr, double eta) {
        switch (nr) {
            case 1:
                return -(1 - eta) / 4;
            case 2:
                return (1 - eta) / 4;
            case 3:
                return (1 + eta) / 4;
            case 4:
                return -(1 + eta) / 4;
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
                                .wpc(N2_WEIGHT)
                                .ksi(-N2_NODE_VALUE)
                                .eta(-N2_NODE_VALUE)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT)
                                .ksi(N2_NODE_VALUE)
                                .eta(-N2_NODE_VALUE)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT)
                                .ksi(N2_NODE_VALUE)
                                .eta(N2_NODE_VALUE)
                                .build(),
                        GaussInterpolationNode.builder()
                                .wpc(N2_WEIGHT)
                                .ksi(-N2_NODE_VALUE)
                                .eta(N2_NODE_VALUE)
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
        DecimalFormat df = new DecimalFormat("#.#####");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                double singleResult = matrix[i][j];
                System.out.printf("%8s \t", df.format(singleResult));
            }
            System.out.println();
        }
    }

    public static double[][] initializeMatrix(int size) {
        return initializeMatrix(size, size);
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

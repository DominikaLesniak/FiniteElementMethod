package com.project.fem.models.supportModels;

import lombok.Getter;

@Getter
public class ElementSurfacePoints {
    private final double N2_NODE_VALUE = 0.57735;
    private GaussInterpolationNode[][] walls;

    public ElementSurfacePoints() {
        walls = new GaussInterpolationNode[4][2];
        walls[0] = new GaussInterpolationNode[]{new GaussInterpolationNode(-N2_NODE_VALUE, -1), new GaussInterpolationNode(N2_NODE_VALUE, -1)};
        walls[1] = new GaussInterpolationNode[]{new GaussInterpolationNode(1, -N2_NODE_VALUE), new GaussInterpolationNode(1, N2_NODE_VALUE)};
        walls[2] = new GaussInterpolationNode[]{new GaussInterpolationNode(N2_NODE_VALUE, 1), new GaussInterpolationNode(-N2_NODE_VALUE, 1)};
        walls[3] = new GaussInterpolationNode[]{new GaussInterpolationNode(-1, N2_NODE_VALUE), new GaussInterpolationNode(-1, -N2_NODE_VALUE)};
    }

    public GaussInterpolationNode[] getWall(int i) {
        return walls[i];
    }
}

package com.project.fem.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GaussInterpolationNode {
    private double ksi;
    private double ni;
    private double wpc;
}

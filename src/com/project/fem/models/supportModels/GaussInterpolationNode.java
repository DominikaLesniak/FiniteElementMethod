package com.project.fem.models.supportModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GaussInterpolationNode {
    private double ksi;
    private double eta;
    private double wpc;

    public GaussInterpolationNode(double ksi, double eta) {
        this.ksi = ksi;
        this.eta = eta;
        this.wpc = 1;
    }
}

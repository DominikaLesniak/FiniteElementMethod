package com.project.fem.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GaussInterpolationNode {
    private double pc;
    private double wpc;
}

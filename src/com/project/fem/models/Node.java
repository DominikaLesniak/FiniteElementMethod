package com.project.fem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Node {
    private double temperature;
    private double x;
    private double y;
    private boolean BC;

    public Node(double x, double y, boolean BC) {
        this.x = x;
        this.y = y;
        this.BC = BC;
    }

    public String toString () {
        return "Node: \n"+
                "\t x="+ x + "\n"+
                "\t y="+ y + "\n"+
                "\t t="+ temperature + "\n"+
                "\t BC="+ BC;
    }
}

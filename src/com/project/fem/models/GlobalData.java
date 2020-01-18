package com.project.fem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalData {
    private double height; // [m]
    private double width; // [m]
    private int nH;
    private int nW;
    private double conductivity; // [W/(m*°C)]
    private double specificHeat; // [J/(kg*°C)]
    private double density; // [kg/m^3]
    private double alpha; // [W/(m^2*K)]
    private double initialTemperature; // [°C]
    private double ambientTemperature; // [°C]
    private double simulationTime; // [s]
    private double simulationStepTime; // [s]


    public void getDataFromFile() {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\com\\project\\fem\\resources\\data.txt"));
            JSONObject jsonObject = (JSONObject) object;

            String h = (String) jsonObject.get("H");
            String w = (String) jsonObject.get("W");
            String nH = (String) jsonObject.get("nH");
            String nW = (String) jsonObject.get("nW");
            String K = (String) jsonObject.get("K");
            String c = (String) jsonObject.get("c");
            String ro = (String) jsonObject.get("ro");
            String alpha = (String) jsonObject.get("alpha");
            String initialTemperature = (String) jsonObject.get("initialTemperature");
            String ambientTemperature = (String) jsonObject.get("ambientTemperature");
            String simulationTime = (String) jsonObject.get("simulationTime");
            String simulationStepTime = (String) jsonObject.get("simulationStepTime");

            this.height = parseDouble(h);
            this.width = parseDouble(w);
            this.nH = parseInt(nH);
            this.nW = parseInt(nW);
            this.conductivity = parseDouble(K);
            this.specificHeat = parseDouble(c);
            this.density = parseDouble(ro);
            this.alpha = parseDouble(alpha);
            this.initialTemperature = parseDouble(initialTemperature);
            this.ambientTemperature = parseDouble(ambientTemperature);
            this.simulationTime = parseDouble(simulationTime);
            this.simulationStepTime = parseDouble(simulationStepTime);

        } catch (FileNotFoundException e) {
            System.err.println("WRONG FILE PATH");
            e.printStackTrace();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}

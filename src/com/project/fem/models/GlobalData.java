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
    private double height;
    private double width;
    private int nH;
    private int nW;
    private int n;

    public void getDataFromFile() {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\com\\project\\fem\\resources\\data.txt"));
            JSONObject jsonObject = (JSONObject) object;

            String h = (String) jsonObject.get("H");
            String w = (String) jsonObject.get("W");
            String nH = (String) jsonObject.get("nH");
            String nW = (String) jsonObject.get("nW");
            String n = (String) jsonObject.get("n");

            this.height = parseDouble(h);
            this.width = parseDouble(w);
            this.nH = parseInt(nH);
            this.nW = parseInt(nW);
            this.n = parseInt(n);

        } catch (FileNotFoundException e) {
            System.err.println("WRONG FILE PATH");
            e.printStackTrace();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}

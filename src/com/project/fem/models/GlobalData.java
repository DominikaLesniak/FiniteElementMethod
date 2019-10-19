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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalData {
    private double height;
    private double width;
    private int nH;
    private int nW;

    public void getDataFromFile() {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\com\\project\\fem\\resources\\data.txt"));
            JSONObject jsonObject = (JSONObject) object;

            String h = (String) jsonObject.get("H");
            String w = (String) jsonObject.get("W");
            String nH = (String) jsonObject.get("nH");
            String nW = (String) jsonObject.get("nW");

            this.height = Double.parseDouble(h);
            this.width = Double.parseDouble(w);
            this.nH = Integer.parseInt(nH);
            this.nW = Integer.parseInt(nW);

        } catch (FileNotFoundException e) {
            System.err.println("WRONG FILE PATH");
            e.printStackTrace();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}

package com.vuw.project1.riverwatch.colour_algorithm;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

public class ColorRecognition {
    private HashMap<Double, Integer> concentrations = new HashMap<Double, Integer>();

    public ColorRecognition(HashMap<Double, Integer> concentrations){
        this.concentrations = concentrations;
    }

    public Map<Double, Double> processImage(Bitmap img) {
        HSBImage hsbSubImage = new HSBImage(img);
        HSBColor median = hsbSubImage.medianColor();
        return estimateFromImage(median);
    }

    private Map<Double, Double> estimateFromImage(HSBColor img){
        System.err.println("estimateFromImage: " + img.toString());
        Map<Double, Double> distances = new HashMap<Double, Double>();
        for(Map.Entry<Double, Integer> nameColours : concentrations.entrySet()){
            Double name = nameColours.getKey();
            int rgbColor = nameColours.getValue();
            System.err.println("rgbColor " + name + ": " + Integer.toHexString(rgbColor));
            HSBColor hsbColor = new HSBColor(rgbColor);
            System.err.println(name + ": " + hsbColor.toString());
//			HSBColor diff = image.differenceFrom(hsbColor);
//			double dist = Math.sqrt(diff.h * diff.h + diff.s * diff.s + diff.b * diff.b);
            double hDist = hsbColor.h - img.h;
            double sDist = hsbColor.s - img.s;
            double bDist = hsbColor.b - img.b;
            double dist = Math.sqrt(hDist * hDist + sDist * sDist + bDist * bDist);
            distances.put(name, dist);
        }
        return distances;
    }
}
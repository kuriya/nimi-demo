package org.nimi.demo;

import java.io.File;

public class Application {
    public static void main(String[] args){
        testDateTransformer();
    }


    public static void testDateTransformer(){
        String path = "C:\\Users\\ACER\\OneDrive\\Desktop\\files\\inputfile.txt";
        path = path.replace("\\", "/");
        File file = new File(path);
        TransformDates.transformDates(file,"dd/MM/yyyy", "yyyy/MM/dd");
    }
}
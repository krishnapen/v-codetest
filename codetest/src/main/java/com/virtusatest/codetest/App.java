package com.virtusatest.codetest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class App {

    public static void main(String [] args){

        String filePath = "c://Users/bpendyala/Desktop/testinputs.txt";

        try (Stream<String> lines = Files.lines(Paths.get(filePath))){
            lines
                    .forEach(line -> Numerals.parse(line));
        }
        catch (IOException io){
            io.printStackTrace();
        }



    }
}

// 20191034
// w1790297
// Ravishan Premerathne

package com.company;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static com.company.Algorithm.fileReader;
import static com.company.Algorithm.vericesNumber;

//import static com.company.Algorithm.graph;

public class Main {

    public static void main(String[] args) {


//Let us create a graph shown in the above example


            Algorithm a = new Algorithm();
            String fileName ="bridge_1.txt";


            int[][] data = fileReader(fileName);


            int sourceNumber = 0;
            int sinkNumber = vericesNumber - 1;

            System.out.println("-A-D-J-A-C-E-N-C-Y-  -M-A-T-R-I-X- \n");

            System.out.println("NODES : "+vericesNumber);
            System.out.println(Arrays.deepToString(data).replace(("], "), "]\n"));
            System.out.println("\n");


            System.out.println("01. Testing file                   : " + fileName);
            System.out.println("02. Number of vertices             : " + vericesNumber);
            System.out.println("03. Source Number                  : " + sourceNumber);
            System.out.println("04. Sink Number                    : " + sinkNumber);

            long startTime = System.nanoTime();
            System.out.println("05. The maximum possible flow      : " + a.algorithm(data,sinkNumber));
            long endTime = System.nanoTime();
            double totalDuration = ((endTime - startTime)/1_000_000_000.0);

            System.out.println("06. Total duration in seconds : "+String.format("%.4f", totalDuration));
            System.out.println("\n");

        }





    }




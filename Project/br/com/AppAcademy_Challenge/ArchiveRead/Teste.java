package br.com.AppAcademy_Challenge.ArchiveRead;

import java.io.BufferedReader;
import java.io.FileReader;

public class Teste {
    public static void main(String[] args) {
        CSVReader reader =  new CSVReader("br\\com\\AppAcademy_Challenge\\AppAcademy_Candidates.csv");

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(CSVReader.getFile()));

            reader.runAll(csvReader);

        } catch (Exception e) {
           e.printStackTrace();
        }
       
    }
}

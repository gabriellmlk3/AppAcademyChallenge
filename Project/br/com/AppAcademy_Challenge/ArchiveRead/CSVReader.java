package br.com.AppAcademy_Challenge.ArchiveRead;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class CSVReader {
        private String csvPath = null;
        private static File csvFile = null;        

        private String row;

        private int lineCount = 0;
        private int idadeCount = 0;    
        private double androidCount = 0;
        private double qaCount = 0;
        private double iosCount = 0;
        private long idadeAverage = 0;
         
        private String estadoAtual = null;
        private String vagaAtual = null;
        private String nomeAtual = null;
        private int idadeAtual = 0;        

        List<String> estados = new ArrayList<>();
        List<String> vagas = new ArrayList<>();
        List<String> nomes = new ArrayList<>();
        List<Integer> idades = new ArrayList<>();
        List<String> lisForWrite = new ArrayList<>();
        String instructor = null;
        List<String> sortedListForWrite;

        Map<String, Integer> hm = new HashMap<String, Integer>();      
        
    public CSVReader(String path) {
        this.csvPath = path;
        csvFile = new File(csvPath);
    }
    
    public void runAll(BufferedReader reader) throws IOException{
        read(reader);
        printProporcaoCandidatos();
        getAverageYearQA();
        printNumeroEstadosDistintos();
        rankEstate();
        convertToCSV();
        getInstrutor();
        
        



    }
   
    public void read(BufferedReader reader) throws IOException{        

            reader.readLine();

            while((row = reader.readLine()) != null) {

                String[] data = row.split(";");
                this.nomeAtual = data[0];
                this.vagaAtual = data[1];
                this.idadeAtual = Integer.parseInt(data[2].replace(" anos", ""));
                this.estadoAtual = data[3];

                discoveryInstructor(nomeAtual, vagaAtual, idadeAtual, estadoAtual);
                
                this.nomes.add(nomeAtual);
                this.vagas.add(vagaAtual);
                this.idades.add(idadeAtual);
                this.estados.add(estadoAtual);  
                    
                if (row.contains("Android")){                        
                    this.androidCount++;                        
                } else if (row.contains("QA")) {
                    this.qaCount++; 
                    int intIdade = idadeAtual;
                    this.idadeCount += intIdade;
                    this.idadeAverage = this.idadeCount / Math.round(this.qaCount);   
                } else if (row.contains("iOS")) {
                    this.iosCount++;                       
                }
                
                lisForWrite.add(nomeAtual + "; " 
                + vagaAtual + "; " 
                + idadeAtual + " anos" + "; " 
                + estadoAtual);               

                this.lineCount++; 
            }
    }

    public void printProporcaoCandidatos(){
        Double androidPercent = androidCount / lineCount * 100;
        Double qaPercent = qaCount / lineCount * 100;
        Double iosPercent = iosCount / lineCount * 100;

        System.out.println("Proporção de candidatos por vaga:\n" +
         "Android: " + Math.round(androidPercent) + "%\n" + 
          "QA     : " + Math.round(iosPercent) + "%\n" + 
          "iOS    : " + Math.round(qaPercent) + "%\n"); 
    }

     

    public void printNumeroEstadosDistintos(){
        for (String i : this.estados) {
            Integer key = hm.get(i);
            hm.put(i, (key == null) ? 1 : key + 1); 
        }

        System.out.println("Número de estados distintos presentes na lista: " + hm.size() + "\n");
    }
    

    public void convertToCSV() throws IOException{
        lisForWrite.add(0, ("Nome; Vaga; Idade; Estado"));
        sortedListForWrite = lisForWrite.stream().sorted().collect(Collectors.toList());
        File csvOutputFile = new File("Sorted_AppAcademy_Candidates.csv");

        System.out.println("Gerando lista ordenada...");

        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            sortedListForWrite.stream().forEach(pw::println);
        }   
        System.out.println("Lista ordenada salva como: " + csvOutputFile + "\n");
    }

    public void discoveryInstructor(String na, String va, int ia, String ea){
        if(va.contains("Android")){
            if(ea.contains("SC")){
                if(ia < 29 && ia > 20) {
                    if (ia % 2 != 0){
                        String[] splitedName =  na.split(" ");
                        String firstName = splitedName[0];
                        String lastName = splitedName[1];
                        char lastChar = firstName.charAt(firstName.length() - 1);
                        String asString = Character.toString(lastChar);

                        if(asString.contains("o")){
                            instructor = firstName + " " + lastName;
                        }                        
                    }
                }
            } 
        }        
    }

    public void rankEstate(){ 
            Map<String, Integer> map = new TreeMap<String, Integer>(hm);

               Set set = map.entrySet();
               Iterator iterator = set.iterator();
               
               System.out.println("Rank dos 2 estados com menos ocorrências:\n");

               for(int i = 1; i < 3; i++) {
                   Map.Entry me = (Map.Entry)iterator.next();
                   System.out.print("#" + i + " " +  me.getKey() + " - " + me.getValue() + " candidato(s) \n");                   
               }
               System.out.println();
    }

    public static File getFile(){
        return csvFile;
    }

    public void getAverageYearQA(){
        System.out.println("Idade média dos candidatos de QA: " + idadeAverage + " anos\n");
    }

    public void getInstrutor(){
        System.out.println("Instrutor de Android: " + instructor + "\n");
    }
}

package br.com.AppAcademy_Challenge.ArchiveRead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ReadArchive {
    public static void main(String[] args) {
        String path = "br\\com\\AppAcademy_Challenge\\AppAcademy_Candidates.csv";
        File csvFile = new File(path);

        String row;
        int idadeCount = 0;
        int lineCount = 0;
        double androidCount = 0;
        double qaCount = 0;
        double iosCount = 0;
        long idadeAverage = 0; 
        String estadosAtual = null;
        String vagasAtual = null;
        String nomesAtual = null;
        int idadesAtual = 0;
        int estadosCount = 0;

        if(csvFile.isFile()){
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
                List<String> estados = new ArrayList<>();
                List<String> vagas = new ArrayList<>();
                List<String> nomes = new ArrayList<>();
                List<Integer> idades = new ArrayList<>();
                
                
                       

                csvReader.readLine();
                while((row = csvReader.readLine()) != null) {

                    String[] data = row.split(";");
                    nomesAtual = data[0];
                    vagasAtual = data[1];
                    idadesAtual = Integer.parseInt(data[2].replace(" anos", ""));
                    estadosAtual = data[3];

                    
                    nomes.add(nomesAtual);
                    vagas.add(vagasAtual);
                    idades.add(idadesAtual);
                    estados.add(estadosAtual);
                    //String vagas = data[1];                 
                    
                        //Separa os candidatos por vaga e faz a contagem
                    if (row.contains("Android")){                        
                        androidCount++;                        
                    } else if (row.contains("QA")) {
                        qaCount++; 

                            //Retira a String "anos" do dado recebido e transforma a string em inteiro
                        int intIdade = idadesAtual;
                        idadeCount += intIdade;
                        idadeAverage = idadeCount / Math.round(qaCount);                     
                        
                    } else if (row.contains("iOS")) {
                        iosCount++;                       
                    }
                    lineCount++; 
                                     
                }
                
                Map<String, Integer> hm = new HashMap<String, Integer>();
                
                for (String i : estados) {
                    Integer j = hm.get(i);
                    hm.put(i, (j == null) ? 1 : j + 1);
                }

                Double androidPercent = androidCount / lineCount * 100;
                Double qaPercent = qaCount / lineCount * 100;
                Double iosPercent = iosCount / lineCount * 100;

               System.out.println("Proporção de candidatos por vaga:\n" +
                "Android: " + Math.round(androidPercent) + "%\n" + 
                "QA     : " + Math.round(iosPercent) + "%\n" + 
                "iOS    : " + Math.round(qaPercent) + "%\n"); 
                
               System.out.println("Idade média dos candidatos de QA: " + idadeAverage + " anos\n");

               System.out.println("Número de estados distintos presentes na lista: " + hm.size() + "\n");
            
               
                
               

               
               Map<String, Integer> map = new TreeMap<String, Integer>(hm);
               Set set2 = map.entrySet();
               Iterator iterator2 = set2.iterator();
               
               System.out.println("Rank dos 2 estados com menos ocorrências:\n");

               for(int i = 1; i < 3; i++) {
                   Map.Entry me2 = (Map.Entry)iterator2.next();
                   System.out.print("#" + i + " " +  me2.getKey() + " - ");
                   System.out.println(me2.getValue() + " candidato(s)");
               }

               csvReader.close();
            } catch (Exception e) {
               e.printStackTrace();
               
            } 
        }
    }
}
package br.com.AppAcademy_Challenge.ArchiveRead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
        String estados = null;

        if(csvFile.isFile()){
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
                       

                csvReader.readLine();
                while((row = csvReader.readLine()) != null) {
                    String[] data = row.split(";");
                    estados = data[3];
                    String vagas = data[1];                 

                        //Separa os candidatos por vaga e faz a contagem
                    if (vagas.equals("Android")){                        
                        androidCount++;                        
                    } else if (vagas.equals("QA")) {
                        qaCount++; 

                            //Retira a String "anos" do dado recebido e transforma a string em inteiro
                        int intIdade = Integer.parseInt(data[2].replace(" anos", ""));
                        idadeCount += intIdade;
                        idadeAverage = idadeCount / Math.round(qaCount);                     
                        
                    } else {
                        iosCount++;                       
                    } 

                    

                    switch (estados){
                        case "SC": {

                        }
                        case  "AC":{

                        }
                        case  "AL":{

                        }
                        case  "AP":{

                        }
                        case  "AM":{

                        }
                        case  "BA":{

                        }
                        case  "CE":{

                        }
                        case  "ES":{

                        }
                        case  "GO":{

                        }
                        case  "MA":{

                        }
                        case  "MT":{

                        }
                        case  "MS":{

                        }
                        case  "MG":{

                        }
                        case  "PA":{

                        }
                        case  "PB":{

                        }
                        case  "PR":{

                        }
                        case  "PE":{

                        }
                        case  "PI":{

                        }
                        case  "RJ":{

                        }
                        case  "RN":{

                        }
                        case  "RS":{

                        }
                        case  "RO":{

                        }
                        case  "RR":{

                        }                        
                        case  "SP":{

                        }
                        case  "SE":{

                        }
                        case  "TO":{

                        }
                        case  "DF":{

                        }

                            

                            

                            
                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            

                            
                        }
                    }

                    lineCount++;                  
                }
                System.out.println(estados);

                Double androidPercent = androidCount / lineCount * 100;
                Double qaPercent = qaCount / lineCount * 100;
                Double iosPercent = iosCount / lineCount * 100;

                System.out.println("Proporção de candidatos por vaga:");                
                System.out.println("Android: " + Math.round(androidPercent) + "%");                
                System.out.println("QA     : " + Math.round(iosPercent) + "%");
                System.out.println("iOS    : " + Math.round(qaPercent) + "%");
                System.out.println();
                System.out.println("Idade média dos candidatos de QA: " + idadeAverage + " anos");

                

            } catch (Exception e) {
               e.printStackTrace();
            }
        }
    }
}
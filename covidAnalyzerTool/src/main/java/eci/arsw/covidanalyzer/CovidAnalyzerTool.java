package eci.arsw.covidanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Camel Application
 */
public class CovidAnalyzerTool {

    private ResultAnalyzer resultAnalyzer;
    private TestReader testReader;
    private int amountOfFilesTotal;
    private AtomicInteger amountOfFilesProcessed;
    private LinkedList<ProcessingThread> listaThreads = new LinkedList<>();

    public CovidAnalyzerTool() {
        resultAnalyzer = new ResultAnalyzer();
        testReader = new TestReader();
        amountOfFilesProcessed = new AtomicInteger();
    }

    public void processResultData(int N) {
        amountOfFilesProcessed.set(0);
        List<File> resultFiles = getResultFileList();
        amountOfFilesTotal = resultFiles.size();
        crearThreads(N,resultFiles);
        for(ProcessingThread t:listaThreads){
            t.start();
        }
        for(ProcessingThread t:listaThreads){
            try{
                t.join();
               
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void crearThreads(int N, List<File> resultFiles){
        amountOfFilesTotal = resultFiles.size();
        for(int i=0;i<N;i++){
            if(amountOfFilesTotal % N != 0){
                ProcessingThread threadCovid = new ProcessingThread(i*((int)(amountOfFilesTotal/N)+1),(i+1)*((int)(amountOfFilesTotal/N)+1)-1,resultFiles,testReader,resultAnalyzer,amountOfFilesProcessed);
                listaThreads.add(threadCovid);
            }
            else{
                ProcessingThread threadCovid = new ProcessingThread(i*(int)(amountOfFilesTotal/N),(i+1)*(int)(amountOfFilesTotal/N)-1,resultFiles,testReader,resultAnalyzer,amountOfFilesProcessed);
                listaThreads.add(threadCovid);
            }
        }
        
    }

    private List<File> getResultFileList() {
        List<File> csvFiles = new ArrayList<>();
        try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/")).filter(path -> path.getFileName().toString().endsWith(".csv"))) {
            csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFiles;
    }


    public Set<Result> getPositivePeople() {
        return resultAnalyzer.listOfPositivePeople();
    }

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        CovidAnalyzerTool covidAnalyzerTool = new CovidAnalyzerTool();
        Thread processingThread = new Thread(() -> covidAnalyzerTool.processResultData(5));
        processingThread.start();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.contains("exit"))
                break;
            String message = "Processed %d out of %d files.\nFound %d positive people:\n%s";
            Set<Result> positivePeople = covidAnalyzerTool.getPositivePeople();
            String affectedPeople = positivePeople.stream().map(Result::toString).reduce("", (s1, s2) -> s1 + "\n" + s2);
            message = String.format(message, covidAnalyzerTool.amountOfFilesProcessed.get(), covidAnalyzerTool.amountOfFilesTotal, positivePeople.size(), affectedPeople);
            System.out.println(message);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.arsw.covidanalyzer;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Acer
 */
public class ProcessingThread extends Thread{
    
    private int a;
    private int b;
    private List<File> resultFiles;
    private TestReader testReader;
    private ResultAnalyzer resultAnalyzer;
    private AtomicInteger amountOfFilesProcessed;
    private boolean detener = false;

    public ProcessingThread(int a, int b, List<File> resultFiles, TestReader testReader,ResultAnalyzer resultAnalyzer,AtomicInteger amountOfFilesProcessed) {
        this.a = a;
        this.b= b;
        this.resultFiles = resultFiles;
        this.testReader = testReader;
        this.resultAnalyzer = resultAnalyzer;
        this.amountOfFilesProcessed = amountOfFilesProcessed;
    }
    public void corra() throws InterruptedException{
        for (int i = a; i<b;i++) {
            List<Result> results = testReader.readResultsFromFile(resultFiles.get(i));
            for (Result result : results) {
                resultAnalyzer.addResult(result);
            }
            amountOfFilesProcessed.incrementAndGet();
        }
        synchronized (this) {
            while (detener) {                      
                wait();
            }
        }
    }
    public void run(){ 
        
        try {
            corra();
                        
	} catch (InterruptedException e) {
            e.printStackTrace();
	}
        
    }

    public void setDetener() {
        if(!detener){
            this.detener = true;
            System.out.println("Johann es re gurrero");
        }
        else{
            this.detener = false; 
            System.out.println("Johann es re gurrero");
        }
    }
    
    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.arsw.covidanalyzer.service.impl;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andres Davila
 */
@Service
public class CovidAggregateService implements ICovidAggregateService{
    
   HashMap<Result,ResultType> resultados= new HashMap<>();
   HashMap<UUID,ResultType> multiple = new HashMap<>();

    @Override
    public boolean aggregateResult(Result result, ResultType type) {
        
        ResultType res=resultados.put(result, type);
        
        return res!=null ? true : false;
    }

    @Override
    public List<Result> getResult(ResultType type) {
        List<Result> res = new ArrayList<>();
        for(Result r: resultados.keySet()){
            if(resultados.get(r).equals(type)){
                res.add(r);
            }
        }
        return res;
    }

    @Override
    public void upsertPersonWithMultipleTests(UUID id, ResultType type) {
        ResultType res=multiple.put(id, type);
        
    }
    
}

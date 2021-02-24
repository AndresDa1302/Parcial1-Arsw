package eci.arsw.covidanalyzer;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import eci.arsw.covidanalyzer.service.impl.CovidAggregateService;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
public class CovidAggregateController {
    @Autowired
    CovidAggregateService covidAggregateService;

    //TODO: Implemente todos los metodos POST que hacen falta.

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.POST)
    public ResponseEntity addTruePositiveResult(Result result) {
        //TODO
        return covidAggregateService.aggregateResult(result, ResultType.TRUE_POSITIVE)? new ResponseEntity<>("Aceptado",HttpStatus.ACCEPTED):new ResponseEntity<>("Rechazado",HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.POST)
    public ResponseEntity addTrueNegativeResult(Result result) {
        //TODO
        return covidAggregateService.aggregateResult(result, ResultType.TRUE_NEGATIVE)? new ResponseEntity<>("Aceptado",HttpStatus.ACCEPTED):new ResponseEntity<>("Rechazado",HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/covid/result/false-positive", method = RequestMethod.POST)
    public ResponseEntity addFalsePositiveResult(Result result) {
        //TODO
        return covidAggregateService.aggregateResult(result, ResultType.FALSE_POSITIVE)? new ResponseEntity<>("Aceptado",HttpStatus.ACCEPTED):new ResponseEntity<>("Rechazado",HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/covid/result/false-negative", method = RequestMethod.POST)
    public ResponseEntity addFalseNegativeResult(Result result) {
        //TODO
        return covidAggregateService.aggregateResult(result, ResultType.FALSE_POSITIVE)? new ResponseEntity<>("Aceptado",HttpStatus.ACCEPTED):new ResponseEntity<>("Rechazado",HttpStatus.NOT_FOUND);
    }
    

    //TODO: Implemente todos los metodos GET que hacen falta.

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.GET)
    public ResponseEntity getTruePositiveResult() {
        //TODO
       try {
            List<Result> res=covidAggregateService.getResult(ResultType.TRUE_POSITIVE);
            
            return new ResponseEntity<>(res ,HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(ResourceNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("El elemento no se encontró",HttpStatus.NOT_FOUND);
        }        
    }
    
    
    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.GET)
    public ResponseEntity getTrueNegativeResult() {
        try {
            List<Result> res=covidAggregateService.getResult(ResultType.TRUE_NEGATIVE);
            return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(ResourceNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("El elemento no se encontró",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/covid/result/false-positive", method = RequestMethod.GET)
    public ResponseEntity getFalsePositiveResult() {
        try {
            List<Result> res=covidAggregateService.getResult(ResultType.FALSE_POSITIVE);
            return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(ResourceNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("El elemento no se encontró",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/covid/result/false-negative", method = RequestMethod.GET)
    public ResponseEntity getFalseNegativeResult() {
        try {
            List<Result> res=covidAggregateService.getResult(ResultType.FALSE_NEGATIVE);
            return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(ResourceNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("El elemento no se encontró",HttpStatus.NOT_FOUND);
        }
    }


    //TODO: Implemente el método.

    @RequestMapping(value = "/covid/result/persona/{id}", method = RequestMethod.PUT)
    public ResponseEntity savePersonaWithMultipleTests(@PathVariable UUID id) {
        
        
        return null;
    }
    
}
package fr.miage.sid.pricebot.controller;

import fr.miage.sid.pricebot.service.PriceApi;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@ComponentScan
@RestController
public class PriceController {


    @Autowired
    private PriceApi priceApi;



    @RequestMapping(value = "/price/{product}",produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBook(@PathVariable("product") String productName) {
        JSONObject result = null;

        try {
            result = priceApi.getPrice(productName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result != null)
            return new ResponseEntity(result.toString(),HttpStatus.OK);
        else
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }




}

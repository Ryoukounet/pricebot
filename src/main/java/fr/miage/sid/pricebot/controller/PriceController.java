package fr.miage.sid.pricebot.controller;

import fr.miage.sid.pricebot.service.PriceApi;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/priceApi")
public class PriceController {

    @Autowired
    private PriceApi priceApi;

    @GetMapping(value = "/price/{product}")
    public ResponseEntity<?> getBook(@PathVariable("product") String productName) {
        JSONObject result = null;
        try {
            result = priceApi.getPrice(productName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

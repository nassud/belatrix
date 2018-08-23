package co.com.belatrix.moneyxchange.controller;

import co.com.belatrix.moneyxchange.models.CurrencyRate;
import co.com.belatrix.moneyxchange.models.LatestRates;
import co.com.belatrix.moneyxchange.repos.CurrencyRateRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/v1/currencyRate")
public class CurrencyRateController {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    @GetMapping(path = "/add")
    public @ResponseBody
    String addNewCurrencyRate(
            @RequestParam String name,
            @RequestParam String code,
            @RequestParam BigDecimal rate,
            @RequestParam Date date
    ) {
        CurrencyRate n = new CurrencyRate();
        n.setName(name);
        n.setCode(code);
        n.setRate(rate);
        n.setDate(date);
        currencyRateRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<CurrencyRate> getAllCurrencyRates() {
        return currencyRateRepository.findAll();
    }
    
    @GetMapping(path = "/latest")
    public @ResponseBody
    LatestRates getLatestRates(@RequestParam(value="base", required = false) String base, @RequestParam(value="symbols", required = false) String symbols) {
        
        CurrencyRate currencyBase;
        if(base != null && !base.isEmpty()){
            currencyBase = currencyRateRepository.findByCode(base);
        } else {
            currencyBase = currencyRateRepository.findByCode("USD");
        }
        
        Iterable<CurrencyRate> rates;
        if(symbols != null && !symbols.isEmpty()){
            String[] symbolList = symbols.split(",");
            rates = currencyRateRepository.findAllByCode(Arrays.asList(symbolList));
        } else {
            rates = currencyRateRepository.findAll();
        }
        
        LatestRates latest = new LatestRates();
        latest.setBase(currencyBase.getCode());
        latest.setDate(new Date());
        latest.setRates(rates);
        
        return latest;
    }
}

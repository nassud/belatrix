package co.com.belatrix.moneyxchange.models;

import java.util.Date;

/**
 *
 * @author sebas
 */
public class LatestRates {
    
    private String base;
    
    private Date date;
    
    private Iterable<CurrencyRate> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Iterable<CurrencyRate> getRates() {
        return rates;
    }

    public void setRates(Iterable<CurrencyRate> rates) {
        this.rates = rates;
    }     
    
}

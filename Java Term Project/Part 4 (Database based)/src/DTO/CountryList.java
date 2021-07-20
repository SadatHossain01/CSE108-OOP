package DTO;

import DataModel.Country;

import java.io.Serializable;
import java.util.List;

public class CountryList implements Serializable {
    List<Country> countryList;

    public CountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }
}

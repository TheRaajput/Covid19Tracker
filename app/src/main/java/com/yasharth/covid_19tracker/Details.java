package com.yasharth.covid_19tracker;

public class Details {
    String region, totalInfected, recovered, deceased;

    public Details(String region, String totalInfected, String recovered, String deceased){
        this.region = region;
        this.totalInfected = totalInfected;
        this.recovered = recovered;
        this.deceased = deceased;
    }

    public String getRegion(){
        return region;
    }

    public String getTotalInfected(){
        return totalInfected;
    }

    public String getRecovered(){
        return recovered;
    }

    public String getDeceased(){
        return deceased;
    }
}

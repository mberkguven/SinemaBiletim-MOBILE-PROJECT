package com.example.baba.sinemabiletim.Model;

// Biletkod means TicketCode.  Biletcode for onlycode,misirkod for promocode code.

public class Biletkod {

    private String Biletkod;
    private String Misirkod;

    public Biletkod(){}


    public Biletkod(String biletkod, String misirkod) {
        Biletkod = biletkod;
        Misirkod = misirkod;
    }

    public String getBiletkod() {
        return Biletkod;
    }

    public void setBiletkod(String biletkod) {
        Biletkod = biletkod;
    }

    public String getMisirkod() {
        return Misirkod;
    }

    public void setMisirkod(String misirkod) {
        Misirkod = misirkod;
    }
}

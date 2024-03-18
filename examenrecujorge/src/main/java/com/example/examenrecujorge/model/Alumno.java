package com.example.examenrecujorge.model;

public class Alumno {
    private String name;
    private String lastName;
    private Double AD;
    private Double SGE;
    private Double DI;
    private Double PMDM;
    private Double EIE;
    private Double PSP;
    private Double HLC;

    public Alumno(String name, String lastName, Double AD, Double SGE, Double DI, Double PMDM, Double EIE, Double PSP, Double HLC) {
        this.name = name;
        this.lastName = lastName;
        this.AD = AD;
        this.SGE = SGE;
        this.DI = DI;
        this.PMDM = PMDM;
        this.EIE = EIE;
        this.PSP = PSP;
        this.HLC = HLC;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getAD() {
        return AD;
    }

    public Double getSGE() {
        return SGE;
    }

    public Double getDI() {
        return DI;
    }

    public Double getPMDM() {
        return PMDM;
    }

    public Double getEIE() {
        return EIE;
    }

    public Double getPSP() {
        return PSP;
    }

    public Double getHLC() {
        return HLC;
    }
}

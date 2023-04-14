package com.growee.objects.modify;

public class FlushPumps {

    String pumps_A = "0";
    String pumps_B = "0";
    String pumps_C = "0";
    String pumps_D = "0";
    String pumps_E = "0";
    String pumps_F = "0";
    String pumps_G = "0";
    String pumps_H = "0";
    String pumps_pH_plus = "0";
    String pumps_pH_minus = "0";

    public FlushPumps( String pumps_A, String pumps_B, String pumps_C, String pumps_D,
                       String pumps_E, String pumps_F, String pumps_G, String pumps_H,
                       String pumps_pH_plus,String pumps_pH_minus) {
        this.pumps_A = pumps_A;
        this.pumps_B = pumps_B;
        this.pumps_C = pumps_C;
        this.pumps_D = pumps_D;
        this.pumps_E = pumps_E;
        this.pumps_F = pumps_F;
        this.pumps_G = pumps_G;
        this.pumps_H = pumps_H;
        this.pumps_pH_plus = pumps_pH_plus;
        this.pumps_pH_minus = pumps_pH_minus;
    }


    public String getPumps_A() {
        return pumps_A;
    }

    public String getPumps_B() {
        return pumps_B;
    }

    public String getPumps_C() {
        return pumps_C;
    }

    public String getPumps_D() {
        return pumps_D;
    }

    public String getPumps_pH_plus() {
        return pumps_pH_plus;
    }

    public String getPumps_pH_minus() {
        return pumps_pH_minus;
    }

    public String getPumps_E() {
        return pumps_E;
    }

    public String getPumps_F() {
        return pumps_F;
    }

    public String getPumps_G() {
        return pumps_G;
    }

    public String getPumps_H() {
        return pumps_H;
    }
}

/**
 *
 */
package com.growee.objects.modify;

/**
 * @author Maor
 */
public class TargetECAndPH {


    //	test new target ec
    String literPerMilliliter;
    String ecRange;
    String targetPH;
    String lastTargetPH;
    String targetEC;
    String phRange;
    String lastModifyPH;
    String lastModifyEC;


    public String getPhRange() {
        return phRange;
    }

    public void setPhRange(String phRange) {
        this.phRange = phRange;
    }

    public String getLastModifyPH() {
        return lastModifyPH;
    }

    public void setLastModifyPH(String lastModifyPH) {
        this.lastModifyPH = lastModifyPH;
    }

    public String getLastModifyEC() {
        return lastModifyEC;
    }

    public void setLastModifyEC(String lastModifyEC) {
        this.lastModifyEC = lastModifyEC;
    }

    public TargetECAndPH() {
    }

    public TargetECAndPH(TargetECAndPH targetECAndPH) {
        this.targetPH = targetECAndPH.getTargetPH();
        this.lastTargetPH = targetECAndPH.getLastTargetPH();
        this.phRange = targetECAndPH.getPhRange();
        this.targetEC = targetECAndPH.getTargetEC();
        this.ecRange = targetECAndPH.getEcRange();
        this.lastModifyPH = targetECAndPH.getLastModifyPH();
        this.lastModifyEC = targetECAndPH.getLastModifyEC();
        this.literPerMilliliter = targetECAndPH.getMilliliterPerLitter();
    }

    public String getMilliliterPerLitter() {
        return literPerMilliliter;
    }

    public String getTargetPH() {
        return targetPH;
    }

    public void setTargetPH(String targetPH) {
        this.targetPH = targetPH;
    }

    public void setTargetEC(String targetEC) {
        this.targetEC = targetEC;
    }

    public String getTargetEC() {
        return targetEC;
    }

    public String getEcRange() {
        return ecRange;
    }

    public String getLiterPerMilliliter() {
        return literPerMilliliter;
    }

    public String getLastTargetPH() {
        return lastTargetPH;
    }

    public void setTargetECAndPH(String targetPH, String lastTargetPH, String phRange, String targetEC, String ecRange, String lastModifyPH, String lastModifyEC, String literPerMilliliter) {
        this.targetPH = targetPH;
        this.lastTargetPH = lastTargetPH;
        this.phRange = phRange;
        this.targetEC = targetEC;
        this.ecRange = ecRange;
        this.lastModifyPH = lastModifyPH;
        this.lastModifyEC = lastModifyEC;
        this.literPerMilliliter = literPerMilliliter;
    }


}

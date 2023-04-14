/**
 *
 */
package com.growee.objects.modify;

/**
 * @author Maor
 */
public class TargetECBottelsNames {

    String bottleAName;
    String bottleBName;
    String bottleCName;
    String bottleDName;
    String bottleEName;
    String bottleFName;
    String bottleGName;
    String bottleHName;
    String bottleANameTarget;
    String bottleBNameTarget;
    String bottleCNameTarget;
    String bottleDNameTarget;
    String bottleENameTarget;
    String bottleFNameTarget;
    String bottleGNameTarget;
    String bottleHNameTarget;
    String waterQuantity;
    String nutrientsType;

    public TargetECBottelsNames() {
    }

    public TargetECBottelsNames(TargetECBottelsNames targetECBottelsNames) {
        this.bottleAName = targetECBottelsNames.getBottleAName();
        this.bottleBName = targetECBottelsNames.getBottleBName();
        this.bottleCName = targetECBottelsNames.getBottleCName();
        this.bottleDName = targetECBottelsNames.getBottleDName();
        this.bottleEName = targetECBottelsNames.getBottleEName();
        this.bottleFName = targetECBottelsNames.getBottleFName();
        this.bottleGName = targetECBottelsNames.getBottleGName();
        this.bottleHName = targetECBottelsNames.getBottleHName();
        this.bottleANameTarget = targetECBottelsNames.getBottleANameTarget();
        this.bottleBNameTarget = targetECBottelsNames.getBottleBNameTarget();
        this.bottleCNameTarget = targetECBottelsNames.getBottleCNameTarget();
        this.bottleDNameTarget = targetECBottelsNames.getBottleDNameTarget();
        this.bottleENameTarget = targetECBottelsNames.getBottleENameTarget();
        this.bottleFNameTarget = targetECBottelsNames.getBottleFNameTarget();
        this.bottleGNameTarget = targetECBottelsNames.getBottleGNameTarget();
        this.bottleHNameTarget = targetECBottelsNames.getBottleHNameTarget();
        this.waterQuantity = targetECBottelsNames.getWaterQuantity();
        this.nutrientsType = targetECBottelsNames.getNutrientsType();
    }

    public String getBottleAName() {
        return bottleAName;
    }

    public void setBottleAName(String bottleAName) {
        this.bottleAName = bottleAName;
    }

    public String getBottleBName() {
        return bottleBName;
    }

    public void setBottleBName(String bottleBName) {
        this.bottleBName = bottleBName;
    }

    public String getBottleCName() {
        return bottleCName;
    }

    public String getNutrientsType() {
        return nutrientsType;
    }

    public void setBottelsName(String bottleAName, String bottleBName, String bottleCName, String bottleDName,
                               String bottleEName, String bottleFName,  String bottleGName, String bottleHName,
                               String bottleANameTarget, String bottleBNameTarget, String bottleCNameTarget,
                               String bottleDNameTarget, String bottleENameTarget, String bottleFNameTarget,
                               String bottleGNameTarget, String bottleHNameTarget,
                               String waterQuantity, String nutrientsType) {
        this.bottleAName = bottleAName;
        this.bottleBName = bottleBName;
        this.bottleCName = bottleCName;
        this.bottleDName = bottleDName;
        this.bottleEName = bottleEName;
        this.bottleFName = bottleFName;
        this.bottleGName = bottleGName;
        this.bottleHName = bottleHName;
        this.bottleANameTarget = bottleANameTarget;
        this.bottleBNameTarget = bottleBNameTarget;
        this.bottleCNameTarget = bottleCNameTarget;
        this.bottleDNameTarget = bottleDNameTarget;
        this.bottleENameTarget = bottleENameTarget;
        this.bottleFNameTarget = bottleFNameTarget;
        this.bottleGNameTarget = bottleGNameTarget;
        this.bottleHNameTarget = bottleHNameTarget;
        this.waterQuantity = waterQuantity;
        this.nutrientsType = nutrientsType;
    }


    public String getBottleANameTarget() {
        return bottleANameTarget;
    }

    public String getBottleBNameTarget() {
        return bottleBNameTarget;
    }

    public String getBottleCNameTarget() {
        return bottleCNameTarget;
    }

    public String getWaterQuantity() {
        return waterQuantity;
    }

    public String getBottleDName() {
        return bottleDName;
    }

    public String getBottleEName() {
        return bottleEName;
    }

    public String getBottleFName() {
        return bottleFName;
    }

    public String getBottleGName() {
        return bottleGName;
    }

    public String getBottleHName() {
        return bottleHName;
    }

    public String getBottleDNameTarget() {
        return bottleDNameTarget;
    }

    public String getBottleENameTarget() {
        return bottleENameTarget;
    }

    public String getBottleFNameTarget() {
        return bottleFNameTarget;
    }

    public String getBottleGNameTarget() {
        return bottleGNameTarget;
    }

    public String getBottleHNameTarget() {
        return bottleHNameTarget;
    }
}

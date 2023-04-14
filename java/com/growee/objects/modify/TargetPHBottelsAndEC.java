
package com.growee.objects.modify;

import java.util.Arrays;

/**
 * @author Maor
 */
public class TargetPHBottelsAndEC {

    private static final int MAX_ITERATION = 10;
    private static final double FACTORY_OF_SAFE = 0.8;
    private static final int START_FACTORY_OF_SAFE_FROM_LITTER = 100;


    TargetECBottelsNames bottelsNames;
    TargetECMililiters bottelsMililiters;
    TargetECAndPH targetECAndPH;

    public TargetPHBottelsAndEC(TargetECBottelsNames bottelsNames, TargetECMililiters bottelsMililiters, TargetECAndPH targetECAndPH) {
        this.bottelsNames = bottelsNames;
        this.bottelsMililiters = bottelsMililiters;
        this.targetECAndPH = targetECAndPH;
    }


    public TargetECBottelsNames getBottelsNames() {
        return bottelsNames;
    }


    public TargetECMililiters getBottelsMililiters() {
        return bottelsMililiters;
    }


    public TargetECAndPH getTargetECAndPh() {
        return targetECAndPH;
    }


    public void computeTargetrEcRelations(int waterQuantity) {
        float targetEcInt = Float.parseFloat(this.targetECAndPH.getTargetEC());
        if (targetEcInt > 0) {
            if (waterQuantity >= START_FACTORY_OF_SAFE_FROM_LITTER) {
                waterQuantity *= FACTORY_OF_SAFE;
            }
            computeEcRelationsByWaterQuantity(waterQuantity);
        }
    }

    private void computeEcRelationsByWaterQuantity(int waterQuantity) {
        float ec_a = Float.parseFloat(this.bottelsMililiters.getBottleA());
        float ec_b = Float.parseFloat(this.bottelsMililiters.getBottleB());
        float ec_c = Float.parseFloat(this.bottelsMililiters.getBottleC());
        float ec_d = Float.parseFloat(this.bottelsMililiters.getBottleD());
        float ec_e = Float.parseFloat(this.bottelsMililiters.getBottleE());
        float ec_f = Float.parseFloat(this.bottelsMililiters.getBottleF());
        float ec_g = Float.parseFloat(this.bottelsMililiters.getBottleG());
        float ec_h = Float.parseFloat(this.bottelsMililiters.getBottleH());
        String milPerLiter = this.targetECAndPH.getMilliliterPerLitter();
        ec_a = ec_a * (waterQuantity / Float.parseFloat(milPerLiter));
        ec_b = ec_b * (waterQuantity / Float.parseFloat(milPerLiter));
        ec_c = ec_c * (waterQuantity / Float.parseFloat(milPerLiter));
        ec_d = ec_d * (waterQuantity / Float.parseFloat(milPerLiter));
        ec_e = ec_e * (waterQuantity / Float.parseFloat(milPerLiter));
        ec_f = ec_f * (waterQuantity / Float.parseFloat(milPerLiter));
        ec_g = ec_g * (waterQuantity / Float.parseFloat(milPerLiter));
        ec_h = ec_h * (waterQuantity / Float.parseFloat(milPerLiter));
        this.bottelsMililiters.setBottleA_target(this.bottelsMililiters.getBottleA());
        this.bottelsMililiters.setBottleB_target(this.bottelsMililiters.getBottleB());
        this.bottelsMililiters.setBottleC_target(this.bottelsMililiters.getBottleC());
        this.bottelsMililiters.setBottleD_target(this.bottelsMililiters.getBottleD());
        this.bottelsMililiters.setBottleE_target(this.bottelsMililiters.getBottleE());
        this.bottelsMililiters.setBottleF_target(this.bottelsMililiters.getBottleF());
        this.bottelsMililiters.setBottleG_target(this.bottelsMililiters.getBottleG());
        this.bottelsMililiters.setBottleH_target(this.bottelsMililiters.getBottleH());
        double[] values = new double[] { ec_a, ec_b, ec_c, ec_d, ec_e, ec_f, ec_g, ec_h };
        int numberOfIteration = getNumberOfIteration(values);
        this.bottelsMililiters.setBottleA(String.format("%.1f", ec_a / numberOfIteration));
        this.bottelsMililiters.setBottleB(String.format("%.1f", ec_b / numberOfIteration));
        this.bottelsMililiters.setBottleC(String.format("%.1f", ec_c / numberOfIteration));
        this.bottelsMililiters.setBottleD(String.format("%.1f", ec_d / numberOfIteration));
        this.bottelsMililiters.setBottleE(String.format("%.1f", ec_e / numberOfIteration));
        this.bottelsMililiters.setBottleF(String.format("%.1f", ec_f / numberOfIteration));
        this.bottelsMililiters.setBottleG(String.format("%.1f", ec_g / numberOfIteration));
        this.bottelsMililiters.setBottleH(String.format("%.1f", ec_h / numberOfIteration));

    }

    private int getNumberOfIteration(double [] values) {
        double res = 0;
        values = Arrays.stream(values).filter(x -> x > 0).toArray();
        double minValue = values[0];


        for (double val: values) {
            if (val < minValue && val > 0) {
                minValue = val;
            }
        }

        if (minValue >= 0) {
            res =  Math.min(minValue, MAX_ITERATION);
        }

        return (int) res;
    }

}

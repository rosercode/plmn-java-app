package cool.example.plmn.entity;

/**
 * @author wangshuo
 * @date 2023/5/15 16:39
 * 建议 "针对不同范围内的体重的人的建议" 对应的实体类
 */

public class WeightAdvice {

    private int id;
    private double minWeight;
    private double maxWeight;
    private String advice;


    public WeightAdvice() {
    }

    public WeightAdvice(String advice) {
        this.advice = advice;
    }

    public WeightAdvice(int id, double minWeight, double maxWeight, String advice) {
        this.id = id;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.advice = advice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(double minWeight) {
        this.minWeight = minWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    @Override
    public String toString() {
        return "WeightAdvice{" +
                "id=" + id +
                ", minWeight=" + minWeight +
                ", maxWeight=" + maxWeight +
                ", advice='" + advice + '\'' +
                '}';
    }
}

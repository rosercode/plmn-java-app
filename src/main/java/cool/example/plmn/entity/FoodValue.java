package cool.example.plmn.entity;

/**
 * @author wangshuo
 * @date 2023/5/15 16:49
 * 对应的数据库表是 t_food_ranking
 */

public class FoodValue {

    private Integer id;
    private String name;
    private String nutritionValue;
    private int calorie;

    public FoodValue() {
    }


    public FoodValue(String name, String nutritionValue, int calorie) {
        this.name = name;
        this.nutritionValue = nutritionValue;
        this.calorie = calorie;
    }

    public FoodValue(Integer id, String name, String nutritionValue, int calorie) {
        this.id = id;
        this.name = name;
        this.nutritionValue = nutritionValue;
        this.calorie = calorie;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNutritionValue(String nutritionValue) {
        this.nutritionValue = nutritionValue;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNutritionValue() {
        return nutritionValue;
    }

    public int getCalorie() {
        return calorie;
    }

    @Override
    public String toString() {
        return "FoodValue{" +
                "name='" + name + '\'' +
                ", nutritionValue='" + nutritionValue + '\'' +
                ", calorie=" + calorie +
                '}';
    }
}

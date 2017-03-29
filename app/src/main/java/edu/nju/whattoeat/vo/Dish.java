package edu.nju.whattoeat.vo;

/**
 * Created by WillLester on 2017/3/25.
 */

public class Dish {
    private String name;
    //主料
    private String mainSubstance;
    //辅料
    private String substance;
    //做法
    private String operation;
    //口味
    private String taste;
    //餐次，早中晚
    private String time_to_eat;
    //营养
    private String nutrition;
    //种类 ，家常菜、甜品等
    private String kind;
    //地域，川菜、粤菜等
    private String place;

    public Dish(String name,String mainSubstance,String substance,String operation,String taste,String time_to_eat,String nutrition,String kind,String place) {
        this.name = name;
        this.mainSubstance = mainSubstance;
        this.substance = substance;
        this.operation = operation;
        this.taste = taste;
        this.time_to_eat = time_to_eat;
        this.nutrition = nutrition;
        this.kind = kind;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainSubstance(){
        return this.mainSubstance;
    }

    public String getSubstance(){
        return this.substance;
    }

    public String getOperation(){
        return this.operation;
    }

    public String getTaste(){
        return  this.taste;
    }

    public String getTime_to_eat(){
        return this.time_to_eat;
    }

    public String getNutrition(){
        return this.nutrition;
    }

    public String getKind(){
        return this.kind;
    }

    public String getPlace(){
        return this.place;
    }

    public void setMainSubstance(String mainSubstance){
        this.mainSubstance = mainSubstance;
    }

    public void setSubstance(String substance){
        this.substance = substance;
    }

    public void setOperation(String operation){
        this.operation = operation;
    }

    public void setTaste(String taste){
        this.taste = taste;
    }

    public void setTime_to_eat(String time_to_eat){
        this.time_to_eat = time_to_eat;
    }

    public void setNutrition(String nutrition){
        this.nutrition = nutrition;
    }

    public void setKind(String kind){
        this.kind = kind;
    }

    public void setPlace(String place){
        this.place = place;
    }
}

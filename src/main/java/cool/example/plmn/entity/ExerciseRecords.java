package cool.example.plmn.entity;

import java.util.Date;

/**
 * @author wangshuo
 * @date2023/5/15 19:56
 */

public class ExerciseRecords {

    private int id;
    private int userId;
    private String exerciseType;
    private String exerciseIntensity;
    private float caloriesBurned;
    private Date startTime;
    private Date endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getExerciseIntensity() {
        return exerciseIntensity;
    }

    public void setExerciseIntensity(String exerciseIntensity) {
        this.exerciseIntensity = exerciseIntensity;
    }

    public float getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(float caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ExerciseRecords{" +
                "id=" + id +
                ", userId=" + userId +
                ", exerciseType='" + exerciseType + '\'' +
                ", exerciseIntensity='" + exerciseIntensity + '\'' +
                ", caloriesBurned=" + caloriesBurned +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
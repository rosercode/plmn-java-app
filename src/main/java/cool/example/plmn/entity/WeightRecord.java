package cool.example.plmn.entity;


import java.time.LocalDateTime;


/**
 * @author wangshuo
 * @date 2023/5/15 19:47
 */


public class WeightRecord {
    private int id;
    private LocalDateTime recordTime;
    private int userId;
    private double weight;
    private String note;

    public WeightRecord() {
    }

    public WeightRecord(LocalDateTime recordTime, int userId, double weight, String note) {
        this.recordTime = recordTime;
        this.userId = userId;
        this.weight = weight;
        this.note = note;
    }

    public WeightRecord(int id, LocalDateTime recordTime, int userId, double weight, String note) {
        this.id = id;
        this.recordTime = recordTime;
        this.userId = userId;
        this.weight = weight;
        this.note = note;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(LocalDateTime recordTime) {
        this.recordTime = recordTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "WeightRecord{" +
                "id=" + id +
                ", recordTime=" + recordTime +
                ", userId=" + userId +
                ", weight=" + weight +
                ", note='" + note + '\'' +
                '}';
    }
}


package cool.example.plmn.controller;


import cool.example.plmn.App;
import cool.example.plmn.dao.WeightAdviceDao;
import cool.example.plmn.dao.WeightRecordDao;
import cool.example.plmn.entity.WeightAdvice;
import cool.example.plmn.entity.WeightRecord;
import cool.example.plmn.ui.ExerciseRecordsFrame;
import cool.example.plmn.ui.WeightRecordFrame;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author wangshuo
 * @date 2023/5/15 16:58
 */

public class UserController {

    public UserController() {
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        WeightAdviceDao weightAdviceDao = WeightAdviceDao.getInstance();
        WeightRecord entity = null;
        try {
            entity = WeightRecordDao.getInstance().selectLatestRecordByUserId(App.currentUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (entity!=null){
            WeightAdvice weightAdvice =  weightAdviceDao.findByWeight(entity.getWeight());
            String advice = weightAdvice!=null ? weightAdvice.getAdvice(): "暂无建议";
            System.out.println("============================");
            System.out.println("你好，用户：" + App.currentUser.getUsername());
            System.out.printf("\t你最新一次的登记体重时间为 %s，体重为 %f%n", entity.getRecordTime().toString(), entity.getWeight());
            System.out.println("\t建议如下:");
            System.out.println("\t" + advice);
            System.out.println("============================");
        }

        while (true) {

            displayMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    new WeightRecordFrame().loop();
                    break;
                case 2:
                    new ExerciseRecordsFrame().loop();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }

    public void displayMenu(){
        System.out.println("1. 体重信息管理");
        System.out.println("2. 运动信息管理");
        System.out.println("3. 返回上一级");
        System.out.print("请选择操作：");
    }
}

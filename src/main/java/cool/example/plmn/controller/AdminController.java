package cool.example.plmn.controller;

import cool.example.plmn.ui.FoodValueFrame;
import cool.example.plmn.ui.RecipeFrame;
import cool.example.plmn.ui.UserFrame;

import java.util.Scanner;

/**
 * @author wangshuo
 * @date 2023/5/15 16:58
 */

public class AdminController {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    new UserFrame().loop();
                    break;
                case 2:
                    new FoodValueFrame().loop();
                    break;
                case 3:
                    new RecipeFrame().loop();
                    break;
                case 4:
                    System.out.println("退出管理员模块");
                    return;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
        }
    }
    public void displayMenu(){
        System.out.println("----- 用户管理 -----");
        System.out.println("1. 用户管理");
        System.out.println("2. 食物管理");
        System.out.println("3. 食谱管理");
        System.out.println("4. 退出");
        System.out.print("请选择操作：");
    }
}

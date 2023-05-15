package cool.example.plmn.controller;


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
        while (true) {

            displayMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    return;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
        }
    }

    public void displayMenu(){
        System.out.println("1. 体重信息管理");
        System.out.println("2. 运动信息管理");
        System.out.println("3. 食谱查询");
        System.out.println("4. 食物查询");
        System.out.println("5. 返回上一级");
        System.out.print("请选择操作：");
    }
}

package cool.example.plmn.controller;

import java.util.Scanner;

/**
 * @author wangshuo
 * @date 2023/5/15 16:58
 */

public class AdminController {

    public AdminController() {
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
        }
    }

    public void menu1(){
        System.out.println("请选择操作：");
        System.out.println("1. 用户管理");
        System.out.println("2. 食物管理");
        System.out.println("3. 建议管理");
        System.out.println("4. 退出");
    }

    public void userManage(){
        System.out.println("1. 查询所有的用户");
        System.out.println("2. 添加用户");
        System.out.println("3. 删除用户");
        System.out.println("4. 更新用户");
    }
}

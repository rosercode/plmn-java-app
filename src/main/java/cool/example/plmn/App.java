package cool.example.plmn;


import cool.example.plmn.controller.AdminController;
import cool.example.plmn.controller.UserController;
import cool.example.plmn.entity.User;
import cool.example.plmn.ui.LoginFrame;
import cool.example.plmn.ui.RegisterFrame;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    // 当前登录用户
    public static User currentUser;

    public static void main(String[] args)  {
        start();
    }

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        while (true) {
            menu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    new LoginFrame().loop();
                    break;
                case 2:
                    new RegisterFrame().loop();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
            if ((choice == 1 || choice == 2) && currentUser !=null){
                if (currentUser.getRole() == 1){
                    new AdminController().start();
                }else if (currentUser.getRole() == 2){
                    new UserController().start();
                }
            }
        }
    }

    public static void menu(){
        System.out.println("1. 登录");
        System.out.println("2. 注册");
        System.out.println("3. 退出");
        System.out.print("请选择操作：");

    }
}

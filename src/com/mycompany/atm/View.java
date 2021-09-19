package com.mycompany.atm;

import java.util.Scanner;

public class View {
    public static boolean firstMenu(){
        do {
            try {
                System.out.println("1-авторизоваться\n2-выход");
                Scanner in = new Scanner(System.in);
                int choice = in.nextInt();
                switch (choice) {
                    case 1:
                        return false;
                    case 2:
                        return true;
                    default:
                        System.out.println("Вы выбрали несуществующую функцию.");
                }
            }
            catch (Exception e){
                System.out.println("Некорректный ввод!");
            }
        } while (true);
    }

    public static void afterAuthorizationMenu(Card card){
        boolean isOpenMenu=true;
        do{
            try {
                System.out.println("1-проверить баланс карты\n2-снять средства со счета\n3-пополнить баланс\n4-выход");
                Scanner menuIn = new Scanner(System.in);
                int choice = menuIn.nextInt();
                switch (choice) {
                    case 1:
                        card.account.checkBalance();
                        break;
                    case 2:
                        try {
                            System.out.print("Введите сумму: ");
                            Scanner takingMoneyIn = new Scanner(System.in);
                            double takingMoneyCount = takingMoneyIn.nextDouble();
                            card.account.takeMoney(takingMoneyCount);
                        } catch (Exception e) {
                            System.out.println("Данные введены некорректно. Возможно, вы ввели сумму в неверном формате.");
                        }
                        break;
                    case 3:
                        try {
                            System.out.print("Введите сумму: ");
                            Scanner addingMoneyIn = new Scanner(System.in);
                            double addingMoneyCount = addingMoneyIn.nextDouble();
                            card.account.addMoney(addingMoneyCount);
                        } catch (Exception e) {
                            System.out.println("Данные введены некорректно. Возможно, вы ввели сумму в неверном формате.");
                        }
                        break;
                    case 4:
                        isOpenMenu = false;
                        break;
                    default:
                        System.out.println("Вы выбрали несуществующую функцию.");
                }
            }
            catch (Exception e){
                System.out.println("Некорректный ввод!");
            }
        }while(isOpenMenu);
    }

    public static void blockNotification(String number){
        System.out.println("Карта "+ number +" была заблокирована на сутки!");
    }

    public static void unblockNotification(){
        System.out.println("Карта была разблокирована!");
    }

    public static void timeToUnblockNotification(long timeLeft){
        System.out.println("До разблокировки карты осталось " + String.format("%dд %dч %dмин %dсек",timeLeft/86400000,(timeLeft%86400000)/3600000,(timeLeft%3600000)/60000,(timeLeft%60000)/1000));
    }

    public static void incorrectCardNumberFormatNotification(){
        System.out.println("Введен неверный формат номера карты!");
    }

    public static void inputNotification(String value){
        System.out.print("Введите " + value + " карты: ");
    }

    public static void incorrectInputNotification(String value){
        System.out.println(value + " был введен некорректно!");
    }

    public static void wrongPinCodeNotification(){
        System.out.println("Неверный пин-код");
    }

    public static void absentCardNumberNotification(){
        System.out.println("Карта с таким номером отстутствует.");
    }

    public static void balanceOutput(double money){
        System.out.println("Ваш баланс составляет " + money);
    }

    public static void successfulAuthorizationNotification(){
        System.out.println("Авторизация прошла успешно.");
    }

    public static void successfulTakeMoneyNotification(double money){
        System.out.println("Вы успешно сняли со счета средства в размере " + money);
    }

    public static void notEnoughAccountMoneyNotification(double money){
        System.out.println("Сумма на текущем счете недостаточна для снятия средств в размере " + money);
    }

    public static void atmMoneyLimitExceededNotification(){
        System.out.println("Превышен лимит средств в банкомате!");
    }

    public static void successfulAddMoneyNotification(double money){
        System.out.println("Вы успешно пополнили баланс на сумму " + money);
    }

    public static void addMoneyLimitExceededNotification(){
        System.out.println("Вы превысили сумму пополнения баланса(сумма пополнения не должна превышать 1 000 000!)");
    }
}

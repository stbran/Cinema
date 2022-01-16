import java.util.*;

// package cinema;
public class Cinema {

    public static Scanner scanner = new Scanner(System.in);
    public static int rows;
    public static int seats;
    public static int row;
    public static int seat;
    public static String[] arr;
    public static boolean flag = true;
    public static int numOfPurchasedTic = 0;
    public static int currentIncome = 0;
    public static int totalIncome = 0;

    public static void requestForCapacity() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        fillArr();
    }

    private static void printNumberSeats() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void fillArr() {
        arr = new String[rows * seats + rows];//заполнение массива S-ками
        int j = 1;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || i %  (seats + 1) == 0) {
                arr[i] = Integer.toString(j);
                j++;
                i++;
            }
        arr[i] = "S";
        }
    }

    private static void ticketCost() {
        boolean fl = true;
        while (fl) {
            System.out.println("\nEnter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();
            if (row > rows || seat > seats) {
                System.out.println("\nWrong input!");
            } else if (arr[(row - 1) * (seats + 1) + seat] == "B") {
                System.out.println("\nThat ticket has already been purchased!");
            } else {
                fl = false;
                int price = 0;
                if (rows * seats <= 60) {
                    price = 10;
                } else {
                    if (row <= rows / 2) {
                        price = 10;
                    } else {
                        price = 8;
                    }
                }
                System.out.println();
                System.out.println("Ticket price: $" + price);
                numOfPurchasedTic += 1;
                currentIncome += price;
                markB();
            }
        }
    }

    private static void markB() {
        
        arr[(row - 1) * (seats + 1) + seat] = "B";
    }
    
    private static void calcTotalIncome() {
        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else {
            if (rows % 2 == 0) {
                totalIncome = rows * seats * 9;
            } else {
                totalIncome = (rows / 2 * 10  + (rows - rows / 2)  * 8) * seats;
            }
        }
    }

    public static void printCapacity() {
        System.out.println();
        printNumberSeats();
        for (int i = 0; i < arr.length; i++){ // печать зала
            if (i > 1 && i % (seats + 1) == 0) {
                System.out.println();
            }
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void statistics() {
        System.out.printf("Number of purchased tickets: %d\n", numOfPurchasedTic);
        float perc = (float) numOfPurchasedTic * 100 / (rows * seats);
        System.out.printf("Percentage: %.2f%%\n", perc);
        System.out.printf("Current income: $%d\n", currentIncome);
        calcTotalIncome();
        System.out.printf("Total income: $%d\n", totalIncome);
    }

    public static void printMenu() {
        System.out.println();
        String[] menu = {"1. Show the seats", "2. Buy a ticket", "3. Statistics", "0. Exit"};
        for (String elem : menu) {
            System.out.println(elem);
        }
        int a = scanner.nextInt();
        switch (a) {
            case 1:
                printCapacity();
                break;
            case 2:
                ticketCost();
                break;
            case 3:
                statistics();
                break;
            case 0:
                flag = false;
                break;
            default:
                System.out.println("Input correct number!");
                break;
        }
    }

    public static void main(String[] args) {
        requestForCapacity();
        while (flag) {
            printMenu();
        }
    }
}
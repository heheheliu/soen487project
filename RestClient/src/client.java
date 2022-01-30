import java.util.InputMismatchException;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        Scanner keyIn = new Scanner(System.in);
        int menuOption = 0;
        int submenuOption = 0;

        do {
            /**
             * display menu options
             */
            System.out.println("\nWhat do you want to do? (choose the menu number)" +
                    "");
            System.out.println("\t1. Artists");
            System.out.println("\t2. Albums");
            System.out.println("\t3. Quit");

            /**
             * accept user choice and make sure it's valid
             */
            boolean isInteger = false;
            try {
                menuOption = keyIn.nextInt();
                isInteger = true;
            } catch (InputMismatchException e) {
                System.out.println("You should enter an integer.");
                keyIn.nextLine();
            }
            if (isInteger == false) {
                continue;
            } else if (menuOption > 3 || menuOption < 1) {
                System.out.println("Invalid option! Please choose again.");
            } else {
                switch(menuOption) {
                    case 1:
                        do {
                            /**
                             * display Artist submenu
                             */
                            System.out.println("\nArtists menu:");
                            System.out.println("\t1. list artists");
                            System.out.println("\t2. get artist details");
                            System.out.println("\t3. add artist");
                            System.out.println("\t4. update artist");
                            System.out.println("\t5. delete artist");
                            System.out.println("\t6. go back");
                            /**
                             * accept user choice and make sure it's valid
                             */
                            boolean isIntSub1 = false;
                            try {
                                submenuOption = keyIn.nextInt();
                                isIntSub1 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("You should enter an integer.");
                                keyIn.nextLine();
                            }
                            if (isIntSub1 == false) {
                                continue;
                            } else if (submenuOption > 6 || submenuOption < 1) {
                                System.out.println("Invalid option! Please choose again.");
                            } else {
                                int exit = 0;
                                switch(submenuOption) {
                                    case 1:
                                        System.out.println("1");
                                        break;
                                    case 2:
                                        System.out.println("2");
                                        break;
                                    case 3:
                                        System.out.println("3");
                                        break;
                                    case 4:
                                        System.out.println("4");
                                        break;
                                    case 5:
                                        System.out.println("5");
                                        break;
                                    case 6:
                                        exit = 1;
                                        break;
                                }
                                if (exit == 1) {
                                    break;
                                }
                            }
                        } while (true);
                        break;
                    case 2:
                        do {
                            /**
                             * display Album submenu
                             */
                            System.out.println("\nAlbum menu");
                            System.out.println("\t1. list albums");
                            System.out.println("\t2. get album details");
                            System.out.println("\t3. add album");
                            System.out.println("\t4. update album info");
                            System.out.println("\t5. delete album");
                            System.out.println("\t6. go back");

                            boolean isIntSub2 = false;
                            try {
                                submenuOption = keyIn.nextInt();
                                isIntSub2 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("You should enter an integer.");
                                keyIn.nextLine();
                            }
                            if (isIntSub2 == false) {
                                continue;
                            } else if (submenuOption > 6 || submenuOption < 1) {
                                System.out.println("Invalid option! Please choose again.");
                            } else {
                                int exit = 0;
                                switch(submenuOption) {
                                    case 1:
                                        System.out.println("1");
                                        break;
                                    case 2:
                                        System.out.println("2");
                                        break;
                                    case 3:
                                        System.out.println("3");
                                        break;
                                    case 4:
                                        System.out.println("4");
                                        break;
                                    case 5:
                                        System.out.println("5");
                                        break;
                                    case 6:
                                        exit = 1;
                                        break;
                                }
                                if (exit == 1) {
                                    break;
                                }
                            }
                        } while (true);
                        break;
                    case 3:
                        System.out.println("Program terminated!");
                        System.exit(0);
                }
            }
        } while(true);
    }
}

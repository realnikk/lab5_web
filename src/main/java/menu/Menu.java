package menu;

import authorization.LogIn;
import authorization.SignUp;
import entity.User;

import java.util.Scanner;

public class Menu {
    public Menu() { }

    Scanner in = new Scanner(System.in);
    MenuFunctions menuFunctions = new MenuFunctions();

    public void IntroducingMenu() {
        LogIn logIn = new LogIn();
        SignUp signUp = new SignUp();
        String choice = "0";
        while (Integer.parseInt(choice) != 3) {
            String s = "Menu\n" +
                    "1. Authorization\n" +
                    "2. Registration\n" +
                    "3. Exit\n" +
                    "Your choice: ";
            System.out.print(s);
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    logIn.authorization();
                    break;
                case "2":
                    signUp.registration();
                    break;
                case "3":
                    System.out.println("Good bye!");
                    break;
                default:
                    System.out.println("Incorrect data!");
                    choice = "0";
                    break;
            }
        }
    }

    public void AdminMenu() {
        String choice = "0";
        while (Integer.parseInt(choice) != 4) {
            String s = "Admin menu\n" +
                    "1. Work with users\n" +
                    "2. Work with companies\n" +
                    "3. Work with cars\n" +
                    "4. Exit\n" +
                    "Your choice: ";
            System.out.print(s);
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    AdminMenuWithUsers();
                    break;
                case "2":
                    AdminMenuWithCompanies();
                    break;
                case "3":
                    AdminMenuWithCars();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Incorrect data!");
                    choice = "0";
                    break;
            }
        }
    }

    public void UserMenu(User currentUser) {
        String choice = "0";
        while (Integer.parseInt(choice) != 6) {
            String s = "User menu\n" +
                    "1. Show all companies\n" +
                    "2. Show all car models\n" +
                    "3. Search a company by name\n" +
                    "4. Search a car model by name\n" +
                    "5. Edit my profile\n" +
                    "6. Exit\n" +
                    "Your choice: ";
            System.out.print(s);
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    menuFunctions.showCompanies();
                    break;
                case "2":
                    menuFunctions.showCars();
                    break;
                case "3":
                    menuFunctions.showOneCompany();
                    break;
                case "4":
                    menuFunctions.findCarByName();
                    break;
                case "5":
                    menuFunctions.updateLoginAndPassword(currentUser);
                    break;
                case "6":
                    break;
                default:
                    System.out.println("Incorrect data!");
                    choice = "0";
                    break;
            }
        }
    }

    private void AdminMenuWithUsers() {
        String choice = "0";
        while (Integer.parseInt(choice) != 5) {
            String s = "Work with users\n" +
                    "1. Add user\n" +
                    "2. Edit user\n" +
                    "3. Delete user\n" +
                    "4. Show all users\n" +
                    "5. Exit\n" +
                    "Your choice: ";
            System.out.print(s);
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    menuFunctions.addPerson();
                    break;
                case "2":
                    menuFunctions.updatePerson();
                    break;
                case "3":
                    menuFunctions.deletePerson();
                    break;
                case "4":
                    menuFunctions.showPeople();
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Incorrect data!");
                    choice = "0";
                    break;
            }
        }
    }

    public void AdminMenuWithCompanies() {
        String choice = "0";
        while (Integer.parseInt(choice) != 5) {
            String s = "Work with companies\n" +
                    "1. Add company\n" +
                    "2. Change company\n" +
                    "3. Delete company\n" +
                    "4. Show all companies\n" +
                    "5. Exit\n" +
                    "Your choice: ";
            System.out.print(s);
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(menuFunctions.addCompany());
                    break;
                case "2":
                    menuFunctions.updateCompany();
                    break;
                case "3":
                    menuFunctions.deleteCompany();
                    break;
                case "4":
                    menuFunctions.showCompanies();
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Incorrect data!");
                    choice = "0";
                    break;
            }
        }
    }

    public void AdminMenuWithCars() {
        String choice = "0";
        while (Integer.parseInt(choice) != 7) {
            String s = "Work with cars\n" +
                    "1. Add car\n" +
                    "2. Edit car\n" +
                    "3. Delete car\n" +
                    "4. Show all cars\n" +
                    "5. Show all cars of this company\n" +
                    "6. Search car by name\n" +
                    "7. Exit\n" +
                    "Your choice: ";
            System.out.print(s);
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    menuFunctions.addCar();
                    break;
                case "2":
                    menuFunctions.updateCar();
                    break;
                case "3":
                    menuFunctions.deleteCar();
                    break;
                case "4":
                    menuFunctions.showCars();
                    break;
                case "5":
                    menuFunctions.showCarsFromOneCompany();
                    break;
                case "6":
                    menuFunctions.findCarByName();
                case "7":
                    break;
                default:
                    System.out.println("Incorrect data!");
                    choice = "0";
                    break;
            }
        }
    }
}

package authorization;

import entity.Person;
import entity.User;
import menu.Menu;
import service.PersonService;
import service.serviceImpl.PersonServiceImpl;

import java.util.List;
import java.util.Scanner;

public class LogIn {
    Scanner in = new Scanner(System.in);

    public void authorization() {
        PersonService personService = new PersonServiceImpl();
        List<Person> people = personService.showPeople();
        System.out.print("Input login: ");
        String login = in.nextLine();
        System.out.print("Input password: ");
        String password = in.nextLine();
        User currentUser = null;
        for(Person p : people) {
            if(p.getUser().getLogin().equals(login) && p.getUser().getPassword().equals(password)) {
                currentUser = p.getUser();
                p.setPersonId(people.size());
            }
            if (p.getUser().getLogin().equals(login) && !p.getUser().getPassword().equals(password)) {
                System.out.println("Incorrect password!");
            }
        }
        if (currentUser != null) {
            System.out.println("Authorization completed! Welcome " +
                    currentUser.getPerson().getSurname() + " " + currentUser.getPerson().getName());
            Menu menu = new Menu();
            String role = currentUser.getRole();
            if(role == "Admin"){
                menu.AdminMenu();
            } else {
                menu.UserMenu(currentUser);
            }
            /*switch (role) {
                case "Admin":
                    menu.AdminMenu();
                    break;
                case "User":
                    menu.UserMenu(currentUser);
                    break;
            }*/
        }
        else {
            System.out.println("This user doesn't exist");
        }
    }
}

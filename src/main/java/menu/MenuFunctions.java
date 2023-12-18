package menu;

import entity.Car;
import entity.Company;
import entity.Person;
import entity.User;
import service.CompanyService;
import service.PersonService;
import service.serviceImpl.CompanyServiceImpl;
import service.serviceImpl.PersonServiceImpl;
//import validator.Validator;

import java.util.List;
import java.util.Scanner;

public class MenuFunctions {
    Scanner in = new Scanner(System.in);
    PersonService personService = new PersonServiceImpl();
    CompanyService companyService = new CompanyServiceImpl();

    public MenuFunctions() { }

    //---PERSON AND USER---//

    public void addPerson() {
        System.out.println("---Add user---");
        Person person = getPersonInfo();
        if (person != null) {
            if (personService.addPerson(person)) {
                System.out.println("---User was added!---");
            }
        }
    }

    private Person getPersonInfo() {
        Person person = null;
        System.out.print("Input user name: ");
        String name = in.nextLine();
        System.out.print("Input user surname: ");
        String surname = in.nextLine();
        System.out.print("Input user age: ");
        String age = in.nextLine();
        System.out.print("Input user phone: ");
        String phone = in.nextLine();
        System.out.print("Input user mail: ");
        String mail = in.nextLine();
        //if (Validator.correctPerson(name, surname, age, phone, mail)) {
        User user = getUserInfo();
        if (user != null) {
            person = new Person(name, surname, Integer.parseInt(age), phone, mail);
            user.setPerson(person);
            person.setUser(user);
        }
        else {
            System.out.println("Password and login doesn't exist!");
        }
      /*  }
        else {
            System.out.println("Личные данные не корректны!");
        }*/
        return person;
    }

    private User getUserInfo() {
        User user = null;
        System.out.print("Input user login: ");
        String login = in.nextLine();
        System.out.print("Input user password: ");
        String password = in.nextLine();
        //  if(Validator.correctUser(login, password)) {
        if(checkUniqueLogin(login)) {
            user = new User(login, password, "User");
        }
        else {
            System.out.println("This login has already exist!");
        }
        //}
        return user;
    }

    private boolean checkUniqueLogin(String login) {
        boolean isUnique = true;
        for (Person p : getPeople()) {
            if (p.getUser().getLogin().equals(login)) {
                isUnique = false;
            }
        }
        return isUnique;
    }

    public void updatePerson() {
        System.out.println("---Edit user---");
        showPeople();
        System.out.print("Choose user by ID: ");
        String id = in.nextLine();
        if (getPersonId(id)) {
            Person person = findPersonById(Integer.parseInt(id));
            if (person != null) {
                changeDataFromPerson(person);
                changeDataFromUser(person);
                if (personService.updatePerson(person)) {
                    System.out.println("---Changes was saved!---");
                }
            }
        }
    }

    private Person changeDataFromPerson(Person person) {
        System.out.print("Input user name: ");
        String name = in.nextLine();
        System.out.print("Input user surname: ");
        String surname = in.nextLine();
        System.out.print("Input user age: ");
        String age = in.nextLine();
        System.out.print("Input user phone: ");
        String phone = in.nextLine();
        System.out.print("Input user mail: ");
        String mail = in.nextLine();
        //  if (Validator.correctPerson(name, surname, age, phone, mail)) {
        person.setName(name);
        person.setSurname(surname);
        person.setAge(Integer.parseInt(age));
        person.setPhone(phone);
        person.setMail(mail);
       /* }
        else {
            System.out.println("Личные данные не корректны!");
        }*/
        return person;
    }

    private Person changeDataFromUser(Person person) {

        System.out.print("Input user login: ");
        String login = in.nextLine();
        System.out.print("Input user password: ");
        String password = in.nextLine();
        //   if(Validator.correctUser(login, password)) {
        person.getUser().setLogin(login);
        person.getUser().setPassword(password);
        // }
        return person;
    }

    public void updateLoginAndPassword(User user) {
        System.out.println("---Change login and password---");
        changeDataFromUser(user.getPerson());
        if (personService.updatePerson(user.getPerson())) {
            System.out.println("---Changes was saved!---");
        }

    }

    public void deletePerson() {
        System.out.println("---Delete user---");
        showPeople();
        System.out.print("Choose user ID: ");
        String id = in.nextLine();
        if (getPersonId(id)) {
            if (personService.deletePerson(Integer.parseInt(id))) {
                System.out.println("---User was deleted!---");
            }
        }
    }

    private boolean getPersonId(String id) {
        boolean isAppropriateNumber = false;
        //   if (Validator.correctId(id)) {
        if (!(Integer.parseInt(id) < 0) && !(Integer.parseInt(id) > getPeople().size())) {
            isAppropriateNumber = true;
        }
        else {
            System.out.println("This ID doesn't exist!");
        }
       /* }
        else {
            System.out.println("ID не корректно!");
        }*/
        return isAppropriateNumber;
    }

    public void showPeople() {
        List<Person> people = getPeople();
        if (people.size() != 0) {
            System.out.format("%10s%20s%20s%10s%20s%30s%20s", "ID |", "Name |", "Surname |", "Age |", "Phone |", "Mail |", "Login |");
            for (Person p: people) {
                System.out.println(" ");
                System.out.format("%10s%20s%20s%10s%20s%30s%20s", p.getPersonId() + " |", p.getName() + " |",
                        p.getSurname() + " |", p.getAge() + " |",
                        p.getPhone() + " |", p.getMail() + " |", p.getUser().getLogin() + " |");

            }
            System.out.println(" ");
        }
        else {
            System.out.println("No users!");
        }
    }

    private List<Person> getPeople() {
        List<Person> people = personService.showPeople();
        return people;
    }

    private Person findPersonById(int id) {
        Person person = personService.findPersonById(id);
        return person;
    }

    //---COMPANY---//

    public String addCompany() {
        System.out.println("---Add company---");
        String result = null;
        System.out.print("Input company name: ");
        String name = in.nextLine();
        System.out.print("Input country were this company was created: ");
        String country = in.nextLine();
        //  if (Validator.correctCompany(name, country)) {
        Company company = new Company(name, country);
        if (companyService.addCompany(company)) {
            result = "---Company was added!---";
        }
      /*  }
        else {
            result = "Данные не корректны!";
        }*/
        return result;
    }

    public String updateCompany() {
        String result = null;
        System.out.println("---Edit company---");
        showCompanies();
        System.out.print("Choose company ID: ");
        String id = in.nextLine();
        if (getCompanyId(id)) {
            System.out.print("Input company name: ");
            String name = in.nextLine();
            System.out.print("Input country were this company was created: ");
            String country = in.nextLine();
            //     if (Validator.correctCompany(name, country)) {
            Company company = companyService.findCompanyById(Integer.parseInt(id));
            company.setCompanyName(name);
            company.setCompanyCountry(country);
            if (companyService.updateCompany(company)) {
                System.out.println("---Changes was saved!---");
            }
          /*  }
            else {
                System.out.println("Данные не корректны!");
            }*/
        }

        return result;
    }

    public void deleteCompany() {
        System.out.println("---Delete company---");
        showCompanies();
        System.out.print("Input company ID: ");
        String id = in.nextLine();
        if (getCompanyId(id)) {
            if(companyService.deleteCompany(Integer.parseInt(id))) {
                System.out.println("---Company was deleted!---");
            }
        }
    }

    private boolean getCompanyId(String id) {
        boolean isAppropriateNumber = false;
        //   if (Validator.correctId(id)) {
        if (!(Integer.parseInt(id) < 0) && !(Integer.parseInt(id) > getCompanies().size())) {
            isAppropriateNumber = true;
        }
        else {
            System.out.println("This ID doesn't exist!");
        }
       /* }
        else {
            System.out.println("ID не корректно!");
        }*/
        return isAppropriateNumber;
    }

    public void showCompanies() {
        List<Company> companies = getCompanies();
        if (companies.size() != 0) {
            theHeaderForCompany();
            for (Company c: companies) {
                theTableForCompany(c);
            }
            System.out.println(" ");
        }
        else {
            System.out.println("No companies!");
        }
    }

    private void theTableForCompany(Company c) {
        System.out.println(" ");
        System.out.format("%10s%20s%30s", c.getCompanyId() + " |", c.getCompanyName() + " |", c.getCompanyCountry() + " |");
        System.out.println(" ");
    }

    private void theHeaderForCompany() {
        System.out.format("%10s%20s%30s"," ID |", "Name |", "Country of origin |");
    }

    private List<Company> getCompanies() {
        List<Company> companies = companyService.showCompanies();
        return companies;
    }

    public void showOneCompany() {
        Company company = findCompanyByName();
        if (company != null) {
            theHeaderForCompany();
            theTableForCompany(company);
        }
    }

    private Company findCompanyByName() {
        System.out.print("Input company name: ");
        String name = in.nextLine();
        boolean isFound = false;
        for (Company c : getCompanies()) {
            if (c.getCompanyName().equals(name)) {
                isFound = true;
            }
        }
        Company company = null;
        if (isFound) {
            company = companyService.findCompanyByName(name);
        }
        else {
            System.out.println("This company doesn't exist!");
        }
        return company;
    }

    //---CAR---//

    public void addCar() {
        System.out.println("---Add car---");
        showCompanies();
        System.out.print("Choose company ID for car: ");
        String id = in.nextLine();
        Car car = getCarInfo();
        if (car != null) {
            Company company = companyService.findCompanyById(Integer.parseInt(id));
            car.setCompany(company);
            company.addCar(car);
            if (companyService.updateCompany(company)) {
                System.out.println("---Car was added!---");
            }
        }
    }

    private Car getCarInfo() {
        System.out.print("Input name: ");
        String name = in.nextLine();
        System.out.print("Input manufacture year: ");
        String year = in.nextLine();
        System.out.print("Input mileage: ");
        String distance = in.nextLine();
        System.out.print("Input type of fuel: ");
        String fuel = in.nextLine();
        System.out.print("Input expenses: ");
        String fuelConsumption = in.nextLine();
        System.out.print("Input price: ");
        String price = in.nextLine();
        // if (Validator.correctCar(name, year, distance, fuel, fuelConsumption, price)) {
        //     if (Validator.correctFuel(fuel)) {
        Car car = new Car();
        car.setName(name);
        car.setYear(Integer.parseInt(year));
        car.setDistance(Integer.parseInt(distance));
        car.setFuel(fuel);
        car.setFuelConsumption(fuelConsumption);
        car.setPrice(Integer.parseInt(price));
        return car;
          /*  }
            else {
                System.out.println("Введите топливо: Бензин или Дизель!");
            }*/
       /* }
        else {
            System.out.println("Данные не корректны!");
        }*/
    }

    public void deleteCar() {
        System.out.println("---Delete car---");
        showCars();
        Company company = findCompanyByName();
        if (company != null) {
            System.out.print("Input car name: ");
            String name = in.nextLine();
            Car car = findCarInList(company, name);
            if (car != null) {
                company.getCars().remove(car);
                if (companyService.updateCompany(company)) {
                    System.out.println("---Car was deleted!---");
                }
            }
        }
    }

    public void updateCar() {
        System.out.println("---Edit car---");
        showCars();
        Company company = findCompanyByName();
        if (company != null) {
            System.out.print("Input car name: ");
            String nameForChange = in.nextLine();
            Car car = findCarInList(company, nameForChange);
            if (car != null) {
                System.out.print("Input name: ");
                String name = in.nextLine();
                System.out.print("Input manufacture year: ");
                String year = in.nextLine();
                System.out.print("Input mileage: ");
                String distance = in.nextLine();
                System.out.print("Input type of fuel: ");
                String fuel = in.nextLine();
                System.out.print("Input expenses: ");
                String fuelConsumption = in.nextLine();
                System.out.print("Input price: ");
                String price = in.nextLine();
              /*  if (Validator.correctCar(name, year, distance, fuel, fuelConsumption, price)) {
                    if (Validator.correctFuel(fuel)) {*/
                car.setName(name);
                car.setYear(Integer.parseInt(year));
                car.setDistance(Integer.parseInt(distance));
                car.setFuel(fuel);
                car.setFuelConsumption(fuelConsumption);
                car.setPrice(Integer.parseInt(price));
            }
            else {
                System.out.println("Input fuel type: Gasoline or Diesel!");
            }
               /* }
                else {
                    System.out.println("Данные не корректны!");
                }
                if (companyService.updateCompany(company)) {
                    System.out.println("---Изменение выполнено!---");
                }*/
            // }
        }
    }

    public void findCarByName() {
        System.out.print("Input car name: ");
        String name = in.nextLine();
        Car car = null;
        for (Company company : getCompanies()) {
            car = findCarInList(company, name);
            if (car != null) {
                theHeaderForCar();
                theTableForCar(car);
            }
        }
        System.out.println(" ");
    }

    private Car findCarInList(Company company, String name) {
        Car car = null;
        if (!company.getCars().isEmpty()) {
            for (Car c : company.getCars()) {
                if (c.getName().equals(name)) {
                    car = c;
                }
            }
            if (car == null) {
                System.out.println("This car doesn't exist in company " + company.getCompanyName());
            }

        }
        return car;
    }

    public void showCarsFromOneCompany() {
        showCompanies();
        Company company = findCompanyByName();
        if (company != null) {
            if (!company.getCars().isEmpty()) {
                theHeaderForCar();
                for (Car c : company.getCars()) {
                    theTableForCar(c);
                }
                System.out.println(" ");
            }
            else {
                System.out.println("Company " + company.getCompanyName() + " has no models!");
            }
        }
    }

    public void showCars() {
        List<Company> companies = getCompanies();
        if (companies.size() != 0) {
            theHeaderForCar();
            for (Company c: companies) {
                List<Car> cars = c.getCars();
                if (!cars.isEmpty()) {
                    for (Car car : cars) {
                        theTableForCar(car);
                    }
                    System.out.println(" ");
                }

            }
        }
        else {
            System.out.println("No cars!");
        }
    }

    private void theHeaderForCar() {
        System.out.format("%15s%20s%10s%10s%10s%15s%10s%20s", "ID |", "Name |", "Manufacture year |", "Mileage |", "Fuel |",
                "Fuel consumption |", "Price |", " Company|");
    }

    private void theTableForCar(Car car) {
        System.out.println(" ");
        System.out.format("%15s%20s%10s%10s%10s%16s%10s%20s", car.getCarId() + " |", car.getName() + " |",
                car.getYear() + " |", car.getDistance() + " |", car.getFuel() + " |",
                car.getFuelConsumption() + " |", car.getPrice() + " |", car.getCompany().getCompanyName() + " |");
    }
}

# SdpFinalProject
  
 
 



 
Dodo Pizza
Final Project
“Software Design Patterns” course
 




 
Group: SE-2220

Team members: Darkhan Omirbay,
Rustem Zhakup.
Temirlan Daulet.


Teaching Assistant: Zhanbolat Mansur. 





 
Astana, 2023
Project Overview
Describe the idea of the project.
Our project a pizza ordering system implemented in Java. It includes various design patterns like Strategy, Singleton, Decorator, Adapter, Observer, and Factory patterns. 
The purpose of the work. 
The purpose of this project is to demonstrate the implementation of different design patterns in a practical application, specifically in the context of a pizza ordering system.
The objectives of the work.
The objectives may include creating a functional pizza ordering system that allows users to select pizza types, add toppings, make payments, and receive notifications when their orders are ready.
UML Diagram
Add a UML diagram to show the structure of your project. Add a GitHub link.
Link of UML diagram: https://t.ly/koHN3
GitHub link: https://github.com/DarkhanOmirbay/SdpFinalProject.git
 

Main body
Strategy pattern:
The Strategy Pattern is a behavioral design pattern that allows you to define a family of algorithms, encapsulate each one of them, and make them interchangeable. In our code, you have applied the Strategy Pattern in the context of payment methods for ordering pizzas.

Our project have interface PaymentStrategy.In this interface have method pay();
We have two types of payment.These is “Credit Card Payment” and “Cash Payment”.
We created two classes “Credit Card Payment” and “Cash Payment” and implemented interface PaymentStrategy and override method pay(); and write some logic for class.
// Strategy Pattern
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;

    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid tenge " + amount + " using credit card ending in " + cardNumber);
    }
}

class CashPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid tenge " + amount + " in cash.");
    }
}

Singleton pattern:
The Singleton Pattern is a creational design pattern that ensures a class has only one instance and provides a global point of access to that instance. In our code, the DODOPizza class is a singleton, which means that there can only be one instance of this class throughout the application's lifecycle.
// Singleton Pattern
class DODOPizza {
    private static DODOPizza  instance;

    private DODOPizza () {
    }

    public static DODOPizza  getInstance() {
        if (instance == null) {
            instance = new DODOPizza ();
        }
        return instance;
    }

    public void servePizza(String pizzaType) {
        System.out.println("Serving a " + pizzaType + " pizza");
    }
}

Decorator pattern:
The Decorator Pattern is a structural design pattern that allows behavior to be added to an individual object, either statically or dynamically, without affecting the behavior of other objects from the same class. In our code, we've applied the Decorator Pattern to enhance the functionality of pizza objects.
// Decorator Pattern
abstract class Pizza {
    String description = "Unknown Pizza";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}

class Margherita extends Pizza{
    public Margherita() {
        description = "Margherita";
    }

    public double cost() {
        return 1590;
    }
}
class Pepperoni extends Pizza {
    public Pepperoni() {
        description = "Pepperoni";
    }

    public double cost() {
        return 1790;
    }
}
class FourSeasons extends Pizza {
    public FourSeasons() {
        description = "FourSeasons";
    }

    public double cost() {
        return 2090;
    }
}

class CheeseDecorator extends Pizza {
    private Pizza pizza;

    public CheeseDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    public double cost() {
        return pizza.cost() + 200;
    }
}

Adapter pattern:
The Adapter Pattern is a structural design pattern that allows the interface of one class to be used as another interface. It is often used when the interface of an existing class does not match the interface expected by the client code. In our code, we've applied the Adapter Pattern to adapt the Pizza Oven class for use as a Payment Strategy.
// Adapter Pattern
class PizzaOven {
    public void bakePizza(String pizzaType) {
        System.out.println("Baking a " + pizzaType + " pizza");
    }
}


class PizzaAdapter implements PaymentStrategy {
    private PizzaOven pizzaOven;
    private String pizzaType;

    public PizzaAdapter(PizzaOven pizzaOven, String pizzaType) {
        this.pizzaOven=pizzaOven;
        this.pizzaType =pizzaType;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " for a pizza " + pizzaType);
        pizzaOven.bakePizza(pizzaType);
    }
}

Observer pattern:
The Observer Pattern is a behavioral design pattern that defines a one-to-many dependency between objects. It allows one object, known as the subject or publisher, to notify multiple dependent objects, known as observers or subscribers, about state changes without them being tightly coupled. In our code, we've applied the Observer Pattern to notify customers when their pizza orders are ready.
// Observer Pattern
interface OrderObserver {
    void update(Order order);
}

class Customer implements OrderObserver {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(Order order) {
        System.out.println("Hi, " + name + "! Your order is ready: " + order.getDescription());
    }
}

class Order {
    private List<OrderObserver> observers = new ArrayList<>();
    private String description;

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }
}

Factory pattern:
The Factory Pattern is a creational design pattern that provides an interface for creating objects, but it allows subclasses to alter the type of objects that will be created. In our code, we've applied the Factory Pattern to create different types of pizzas based on the user's choice.

// Factory Pattern
class PizzaFactory {
    public Pizza createPizza(String type) {
        switch (type) {
            case "Margherita":
                return new Margherita();
            case "Pepperoni":
                return new Pepperoni();

            case "FourSeasons":
                return new FourSeasons();

            default:
                System.out.println("We don't have this type of pizza");
        }
        return null;
    }
}


Our user-friendly command line interface:
public class PizzaApp {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
           PizzaFactory pizzaFactory = new PizzaFactory();
           DrinkFactory drinkFactory = new DrinkFactory();

           System.out.println("Welcome to the DoDo pizza!");

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Order pizza and drink");
                System.out.println("2. Exit");
                System.out.print("Select an option:\n");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("\nPizza Menu:");
                    System.out.println("1. Margherita");
                    System.out.println("2. Pepperoni");
                    System.out.println("3. FourSeasons");

                    System.out.print("Select a pizza type:\n");

                    int pizzaChoice = scanner.nextInt();
                    System.out.println("Do you want to add cheese? it costs 200 tenge\n" +
                            "1.Yes!\n" +
                            "2.No!");
                    int YN= scanner.nextInt();
                    System.out.println("\nDrink Menu:");
                    System.out.println("1. Coca-cola");
                    System.out.println("2. Sprite");
                    System.out.println("3. Fanta");
                    System.out.print("Select a drink type:\n");
                    int drinkChoice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String customerName = scanner.nextLine();
                    Pizza pizza = null;
                    String pizzaType = "";
                    Drink drink = null;
                    String drinkType="";
                    switch (pizzaChoice) {
                        case 1:
                            if(YN==1) {
                                pizza = new CheeseDecorator(pizzaFactory.createPizza("Margherita"));
                                pizzaType = "Margherita";
                                break;
                            }else if (YN==2){
                                pizza = pizzaFactory.createPizza("Margherita");
                                pizzaType = "Margherita";
                                break;
                            }else{
                                System.out.println("error");
                            }
                        case 2:
                            if(YN==1) {
                                pizza = new CheeseDecorator(pizzaFactory.createPizza("Pepperoni"));
                                pizzaType = "Pepperoni";
                                break;
                            }else if (YN==2){
                                pizza = pizzaFactory.createPizza("Pepperoni");
                                pizzaType = "Pepperoni";
                                break;
                            }else{
                                System.out.println("error");
                            }
                        case 3:
                            if(YN==1) {
                                pizza = new CheeseDecorator(pizzaFactory.createPizza("FourSeasons"));
                                pizzaType = "FourSeasons";
                                break;
                            }else if (YN==2){
                                pizza = pizzaFactory.createPizza("FourSeasons");
                                pizzaType = "FourSeasons";
                                break;
                            }else{
                                System.out.println("error");
                            }
                        default:
                            System.out.println("Invalid pizza selection.");
                            continue;
                    }
                    switch (drinkChoice) {
                        case 1:
                           drink = drinkFactory.createDrink("Coca-cola");
                            drinkType = "Coca-cola";
                            break;
                        case 2:
                            drink = drinkFactory.createDrink("Sprite");
                            drinkType = "Sprite";
                            break;
                        case 3:
                            drink = drinkFactory.createDrink("Fanta");
                            drinkType = "Fanta";
                            break;
                        default:
                            System.out.println("Invalid drink selection.");
                            continue;
                    }
                    System.out.println("Ordering " + pizzaType +" and "+ drinkType + " for " + customerName);
                    Order order = new Order();
                    order.addObserver(new Customer(customerName));
                    order.setDescription(pizza.getDescription());
                    order.setDescription(drink.getDescription());
                    int costOFPizzaAndDrink = (int) (pizza.cost()+drink.cost());
                    System.out.println("Total cost: " + costOFPizzaAndDrink+" tenge");
                    // Payment
                    System.out.println("\nPayment Options:");
                    System.out.println("1. Credit Card");
                    System.out.println("2. Cash");
                    System.out.print("Select a payment method: ");
                    int paymentChoice = scanner.nextInt();
                    PaymentStrategy paymentStrategy = null;
                    switch (paymentChoice) {
                        case 1:
                            System.out.print("Enter credit card number: ");
                            String cardNumber = scanner.next();
                            paymentStrategy = new CreditCardPayment(cardNumber, customerName);
                            break;
                        case 2:
                            paymentStrategy = new CashPayment();
                            break;
                        default:
                            System.out.println("Invalid payment selection.");
                            continue;
                    }
                    paymentStrategy.pay((int) pizza.cost()+(int)drink.cost());
                    System.out.println("Thank you for your order!");

                } else if (choice == 2) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }

Maintaining a well-structured and readable codebase is essential in software development. To achieve this, it's crucial to adhere to certain guiding principles that promote code quality and maintainability. Our project code should strictly follow the principles of SOLID, DRY (Don't Repeat Yourself), and KISS (Keep It Simple, Stupid).

SOLID Principles:
SOLID is an acronym that represents a set of five principles for designing maintainable and scalable software. These principles include Single Responsibility (each class should have one reason to change), Open-Closed (open for extension but closed for modification), Liskov Substitution (subtypes should be substitutable for their base types), Interface Segregation (clients should not be forced to depend on interfaces they do not use), and Dependency Inversion (high-level modules should not depend on low-level modules; both should depend on abstractions).

By adhering to SOLID principles, we ensure that our codebase is modular, adaptable, and easy to extend without introducing unintended side effects.

DRY (Don't Repeat Yourself):
The DRY principle advocates for the elimination of redundancy in code. It emphasizes the importance of reusing code by abstracting common functionality into reusable components. This reduces the risk of errors, simplifies maintenance, and keeps the codebase concise.

In our project, we strive to eliminate duplicate code and encapsulate shared functionality in a central location, promoting code reusability and minimizing the need for redundant implementations.

KISS (Keep It Simple, Stupid):
KISS encourages simplicity in design and development. It suggests that the most straightforward solution is often the most effective. Complex code can be challenging to understand, maintain, and debug. By keeping our codebase simple and straightforward, we improve its readability and reduce the likelihood of introducing errors.

Our project aims to embrace the KISS principle by prioritizing clear and concise code that is easily comprehensible and maintainable.

In conclusion, adhering to these principles is integral to the success of our project. By following SOLID, DRY, and KISS, we ensure that our codebase remains clean, efficient, and adaptable. This approach not only benefits the development process but also contributes to a robust and maintainable software system."
Conclusion
In our "Dodo Pizza" project, we have successfully implemented several software design patterns to create a functional pizza ordering system. These patterns have allowed us to structure our code efficiently, enhance modularity, and promote reusability.
Key points of your project. Mention used patterns.
Design Patterns: We have applied various design patterns in our project, including the Strategy Pattern for payment methods, the Singleton Pattern for controlling the pizza service, the Decorator Pattern for customizing pizzas, the Adapter Pattern to adapt payment and baking functionalities, and the Observer Pattern for order notifications.
Pizza Ordering System: Our project is a practical implementation of a pizza ordering system that allows customers to select different types of pizzas, customize them with toppings, choose payment methods, and receive notifications when their orders are ready.
Project outcomes. Challenges faced. 
Project Outcomes:
Successfully demonstrated the usage of multiple design patterns in a real-world application.
Created a user-friendly and modular pizza ordering system that provides flexibility for adding new pizza types and payment methods.
Challenges Faced:
Managing the integration of multiple design patterns and ensuring they work seamlessly together.
Handling exceptions and user input validation to provide a robust user experience.
Insights:
Design patterns are essential for creating scalable and maintainable software systems.
The Observer Pattern is valuable for handling notifications and updates in a decoupled manner, enhancing user experience.
Future Improvements:
Enhance the user interface to provide a more user-friendly experience.
Expand the menu with more pizza types and additional customizations.
Implement a database to store order history and customer information for better tracking.
References
Refactoring Guru. (n.d.). Design Patterns. https://refactoring.guru/ru

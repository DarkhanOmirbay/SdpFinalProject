package DodoPizzaFinalProject;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println("Paid " + amount + " tenge using credit card ending in " + cardNumber);
    }
}

class CashPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " tenge in cash.");
    }
}

// Singleton Pattern
class DODOPizza {
    private static DODOPizza instance;

    private DODOPizza () {
    }

    public static DODOPizza getInstance() {
        if (instance == null) {
            instance = new DODOPizza();
        }
        return instance;
    }

    public void servePizza(String pizzaType) {
        System.out.println("Serving a " + pizzaType + " pizza");
    }
}

// Decorator Pattern
abstract class Pizza {
    String description = "Unknown Pizza";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}

class Margherita extends Pizza {
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
        return pizza.getDescription() + " with added Cheese";
    }

    public double cost() {
        return pizza.cost() + 200;
    }
}
abstract class Drink{
    String description = "Unknown Drink";
    public String getDescription(){
        return description;
    }
    public abstract double cost();

}
class CocaCola extends Drink {
    public CocaCola(){
        description = "Coca-cola";
    }
    @Override
    public double cost() {
        return 500;
    }
}
class Sprite extends Drink {
    public Sprite(){
        description = "Sprite";
    }
    @Override
    public double cost() {
        return 500;
    }
}
class Fanta extends Drink {
    public Fanta(){
        description = "Fanta";
    }
    @Override
    public double cost() {
        return 500;
    }
}

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
        System.out.println(name + "! Your order is ready: " + order.getDescription());
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
class DrinkFactory{
    public Drink createDrink(String type){
        switch (type) {
            case "Coca-cola":
                return new CocaCola();
            case "Sprite":
                return new Sprite();

            case "Fanta":
                return new Fanta();

            default:
                System.out.println("We don't have this type of drink");
        }
        return null;
    }
}
//Strategy Pattern
//        PaymentStrategy paymentStrategy = new CreditCardPayment("1234-5678-9012-3456", "Magzhan");
//        paymentStrategy.pay(2090);

// Singleton Pattern
//        DODOPizza dodoPizza = DODOPizza.getInstance();
//        dodoPizza.servePizza("Pepperoni");
//        DODOPizza dodoPizza1=DODOPizza.getInstance();
//        System.out.println(dodoPizza.toString());
//        System.out.println(dodoPizza1.toString());
//        dodoPizza1.servePizza("Margharita");
//
//        // Decorator Pattern
//        Pizza pizza = new Pepperoni();
//        pizza = new CheeseDecorator(pizza);
//        System.out.println("Ordered: " + pizza.getDescription() + " for tenge " + pizza.cost());
//
//        // Adapter Pattern
//        PizzaOven pizzaOven = new PizzaOven();
//        PaymentStrategy pizzaPayment = new PizzaAdapter(pizzaOven, "Pepperoni");
//        pizzaPayment.pay(2090);
//
// Observer Pattern
//        Customer customer1 = new Customer("Alice");
//        Customer customer2 = new Customer("Bob");
//
//        Order order = new Order();
//        order.addObserver(customer1);
//        order.addObserver(customer2);
//        order.setDescription("Pepperoni");
//
//
//
//        // Factory Pattern
//        PizzaFactory pizzaFactory = new PizzaFactory();
//        Pizza pizzaFromFactory = pizzaFactory.createPizza("Pepperoni");
//        System.out.println("Factory created: " + pizzaFromFactory.getDescription() + " for tenge " + pizzaFromFactory.cost());




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
    }
}


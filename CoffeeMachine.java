package machine;
import java.util.*;

enum State {
    CHOOSING_ACTION,
    CHOOSING_COFFEE,
    FILLING_WATER,
    FILLING_MILK,
    FILLING_BEANS,
    FILLING_CUPS,
    EXIT
}

public class CoffeeMachine {
    private int money;
    private int mlsOfWater;
    private int mlsOfMilk;
    private int gramsOfCoffee;
    private int cups;
    private State state;

    public CoffeeMachine(int money, int mlsOfWater, int mlsOfMilk, int gramsOfCoffee, int cups) {
        this.money = money;
        this.mlsOfWater = mlsOfWater;
        this.mlsOfMilk = mlsOfMilk;
        this.gramsOfCoffee = gramsOfCoffee;
        this.cups = cups;
        this.state = State.CHOOSING_ACTION;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void printStatus() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(mlsOfWater + " of water");
        System.out.println(mlsOfMilk + " of milk");
        System.out.println(gramsOfCoffee + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money\n");
    }

    public void buy(String choice) {
        switch (choice) {
            case "1":
                if (mlsOfWater < 250) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (gramsOfCoffee < 16) {
                    System.out.println("Sorry, not enough coffee beans!\n");
                } else if (cups == 0) {
                    System.out.println("Sorry, not enough disposable cups!\n");
                } else {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    addMoney(4);
                    mlsOfWater -= 250;
                    gramsOfCoffee -= 16;
                    cups--;
                }
                break;
            case "2":
                if (mlsOfWater < 350) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (mlsOfMilk < 75) {
                    System.out.println("Sorry, not enough milk!\n");
                } else if (gramsOfCoffee < 20) {
                    System.out.println("Sorry, not enough coffee beans!\n");
                } else if (cups == 0) {
                    System.out.println("Sorry, not enough disposable cups!\n");
                } else {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    addMoney(7);
                    mlsOfWater -= 350;
                    mlsOfMilk -= 75;
                    gramsOfCoffee -= 20;
                    cups--;
                }
                break;
            case "3":
                if (mlsOfWater < 200) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (mlsOfMilk < 100) {
                    System.out.println("Sorry, not enough milk!\n");
                } else if (gramsOfCoffee < 12) {
                    System.out.println("Sorry, not enough coffee beans!\n");
                } else if (cups == 0) {
                    System.out.println("Sorry, not enough disposable cups!\n");
                } else {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    addMoney(6);
                    mlsOfWater -= 200;
                    mlsOfMilk -= 100;
                    gramsOfCoffee -= 12;
                    cups--;
                }
                break;
            case "back":
                System.out.println();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void fillWater(int mlsOfWater) {
        this.mlsOfWater += mlsOfWater;
    }

    public void fillMilk(int mlsOfMilk) {
        this.mlsOfMilk += mlsOfMilk;
    }

    public void fillCoffeeBeans(int gramsOfCoffee) {
        this.gramsOfCoffee += gramsOfCoffee;
    }

    public void fillCups(int cups) {
        this.cups += cups;
    }

    public void take() {
        System.out.println("I gave you $" + money + "\n");
        money = 0;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void chooseAction(String input) {
        switch(state) {
            case CHOOSING_ACTION:
                switch(input) {
                    case "buy":
                        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        state = State.CHOOSING_COFFEE;
                        break;
                    case "fill":
                        System.out.println("\nWrite how many ml of water do you want to add:");
                        state = State.FILLING_WATER;
                        break;
                    case "take":
                        System.out.println();
                        take();
                        break;
                    case "remaining":
                        printStatus();
                        break;
                    case "exit":
                        setState(State.EXIT);
                        break;
                    default:
                        break;
                }
                break;
            case CHOOSING_COFFEE:
                buy(input);
                state = State.CHOOSING_ACTION;
                break;
            case FILLING_WATER:
                fillWater(Integer.parseInt(input));
                System.out.println("Write how many ml of milk do you want to add:");
                state = State.FILLING_MILK;
                break;
            case FILLING_MILK:
                fillMilk(Integer.parseInt(input));
                System.out.println("Write how many grams of coffee beans do you want to add:");
                state = State.FILLING_BEANS;
                break;
            case FILLING_BEANS:
                fillCoffeeBeans(Integer.parseInt(input));
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                state = State.FILLING_CUPS;
                break;
            case FILLING_CUPS:
                fillCups(Integer.parseInt(input));
                System.out.println();
                state = State.CHOOSING_ACTION;
                break;
            case EXIT:
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(550, 400, 540, 120, 9);
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            if (machine.getState() == State.CHOOSING_ACTION) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
            }
            input = scanner.nextLine();
            machine.chooseAction(input);
        } while (machine.getState() != State.EXIT);

    }
}
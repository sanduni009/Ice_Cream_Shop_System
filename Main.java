import java.util.ArrayList;
import java.util.Scanner;

class OrderNode {
    int orderId;
    String customerName;
    String flavor;
    double price;
    ArrayList<String> orderToppings; 
    OrderNode next;

    public OrderNode(int orderId, String customerName, String flavor, double price, ArrayList<String> toppings) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.flavor = flavor;
        this.price = price;
        this.orderToppings = new ArrayList<>(toppings); 
        this.next = null;
    }
}

class OrderQueue {
    private OrderNode front;
    private OrderNode rear;

    public OrderQueue() {
        this.front = null;
        this.rear = null;
    }

    public void addOrder(int orderId, String customerName, String flavor, double price, ArrayList<String> toppings) {
        OrderNode newOrder = new OrderNode(orderId, customerName, flavor, price, toppings);
        if (this.rear == null) {
            this.front = this.rear = newOrder;
        } else {
            this.rear.next = newOrder;
            this.rear = newOrder;
        }
        System.out.println("[Queue] Order successfully added for " + customerName);
    }

    public OrderNode processOrder() {
        if (this.front == null) {
            System.out.println("[Queue] No pending orders in the queue!");
            return null;
        }
        OrderNode temp = this.front;
        this.front = this.front.next;
        if (this.front == null) {
            this.rear = null;
        }
        
        String toppingsDisplay = temp.orderToppings.isEmpty() ? "No Toppings" : temp.orderToppings.toString();
        System.out.println("[Queue] Serving Customer: " + temp.customerName);
        System.out.println("        -> Flavor: " + temp.flavor + " (Rs." + temp.price + ")");
        System.out.println("        -> Toppings Applied: " + toppingsDisplay);
        return temp;
    }

    public void viewQueue() {
        if (this.front == null) {
            System.out.println("   Queue is empty.");
            return;
        }
        OrderNode current = this.front;
        int index = 1;
        while (current != null) {
            String toppingsDisplay = current.orderToppings.isEmpty() ? "No Toppings" : current.orderToppings.toString();
            System.out.println("   " + index + ". " + current.customerName + " [" + current.flavor + "] | Toppings: " + toppingsDisplay);
            current = current.next;
            index++;
        }
    }
}

class IceCreamItem {
    int id;
    String name;
    double price;

    public IceCreamItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - Rs." + price;
    }
}

class IceCreamInventory {
    private IceCreamItem[] flavors;

    public IceCreamInventory() {
        flavors = new IceCreamItem[]{
            new IceCreamItem(1, "Vanilla", 250),
            new IceCreamItem(2, "Chocolate", 300),
            new IceCreamItem(3, "Strawberry", 280),
            new IceCreamItem(4, "Mango", 350)
        };
    }

    public IceCreamItem searchFlavor(String flavorName) {
        for (int i = 0; i < flavors.length; i++) {
            if (flavors[i].name.equalsIgnoreCase(flavorName)) {
                return flavors[i];
            }
        }
        return null;
    }

    public void sortByPrice() {
        int n = flavors.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (flavors[j].price > flavors[j + 1].price) {
                    IceCreamItem temp = flavors[j];
                    flavors[j] = flavors[j + 1];
                    flavors[j + 1] = temp;
                }
            }
        }
        System.out.println("[Bubble Sort] Inventory sorted by price successfully!");
        printInventory();
    }

    public void printInventory() {
        for (IceCreamItem item : flavors) {
            System.out.println("   ID: " + item.id + " | " + item.name + " - Rs." + item.price);
        }
    }
}

class ToppingStack {
    private ArrayList<String> stack;

    public ToppingStack() {
        this.stack = new ArrayList<>();
    }

    public void addTopping(String topping) {
        this.stack.add(topping);
        System.out.println("[Stack] Added topping: " + topping);
        printStack();
    }

    public void undoTopping() {
        if (this.stack.isEmpty()) {
            System.out.println("[Stack Underflow] No toppings to remove!");
            return;
        }
        String removed = this.stack.remove(this.stack.size() - 1);
        System.out.println("[Stack] Undo Successful! Removed topping: " + removed);
        printStack();
    }

    public ArrayList<String> getCurrentStack() {
        return this.stack;
    }

    public void clearStack() {
        this.stack.clear();
    }

    public void printStack() {
        if (stack.isEmpty()) {
            System.out.println("   Topping Stack is empty.");
            return;
        }
        System.out.print("   Current Topping Stack (Top -> Bottom): ");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.print("[" + stack.get(i) + "] ");
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IceCreamInventory inventory = new IceCreamInventory();
        OrderQueue queue = new OrderQueue();
        ToppingStack toppingSelector = new ToppingStack();
        int orderIdCounter = 101;

        System.out.println("==================================================");
        System.out.println("     Welcome to Smart Ice Cream Shop System      ");
        System.out.println("==================================================");

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View & Search Flavors (Array & Linear Search)");
            System.out.println("2. Sort Flavors by Price (Bubble Sort)");
            System.out.println("3. Prepare Custom Toppings (Stack - Push/Pop)");
            System.out.println("4. Place Customer Order (Queue Enqueue)");
            System.out.println("5. Serve Next Customer (Queue Dequeue)");
            System.out.println("6. View Pending Orders Queue");
            System.out.println("7. Exit System");
            System.out.print("Choose an option (1-7): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Invalid input! Please enter a number between 1 and 7.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- Ice Cream Inventory ---");
                    inventory.printInventory();
                    System.out.print("\nEnter flavor name to search: ");
                    String searchKey = scanner.nextLine().trim();
                    System.out.println("\n[Linear Search] Searching...");
                    IceCreamItem found = inventory.searchFlavor(searchKey);
                    if (found != null) {
                        System.out.println("✅ Found: " + found);
                    } else {
                        System.out.println("❌ Flavor '" + searchKey + "' is not available!");
                    }
                    break;

                case 2:
                    System.out.println("\nRunning Bubble Sort Algorithm...");
                    inventory.sortByPrice();
                    break;

                case 3:
                    System.out.println("\n--- Custom Toppings Management ---");
                    System.out.println("1. Add Topping (Push)");
                    System.out.println("2. Undo Last Topping (Pop)");
                    System.out.print("Select action (1-2): ");
                    String topChoice = scanner.nextLine();
                    if (topChoice.equals("1")) {
                        System.out.print("Enter topping name (e.g., Choco Chips, Sprinkles, Almond): ");
                        String topName = scanner.nextLine().trim();
                        if(!topName.isEmpty()) toppingSelector.addTopping(topName);
                    } else if (topChoice.equals("2")) {
                        toppingSelector.undoTopping();
                    } else {
                        System.out.println("Invalid topping option!");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Place New Order ---");
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter Ice Cream Flavor: ");
                    String flavor = scanner.nextLine().trim();
                    
                    IceCreamItem verifiedFlavor = inventory.searchFlavor(flavor);
                    double price = (verifiedFlavor != null) ? verifiedFlavor.price : 250.0;

                    if (!name.isEmpty() && !flavor.isEmpty()) {
                        ArrayList<String> currentToppings = toppingSelector.getCurrentStack();
                        
                        queue.addOrder(orderIdCounter++, name, flavor, price, currentToppings);
                        
                        toppingSelector.clearStack(); 
                    } else {
                        System.out.println("❌ Name or Flavor cannot be empty!");
                    }
                    break;

                case 5:
                    System.out.println("\n--- Processing Orders (FIFO) ---");
                    queue.processOrder();
                    break;

                case 6:
                    System.out.println("\n--- Current Pending Orders Queue ---");
                    queue.viewQueue();
                    break;

                case 7:
                    System.out.println("\nThank you for using Smart Ice Cream Shop System. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid choice! Please select between 1 and 7.");
            }
        }
    }
}
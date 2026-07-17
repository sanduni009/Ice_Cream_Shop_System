 Smart Ice Cream Shop Management System

A Java Console Application showcasing core Data Structures and Algorithms (DSA) concepts through an Object-Oriented Programming (OOP) approach. Designed as a practical assignment for Uva Wellassa University.

---

 Project Overview
Managing workflows in food service (such as ice cream parlors) requires highly predictable data handling. Customers must be served in the order they arrive, toppings must be flexible to undo/redo operations, and inventory lookup must be fast. 

This system resolves these business bottlenecks by implementing custom data structure representations for Arrays, Stacks, and Queues along with standard search and sort operations.

---

 Data Structures & Algorithms Implemented

1. Arrays (Dynamic Flavor Inventory)
 Implementation: The ice cream menu database is mapped using an object array of the `IceCreamItem` class.
 Justification: Perfect for low-overhead indexed access.

2. Linear Search (Flavor Search)
 Implementation: Performs a case-insensitive, step-by-step sequential search to verify if a requested ice cream flavor exists in the inventory.

 3. Bubble Sort (Price Sorting)
 Implementation: Dynamically sorts the inventory list by the price of each ice cream flavor in ascending order.

 4. Stack (Topping Customization - LIFO)
 Implementation: Implements Last-In-First-Out (LIFO) memory access. Staff can Push toppings onto an active preparation stack and use the Pop (Undo) mechanism to easily remove the most recently added ingredient if requested by a customer.

 5. Linked List Queue (Order Booking - FIFO)
 Implementation: Built from scratch using a dedicated pointer-based queue (`OrderNode` and `OrderQueue` classes). It executes *Enqueue* when placing an order and Dequeue when serving a customer.
 Justification: Guarantees strict First-In-First-Out (FIFO) service equity without experiencing array-shifting memory overhead.

---

 How to Run the System

 Prerequisites
Make sure you have the Java Development Kit (JDK) installed on your system.
 Check version using terminal: `java -version`

 Step-by-Step Compilation & Run

1. Open your terminal or command prompt (CMD).
2. Navigate to the directory containing your source code:
                                                                                                   # Ice_Cream_Shop_System

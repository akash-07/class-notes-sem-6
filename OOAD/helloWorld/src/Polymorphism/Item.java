package Polymorphism;

/**
 * Created by @kash on 1/17/2018.
 */
public class Item {
    int itemNo;
    String name;
    float price;

    void giveData(int itemNo, String name, float price) {
        this.itemNo = itemNo;
        this.name = name;
        this.price = price;
    }

    void displayItemDetails()   {
        System.out.println("Item no = " + itemNo);
        System.out.println("Item name = " + name);
        System.out.println("Item price = " + price);
    }

    void giveData(float price)    {
        this.price = price;
    }
}

class SubItem extends Item{
    void giveData(float price)  {
        System.out.println("I am subItem");
        this.price = price;
    }
}

class MethodOverLoading {
    public static void main(String[] args)  {
        Item item = new Item();
        item.giveData(1000, "Nut", 5000);
        item.displayItemDetails();
        item.giveData(6000);
        System.out.println("After modification: ");
        item.displayItemDetails();
        SubItem subItem = new SubItem();
        subItem.giveData(7000);
        subItem.displayItemDetails();
    }
}

/*
* Overriden methods should have same type signature
* and should have same return type.
*
* Overloaded methods should have same name but different
* types of parameters in the sense of either of:
* 1. Number of parameters
* 2. order of parameters
* 3. type of parameters
*
* return type is irrelevant when it comes to overloaded
* methods*/
import java.io.*;
import java.util.Scanner;

public class Basket {
    protected String[] products;
    protected int[] prices;
    protected int[] basket = new int[5];
    protected File file = new File("basket.bin");

    public Basket() {
        this.products = new String[]{"Мандарины- ", "Яблоки- ", "Груши- ", "Хлеб- ", "Молоко- "};
        this.prices = new int[]{100, 150, 120, 50, 130};
    }

    public Basket(String[] products, int[] prices, int[] basket) {
        this.products = products;
        this.prices = prices;
        this.basket = basket;
    }

    public void addToCart(int productNum, int amount) {
        basket[productNum - 1] += amount;
    }

    public void printCart() {
        int sumProducts = 0;
        System.out.println("Ваша корзина: ");
        System.out.println();
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] <= 0) {
                continue;
            }
            int priceProduct = basket[i] * prices[i];
            sumProducts += priceProduct;
            System.out.println(products[i] + ": " + basket[i] + "шт " + prices[i] + " шт/рублей." + " Итого: " + priceProduct + " рублей.");
        }
        System.out.println("Сумма корзины: " + sumProducts + " рублей.");
    }

    public File getFile() {
        return file;
    }

    public void saveBin(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(new Basket(products, prices, basket));
        }
    }

    public static Basket loadFromBinFile(File file) throws Exception {
        Basket basket = null;
        try (ObjectInputStream ins = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ins.readObject();
            return basket;
        }
    }

    @Override
    public String toString() {
        System.out.println("Список товаров для покупки: ");
        for (int i = 0; i < products.length; i++) {
            System.out.println(" Товар: " + " " + products[i] + prices[i] + " рублей.");
        }
        return " ";
    }
}

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;


public class Basket {

    protected String[] products;
    protected int[] prices;
    protected int[] basket;
    protected File file = new File("basket.txt");

    public Basket (String[] products, int[] prices) {
        this.products = new String[]{"Мандарины ", "Яблоки ", "Груши ", "Хлеб ", "Молоко "};
        this.prices = new int[]{100, 150, 120, 50, 130};
        this.basket = new int[this.products.length];
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
        int sumPurchase = 0;
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] > 0) {
                int pricePurchase = prices[i] * basket[i];
                sumPurchase += pricePurchase;
                System.out.println(products[i] + "-" + basket[i] + "шт" + " на сумму: " + pricePurchase
                );
            }
        }
        System.out.println("Сумма вашей корзины : " + sumPurchase);
    }
    public File getFile() {
        return file;
    }
    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(textFile)) {
            for (String product : products) {
                writer.print(product + " ");
            }
            writer.print("\n");
            for (int prices : prices) {
                writer.print(prices + " ");
            }
            writer.print("\n");
            for (int i : basket) {
                writer.print(i + " ");
            }
        }
    }
    public static Basket loadFromTxtFile(File textFile) throws Exception {
        try (InputStream ins = new FileInputStream(textFile)) {
            Scanner scanner = new Scanner(ins);
            String[] products = scanner.nextLine().trim().split(" ");
            String[] pricesI = scanner.nextLine().trim().split(" ");
            int[] prices = new int[pricesI.length];
            for (int i = 0; i < pricesI.length; i++) {
                prices[i] = Integer.parseInt(pricesI[i]);
            }
            String[] basketI = scanner.nextLine().trim().split(" ");
            int[] basket = new int[basketI.length];
            for (int i = 0; i < basketI.length; i++)
                basket[i] = Integer.parseInt(basketI[i]);
            return new Basket(products, prices, basket);
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
    public void saveJSON(File file)  {
        try (PrintWriter writer= new PrintWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json =gson.toJson(this);
            writer.print(json);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromJSONFile(File file){
        Basket basket = null;
        try (BufferedReader reader= new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String line =null;
            while ((line=reader.readLine()) != null) {
                builder.append(line);
            }
            Gson gson = new Gson();
            basket = gson.fromJson(builder.toString(), Basket.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }
}



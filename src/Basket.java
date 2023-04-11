import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String products[];
    protected int prices[];
    protected int basket[];

    public Basket() {
    }


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

    public void saveTxt(File textFile) throws FileNotFoundException {
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
    public static Basket loadFromTxtFile(File textFile) throws FileNotFoundException {
        Basket basket = new Basket();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String productsStr = bufferedReader.readLine();
            String pricesStr = bufferedReader.readLine();
            String basketStr = bufferedReader.readLine();
            basket.products = productsStr.split(" ");
            basket.prices = Arrays.stream(pricesStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
            basket.basket = Arrays.stream(basketStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }
    public void saveBin(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromBinFile(File file) {
        Basket basket = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return basket;
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
        Basket basket ;
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



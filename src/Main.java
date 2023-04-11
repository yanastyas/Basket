import java.io.File;


public class Main {
    static File saveFile = new File("basket.json");

    public static void main(String[] args) throws Exception {

        Basket basket = null;
        if (saveFile.exists()) {
            basket = Basket.loadFromJSONFile(saveFile);
            basket.printCart();
        } else {basket=new Basket(basket.products, basket.prices);}
        basket.addToCart(1, 2);
        basket.addToCart(2, 1);
        basket.addToCart(3, 2);
        basket.addToCart(4, 1);
        basket.addToCart(5, 3);
        basket.saveJSON((saveFile));
        basket.printCart();

        ClientLog clientLog = new ClientLog();
        clientLog.log(1, 2);
        clientLog.log(2, 1);
        clientLog.log(3, 2);
        clientLog.log(4, 1);
        clientLog.log(5, 3);
        clientLog.exportAsCSV(new File("log.csv"));

    }
}


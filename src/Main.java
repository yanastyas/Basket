import java.io.File;
import java.util.Scanner;

public class Main {
    //static File saveFile = new File("basket.json");
    static Scanner scanner = new Scanner(System.in);
    static String [] products={"Мандарины ", "Яблоки ", "Груши ", "Хлеб ", "Молоко "};
    static  int [] prices={100, 150, 120, 50, 130};

    public static void main(String[] args) throws Exception {
        XMLSettingsReader settings = new XMLSettingsReader(new File("shop.xml"));
        File loadFile = new File(settings.loadFile);
        File saveFile = new File(settings.saveFile);
        File logFile = new File(settings.logFile);
        Basket basket = createBasket(loadFile, settings.isLoad, settings.loadFormat);

        ClientLog clientLog = new ClientLog();
        while (true) {
            showPrice();
            System.out.println("Выберите товар и количество через пробел или end");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                if (settings.isLog) {
                    clientLog.exportAsCSV(logFile);
                }
                break;
            }
            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, productCount);
            if (settings.isLog) {
                clientLog.log(productNumber, productCount);
            }
            if (settings.isSave) {

                switch (settings.saveFormat) {
                    case "json" -> basket.saveJSON(saveFile);
                    case "txt" -> basket.saveTxt(saveFile);
                }
            }
        }
        basket.printCart();
    }
    public static Basket createBasket(File loadFile, boolean isLoad, String loadFormat) throws Exception {
        Basket basket ;
        if(isLoad && loadFile.exists()){
            basket=switch (loadFormat) {
                case "json" -> Basket.loadFromJSONFile(loadFile);
                case "txt" -> Basket.loadFromTxtFile(loadFile);
                default -> new Basket(products, prices);
            };
        }else {
            basket = new Basket(products,prices);
        }
        return basket;
    }
    public static void showPrice(){
        System.out.println("Список товаров для покупки : ");
        for (int i = 0; i < products.length ; i++) {
            System.out.println(products[i]+" "+prices[i]+"руб./шт.");

        }
    }
}



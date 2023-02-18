public class Main {
    public static void main(String[] args) throws Exception {
        Basket basket = new Basket();
        System.out.println(basket);
        if (basket.getFile().exists()) {
            basket = Basket.loadFromTxtFile(basket.getFile());
            basket.printCart();
        } else {
            basket.addToCart(1, 2);
            basket.saveTxt(basket.getFile());
            basket.addToCart(2, 1);
            basket.saveTxt(basket.getFile());
            basket.addToCart(3, 2);
            basket.saveTxt(basket.getFile());
            basket.addToCart(4, 1);
            basket.saveTxt(basket.getFile());
            basket.addToCart(5, 3);
            basket.saveTxt(basket.getFile());
            basket.printCart();
        }
    }
}
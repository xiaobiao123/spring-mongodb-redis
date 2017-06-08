package model.observer;

public class ObserverClient {
    public static void main(String[] args) {
        Subject product = new Product();
        Subscriber subscriber = new Subscriber(product);
        product.setPrice(10.98);
        product.setPrice(998.15);
    }
}  
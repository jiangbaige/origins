package org.smart4j.test.aop.threadlocal;

public class ClientThread extends Thread {

    private Sequence sequence;

    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }
//
//    @Override
//    public void run() {
//
//
//        for(int i = 0; i < 3; i++) {
//            System.out.println(Thread.currentThread().getName() + " => " + sequence.getNumber());
//
//        }
//    }

    private ProductService productService;

    public ClientThread(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        productService.updateProductPrice(1,3000);
    }

}

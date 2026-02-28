package com.example.demo;

import com.example.demo.Models.*;
import com.example.demo.Repositories.*;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DatabaseInit implements org.springframework.boot.CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProduceRepository produceRepository;

    @Autowired
    private SellerProduceRepository sellerProduceRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedItemsRepository orderedItemsRepository;
    
    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        User user1_bob = new User("Bob","bob@sample.com", "bob_pass", UserType.BUYER);
        User user2_pra = new User("Prapanch","prapanch@sample.com", "prapanch_pass", UserType.SELLER);
        User user3_ade = new User("Ademola","ademola@sample.com", "ademola_pass", UserType.BOTH);
        User user4_zhi = new User("Zhixian","zhixian@sample.com", "zhixian_pass", UserType.BUYER);
		userRepository.save(user1_bob);
        userRepository.save(user2_pra);
        userRepository.save(user3_ade);
        userRepository.save(user4_zhi);

        Produce prod1_app = new Produce("Apple");
        Produce prod2_lett = new Produce("Lettuce");
        Produce prod3_pota = new Produce("Potato");
        produceRepository.save(prod1_app);
        produceRepository.save(prod2_lett);
        produceRepository.save(prod3_pota);


        // Prapanch sells apples for £0.15 (stock 100)
        SellerProduce prapApple = new SellerProduce();
        prapApple.setSeller(user2_pra);
        prapApple.setProduce(prod1_app);
        prapApple.setPrice((float)0.15);
        prapApple.setQuantity(100);
        sellerProduceRepository.save(prapApple);
        // Prapanch sells lettuce for £0.25 (stock 20)        
        SellerProduce prapLettuce = new SellerProduce();
        prapLettuce.setSeller(user2_pra);
        prapLettuce.setProduce(prod2_lett);
        prapLettuce.setPrice((float)0.25);
        prapLettuce.setQuantity(20);
        sellerProduceRepository.save(prapLettuce);

        // Ademola sells apples for £0.30 (stock 50). 
        SellerProduce ademApple = new SellerProduce();
        ademApple.setSeller(user3_ade);
        ademApple.setProduce(prod1_app);
        ademApple.setPrice((float)0.30);
        ademApple.setQuantity(50);
        sellerProduceRepository.save(ademApple);
        // Ademola sells potatoes for £0.05 (stock 30).
        SellerProduce ademPotato = new SellerProduce();
        ademPotato.setSeller(user3_ade);
        ademPotato.setProduce(prod3_pota);
        ademPotato.setPrice((float)0.05);
        ademPotato.setQuantity(30);
        sellerProduceRepository.save(ademPotato);

        
        Order bobOrder = new Order(user1_bob);
        orderRepository.save(bobOrder);
        // Bob places an order for 2 apples from Ademola.
        OrderedItems bobApple = new OrderedItems();
        bobApple.setOrder(bobOrder);
        bobApple.setPriceAtOrder(ademApple.getPrice());
        bobApple.setQuantity(2);
        bobApple.setSellerProduce(ademApple);
        orderedItemsRepository.save(bobApple);
        // Bob places an order for 1 lettuce from Prapanch.
        OrderedItems bobLettuce = new OrderedItems();
        bobLettuce.setOrder(bobOrder);
        bobLettuce.setPriceAtOrder(prapLettuce.getPrice());
        bobLettuce.setQuantity(1);
        bobLettuce.setSellerProduce(prapLettuce);



        Order zhiOrder = new Order(user4_zhi);
        orderRepository.save(zhiOrder);
        // Zhixian places an order for 10 apples from Prapanch.
        OrderedItems zhiApple = new OrderedItems();
        zhiApple.setOrder(zhiOrder);
        zhiApple.setPriceAtOrder(prapApple.getPrice());
        zhiApple.setQuantity(10);
        zhiApple.setSellerProduce(prapApple);
        orderedItemsRepository.save(zhiApple);
        // Zhixian places an order for 15 potatoes from Ademola.
        OrderedItems zhiPotato = new OrderedItems();
        zhiPotato.setOrder(zhiOrder);
        zhiPotato.setPriceAtOrder(ademPotato.getPrice());
        zhiPotato.setQuantity(15);
        zhiPotato.setSellerProduce(ademPotato);
        orderedItemsRepository.save(zhiPotato);

        prapApple.setQuantity(prapApple.getQuantity() - bobApple.getQuantity() - zhiApple.getQuantity());
        prapLettuce.setQuantity(prapLettuce.getQuantity() - bobLettuce.getQuantity());
        ademApple.setQuantity(ademApple.getQuantity() - zhiApple.getQuantity());
        ademPotato.setQuantity(ademPotato.getQuantity() - zhiPotato.getQuantity());
        sellerProduceRepository.save(prapApple);
        sellerProduceRepository.save(prapLettuce);
        sellerProduceRepository.save(ademApple);
        sellerProduceRepository.save(ademPotato);
    }
}

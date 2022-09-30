package com.ecommerce.service;

import com.ecommerce.dto.*;
import com.ecommerce.exception.Error;
import com.ecommerce.mapper.*;
import com.ecommerce.model.*;
import com.ecommerce.repository.PublicationRepository;
import com.ecommerce.repository.SellProductRepository;
import com.ecommerce.repository.ShoppingCartRepository;
import com.ecommerce.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PublicationMapper publicationMapper;
    @Autowired
    private SimpleStoreMapper simpleStoreMapper;
    @Autowired
    private SellProductRepository sellProductRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;


    public List<StoreDTO> findall() {
        List<Store> store = storeRepository.findAll();
        List<StoreDTO> storeDTOS = new ArrayList<>();
        for (Store store1 : store) {
            storeDTOS.add(storeMapper.storeToDTO(store1));
        }
        return storeDTOS;
    }

    public SimpleStoreDTO findbyId(Long id) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        return simpleStoreMapper.storeToSimpleStore(store);
    }

    public Store createStore(Long id) throws Error {
        Store store = new Store();
        Seller seller = sellerService.findSellerById(id);
        store.setUser(seller);
        for (StoreDTO stor1 : findall()) {
            if (stor1.getSeller_id().equals(seller.getSeller_id())) {
                throw new Error("Store already exists");
            }
        }
        return storeRepository.save(store);
    }

    public PublicationDTO addPublication(Long id, PublicationDTO publication) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        if (store == null) {
            throw new Error("Store not found");
        }
        List<ShowSellProductDTO> products = productService.findAllsellProducts(); //TODO VERIFICAR QUE EL PRODUCTO SEA DEL USUARIO
        for (ShowSellProductDTO product : products) {
            if (product.getId().equals(publication.getIdSellProduct())) {
                Publication publication1 = new Publication();
                SellProduct buff = sellProductRepository.findById(publication.getIdSellProduct()).orElseThrow(() -> new Error("Product not found"));
                publication1.setPublicationName(publication.getPublicationName());
                publication1.setStock(publication.getStock());
                publication1.setPrice(product.getPrice());
                publication1.setIsActive(publication.getIsActive());
                publication1.setId_sellproduct(publication.getIdSellProduct());
                publication1.setStore_id(id);
                publication1.setSellProduct(buff);
                publicationService.createPublication(publication1);
                return publicationMapper.toPublicationDTO(publication1);
            }
        }
        throw new Error("Product not found");
    }

    public Store deleteStore(Long id) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        storeRepository.delete(store);
        return store;
    }

    public Store addPaymentMethod(Long id, PaymentMethod paymentMethod) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        for (PaymentMethod paymentMethod1 : store.getPaymentMethods()) {
            if (paymentMethod1.equals(paymentMethod)) {
                throw new Error("Payment method already exists");
            }
        }
        store.getPaymentMethods().add(paymentMethod);
        return storeRepository.save(store);
    }

    public Store removePaymentMethod(Long id, PaymentMethod paymentMethod) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        for (PaymentMethod paymentMethod1 : store.getPaymentMethods()) {
            if (paymentMethod1.equals(paymentMethod)) {
                store.getPaymentMethods().remove(paymentMethod);
                return storeRepository.save(store);
            }
        }
        throw new Error("Payment method not found");
    }

    public StoreDTO updatepublicationStatus(Long id, Long idPublication, Boolean status) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        for (Publication publication : store.getPublications()) {
            if (publication.getId().equals(idPublication)) {
                if (publication.getIsActive().equals(status)) {
                    throw new Error("Publication already has this status");
                }
                publication.setIsActive(status);
                publicationService.createPublication(publication);
                return storeMapper.storeToDTO(store);
            }
        }
        throw new Error("Publication not found");
    }

    //TODO CONSULTAR TODO LO DE ABAJO
    public String addProductToShoppingCart(Long id, Long idproduct) throws Error {
        SellProduct nuevo = storeFindProduct(id, idproduct);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.getProductList().add(nuevo);
        shoppingCart.setTotalProducts(shoppingCart.getProductList().size());
        shoppingCart.setTotalPrice(getTotalPrice(shoppingCart));
        shoppingCartRepository.save(shoppingCart);
        return "Product added to shopping cart";
    }

    public String addMoreProductstoShopping (Long idStore, Long idProduct ,Long idShopping) throws Error {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(idShopping).orElseThrow(() -> new Error("Shopping cart not found"));
        SellProduct nuevo = storeFindProduct(idStore, idProduct);
        shoppingCart.getProductList().add(nuevo);
        shoppingCart.setTotalProducts(shoppingCart.getProductList().size());
        shoppingCart.setTotalPrice(getTotalPrice(shoppingCart));
        shoppingCartRepository.save(shoppingCart);
        return "Product added to shopping cart";
    }

    public Double getTotalPrice(ShoppingCart shop) {
        Double total = 0.0;
        for (SellProduct sellProduct : shop.getProductList()) {
            total += sellProduct.getSellingPrice();
        }
        return total;
    }

    public SellProduct storeFindProduct(Long id, Long idproduct) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        for (Publication publication : store.getPublications()) {
            if (publication.getSellProduct().getId().equals(idproduct) && publication.getIsActive()) {
                return publication.getSellProduct();
            }else{
                throw new Error("Product not found or Publication is not active");
            }
        }
        throw new Error("Product not found");
    }

    public List<ShoppingCartDTO> getShoppingCart() {
        return shoppingCartMapper.shoppingCartEntityList2DTOList(shoppingCartRepository.findAll());
    }

    public ShoppingCartDTO getShoppingCartById(Long id) throws Error {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(() -> new Error("Shopping cart not found"));
        return shoppingCartMapper.shoppingCartToDTO(shoppingCart);
    }

    public String checkoOut (Long id) throws Error {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(() -> new Error("Shopping cart not found"));
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1, "Total Price " + getTotalPrice(shoppingCart).toString());
        map.put(2, "Total Products " + shoppingCart.getTotalProducts().toString());
        map.put(3, "Products" + shoppingCart.getProductList().toString());
        shoppingCartRepository.delete(shoppingCart);
        return map.toString() + "Checkout done - Thanks for your purchase";
    }


}

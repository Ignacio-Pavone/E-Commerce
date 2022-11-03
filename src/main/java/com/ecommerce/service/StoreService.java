package com.ecommerce.service;

import com.ecommerce.exception.Error;
import com.ecommerce.mapper.*;
import com.ecommerce.model.*;
import com.ecommerce.model.dto.PublicationDTO;
import com.ecommerce.model.dto.ShoppingCartProductDTO;
import com.ecommerce.model.dto.ShowSellProductDTO;
import com.ecommerce.model.dto.StoreDTO;
import com.ecommerce.repository.*;
import com.ecommerce.utils.InvoicePDFExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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
    private SellProductRepository sellProductRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PublicationRepository publicationRepository;


    public List<StoreDTO> findall() {
        List<Store> store = storeRepository.findAll();
        List<StoreDTO> storeDTOS = new ArrayList<>();
        for (Store store1 : store) {
            storeDTOS.add(storeMapper.storeToDTO(store1));
        }
        return storeDTOS;
    }
    public StoreDTO findbyId(Long id) throws Error {
        Optional<Store> store = storeRepository.findById(id);
           if (store.isPresent()) {
                return storeMapper.storeToDTO(store.get());
            }
           return null;
    }
    public StoreDTO createStore(Long id) throws Error {
        Store store = new Store();
        Seller seller = sellerService.findSellerById(id);
        store.setUser(seller);
        for (StoreDTO stor1 : findall()) {
            if (stor1.getSeller_id().equals(seller.getSeller_id())) {
                throw new Error("Store already exists");
            }
        }
        StoreDTO back = storeMapper.storeToDTO(storeRepository.save(store));
        return back;
    }

    public PublicationDTO addPublication(Long id, PublicationDTO publication) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        if (store == null) {
            throw new Error("Store not found");
        }else if (store.getPaymentMethods().isEmpty()) {
            throw new Error("Store without payment methods");
        }
        List<ShowSellProductDTO> products = productService.findAllsellProducts();
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

    public StoreDTO deleteStore(Long id) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        StoreDTO retorno = storeMapper.storeToDTO(store);
        storeRepository.delete(store);
        return retorno;

    }

    public StoreDTO addPaymentMethod(Long id, PaymentMethod paymentMethod) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        for (PaymentMethod paymentMethod1 : store.getPaymentMethods()) {
            if (paymentMethod1.equals(paymentMethod)) {
                throw new Error("Payment method already exists");
            }
        }
        store.getPaymentMethods().add(paymentMethod);
        StoreDTO retorno = storeMapper.storeToDTO(storeRepository.save(store));
        return retorno;
    }

    public StoreDTO removePaymentMethod(Long id, PaymentMethod paymentMethod) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        for (PaymentMethod paymentMethod1 : store.getPaymentMethods()) {
            if (paymentMethod1.equals(paymentMethod)) {
                store.getPaymentMethods().remove(paymentMethod);
                StoreDTO retorno = storeMapper.storeToDTO(storeRepository.save(store));
                storeRepository.save(store);
                 return retorno;
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

    public List<ShoppingCart> getAllShoppingCart() throws Error {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart getShoppingCart(Long id) throws Error {
        return shoppingCartRepository.findById(id).orElseThrow(() -> new Error("Shopping cart not found"));
    }

    public SellProduct storeFindProduct(Long idStore, Long idPublication) throws Error {
        Store store = storeRepository.findById(idStore).orElseThrow(() -> new Error("Store not found"));
        for (Publication publication : store.getPublications()) {
            if (publication.getId().equals(idPublication) && publication.getIsActive()) {
                return publication.getSellProduct();
            }
        }
        throw new Error("Product not found or not active");
    }

    public String addProductToShoppingCart(ShoppingCartProductDTO data) throws Error {
        SellProduct nuevo = storeFindProduct(data.getStoreID(), data.getPublicationID());
        Item item = new Item(nuevo, data.getQuantity());
        List<Item> items = new ArrayList<>();
        items.add(item);
        itemRepository.save(item);
        ShoppingCart shoppingCart = new ShoppingCart(items, items.size(), getTotalPriceList(items, data.getQuantity()), data.getStoreID(), LocalDate.now());
        shoppingCartRepository.save(shoppingCart);
        return "Product added to shopping cart";

    }

    public Double getTotalPriceList(List<Item> shop, Integer quantity) {
        Double total = 0.0;
        for (Item sellProduct : shop) {
            total += sellProduct.getSellProduct().getSellingPrice();
        }
        return total * quantity;
    }

    public Double getTotalPrice(ShoppingCart shop, Integer quantity) {
        Double total = 0.0;
        for (Item sellProduct : shop.getProductList()) {
            total += sellProduct.getSellProduct().getSellingPrice();
        }
        return total * quantity;
    }

    public String addMoreProductstoShopping(Long idShopping,ShoppingCartProductDTO data) throws Error {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(idShopping).orElseThrow(() -> new Error("Shopping cart not found"));
        SellProduct nuevo = storeFindProduct(data.getStoreID(), data.getPublicationID());
        System.out.println(nuevo);
        if (shoppingCart.getStore().equals(data.getStoreID())) {
            Item item = new Item(nuevo, data.getQuantity());
            shoppingCart.getProductList().add(item);
            shoppingCart.setTotalProducts(shoppingCart.getProductList().size());
            shoppingCart.setTotalPrice((nuevo.getSellingPrice() * data.getQuantity()) + shoppingCart.getTotalPrice());
            shoppingCartRepository.save(shoppingCart);
            return "Product added to shopping cart";
        }
        throw new Error("Product is not from this store");
    }

    public String checkoOut(Long id) throws Error {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(() -> new Error("Shopping cart not found"));
        Store findStore = storeRepository.findById(shoppingCart.getStore()).orElseThrow(() -> new Error("Store not found"));
        if (createInvoice(findStore.getUser().getUser().getName(),shoppingCart)) {
            shoppingCartRepository.delete(shoppingCart);
            return "Checkout completed";
        }else{
            throw new Error("Error creating invoice");
        }

    }

    public boolean createInvoice (String sellername, ShoppingCart shoppingCart) throws Error {
        Invoice invoice = new Invoice(shoppingCart.getId_shopping_cart(),sellername, shoppingCart.getTotalProducts(), shoppingCart.getBuyDate(), shoppingCart.getTotalPrice(), shoppingCart.getProductList());
        InvoicePDFExporter invoicePDFExporter = new InvoicePDFExporter(invoice);
        Boolean flag = false;
        try {
            invoicePDFExporter.export();
            flag = true;
            return flag;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteProductFromShoppingCart(Long idShopping, Long idProduct) throws Error {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(idShopping).orElseThrow(() -> new Error("Shopping cart not found"));
        for (Item item : shoppingCart.getProductList()) {
            if (item.getSellProduct().getId().equals(idProduct)) {
                shoppingCart.getProductList().remove(item);
                shoppingCart.setTotalProducts(shoppingCart.getProductList().size());
                shoppingCart.setTotalPrice(getTotalPrice(shoppingCart, item.getQuantity()));
                shoppingCart.setBuyDate(shoppingCart.getBuyDate());
                if (shoppingCart.getProductList().size() == 0) {
                    shoppingCartRepository.delete(shoppingCart);
                }
                shoppingCartRepository.save(shoppingCart);
                return "Product removed from shopping cart";
            }
        }


        throw new Error("Product not found");
    }
}

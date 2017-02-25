package com.pizzashop.initializers;

import com.pizzashop.models.*;
import com.pizzashop.models.builders.*;
import com.pizzashop.models.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bartosz Pigla on 09/12/2016.
 */
public class DbInitializer {

    @Autowired
    InitEntitiesRepository repository;

    public static Rebate createRebate() {
        return new RebateBuilder()
                .setName("2 w 1")
                .createRebate();
    }

    public static Set<Seasoning> createSeasonings(){
        Set<Seasoning> seasonings=new HashSet<>();

        Seasoning pepper= new SeasoningBuilder()
                .setName("pieperz")
                .createSeasoning();

        Seasoning onion = new SeasoningBuilder()
                .setName("cebula")
                .createSeasoning();

        seasonings.addAll(Arrays.asList(pepper,onion));

        return seasonings;
    }

    public static  Set<Ingredient> createIngredients() {
        Set<Ingredient> ingredients=new HashSet<>();

        Ingredient sos = new IngredientBuilder()
                .setName("sos")
                .createIngredient();

        Ingredient ser = new IngredientBuilder()
                .setName("ser")
                .createIngredient();

        Ingredient szynka = new IngredientBuilder()
                .setName("szynka")
                .createIngredient();

        Ingredient pomidor = new IngredientBuilder()
                .setName("pomidor")
                .createIngredient();

        Ingredient pieczarki = new IngredientBuilder()
                .setName("pieczarki")
                .createIngredient();
        ingredients.addAll(Arrays.asList(sos, ser, szynka, pomidor, pieczarki));

        return ingredients;
    }

    public static  Pizza createPizza(Set<Ingredient> ingredients, Rebate rebate) {
        return new PizzaBuilder()
                .setName("margheritta")
                .setPrice(new Double("14.32"))
                .addRebates(rebate)
                .setDescription("description")
                .setDoughType(DoughType.cienkie)
                .setPizzaSize(PizzaSize.duża)
                .setDoughPrice(new Double("2.43"))
                .setIngredients(ingredients)
                .setUrl("http://www.halopizza-luban.pl/wp-content/uploads/2016/02/pizza-1.jpg")
                .createPizza();
    }

    public static Sauce createSauce(Set<Seasoning> seasonings, Rebate rebate){
        return new SauceBuilder()
                .setName("pomidorowy")
                .setDescription("opis")
                .setPrice(new Double("1.34"))
                .addRebates(rebate)
                .setSeasonings(seasonings)
                .setUrl("http://www.zajadam.pl/wp-content/uploads/2014/07/sos-czosnkowy-1-654x434.jpg")
                .createSauce();
    }

    public static Drink createDrink(Rebate rebate){
        return new DrinkBuilder()
                .setName("cola")
                .setDescription("opis")
                .setPrice(new Double("4.32"))
                .setLiterCount("0.33")
                .addRebates(rebate)
                .setUrl("https://www.pepsi.pl/img/produktPepsiRegular.jpg")
                .createDrink();
    }

    public static  Client createClient() {
        return new ClientBuilder()
                .setFirstName("bartosz")
                .setSurname("ds")
                .seteMail("bartek217a@wp.pl")
                .setClientType(ClientType.normalny)
                .createClient();
    }

    public  static Order createOrder(Client client) {
        return new OrderBuilder()
                .setProductOrderStatus(ProductOrderStatus.w_drodze)
                .setPrice(new Double("32.34"))
                .setAddress("sd")
                .setClient(client)
                .setReceiptDate(new java.sql.Date((new java.util.Date()).getTime()))
                .setOrderDate(new java.sql.Date((new java.util.Date()).getTime()))
                .createOrder();
    }

    public  static Set<OrderPosition> createOrderPositions(Order order, Product product, Rebate rebate) {
        Set<OrderPosition> orderPositions = new HashSet<>();

        orderPositions.addAll(Arrays.asList(
                new OrderPositionBuilder()
                        .setCount(1)
                        .setPrice(new Double("21.32"))
                        .setProduct(product)
                        .setRebate(rebate)
                        .setOrder(order)
                        .createOrderPosition(),

                new OrderPositionBuilder()
                        .setCount(2)
                        .setPrice(new Double("11.32"))
                        .setProduct(product)
                        .setRebate(rebate)
                        .setOrder(order)
                        .createOrderPosition()
        ));

        return orderPositions;
    }

    public  static Complaint createComplaint(Order order) {
        return new ComplaintBuilder()
                .setComment("comment")
                .setOrder(order)
                .setSubmitDate(new java.sql.Date((new java.util.Date()).getTime()))
                .setComplaintStatus(ComplaintStatus.odrzucone)
                .createComplaint();
    }

    public static Manager createManager(){
        return new ManagerBuilder()
                .seteMail("bartoszpigla@o2.pl")
                .setPassword("1234")
                .setRoles(Arrays.asList("ROLE_ADMIN","ADMIN","USER"))
                .createManager();
    }

    @PostConstruct
    @Transactional
    public void initialize() {
        repository.initializeProductSubComponents();
        //save();
    }


//    @PostConstruct
//    public void initialize() {
//        List<Ingredient> ingredientList = ingredientRepository.findAll();
//        {
//            for (Ingredient ingredient : ingredients
//                    ) {
//
//                System.out.println(ingredient);
//
//                if (!ingredientList.contains(ingredient))
//                    ingredientRepository.save(ingredient);
//
//            }
//        }
//        Set<Ingredient> ingredientSet = new HashSet<>(ingredientRepository.findAll());
//        pizza1.setIngredients(ingredientSet);
//
//        rebate1 = rebateRepository.save(rebate1);
//        pizza1.addRebate(rebate1);
//        pizza1 = pizzaRepository.save(pizza1);
//
//        Client client = new ClientBuilder().createClient();
//        client.setFirstName("bartosz");
//        client.setSurname("ds");
//        client.seteMail("bartek217a@wp.pl");
//        client.setClientType(ClientType.normalny);
//
//        client = clientRepository.save(client);
//
//        //List<OrderPosition> orderPositions=new ArrayList<>();
//
//        Order order = new OrderBuilder().createOrder();
//        order.setProductOrderStatus(ProductOrderStatus.w_drodze);
//        order.setPrice(new BigDecimal("32.34"));
//        order.setAddress("sd");
//        order.setClient(client);
//        order.setReceiptDate(new java.sql.Date((new java.util.Date()).getTime()));
//        order.setOrderDate(new java.sql.Date((new java.util.Date()).getTime()));
//
//        order = productOrderRepository.save(order);
//
//        OrderPosition op1 = new OrderPositionBuilder().createOrderPosition();
//        op1.setCount(1);
//        op1.setPrice(new BigDecimal("21.32"));
//        op1.setProduct(pizza1);
//        op1.setOrder(order);
//        op1.setRebate(rebate1);
//
//        op1 = orderPositionRepository.save(op1);
//
//        Complaint complaint = new ComplaintBuilder().createComplaint();
//        complaint.setComment("comment");
//        complaint.setOrder(order);
//        complaint.setSubmitDate(new java.sql.Date((new java.util.Date()).getTime()));
//        complaint.setComplaintStatus(ComplaintStatus.odrzucone);
//
//        complaint = complaintRepository.save(complaint);
//
//        System.out.println(complaint);
//        //op1.setOrder();
//    }

}

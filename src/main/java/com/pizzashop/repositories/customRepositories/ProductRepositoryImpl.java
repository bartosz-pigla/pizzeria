package com.pizzashop.repositories.customRepositories;

import com.pizzashop.exceptions.IngredientNotFoundException;
import com.pizzashop.exceptions.RebateNotFoundException;
import com.pizzashop.exceptions.SeasoningNotFoundException;
import com.pizzashop.models.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Bartosz Pigla on 30/12/2016.
 */
@Component
@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Pizza save(Pizza pizza) throws IngredientNotFoundException, RebateNotFoundException {
//        Ingredient i1=entityManager.find(Ingredient.class,1);
//        Set<Ingredient> ingredients = findIngredients(pizza);
//        pizza.setIngredients(ingredients);
//
//        Set<Rebate> rebates = findRebates(pizza);
//        pizza.setRebates(rebates);

        entityManager.persist(pizza);
        entityManager.flush();
        return pizza;
    }

    @Override
    public List<Pizza> getFromCache() {
        Session session=entityManager.unwrap(Session.class);
        Query query=session.createQuery("select p from Pizza p");

        query.setCacheable(true);

        List<Pizza> list= (List<Pizza>)query.list();
        for (Pizza p:list
             ) {
            System.out.println("cache:"+
                    entityManager.getEntityManagerFactory().getCache().contains(Pizza.class,p));
        }
        return list;
    }

    @Override
    public Sauce save(Sauce sauce) throws SeasoningNotFoundException, RebateNotFoundException {
        Set<Seasoning> seasonings = findSeasonings(sauce);
        sauce.setSeasonings(seasonings);

        Set<Rebate> rebates = findRebates(sauce);
        sauce.setRebates(rebates);

        entityManager.persist(sauce);
        entityManager.flush();
        return sauce;
    }

    @Override
    public Drink save(Drink drink) throws RebateNotFoundException {
        Set<Rebate> rebates = findRebates(drink);
        drink.setRebates(rebates);

        entityManager.persist(drink);
        entityManager.flush();
        return drink;
    }

    Set<Ingredient> findIngredients(Pizza pizza) throws IngredientNotFoundException {
        Set<Ingredient> ingredients = new HashSet<>();
        for (Ingredient ingredient : pizza.getIngredients()) {
            ingredient = entityManager.find(Ingredient.class, ingredient.getIngredientId());
            if (ingredient != null)
                ingredients.add(ingredient);
            else throw new IngredientNotFoundException();
        }
        return ingredients;
    }

    Set<Rebate> findRebates(Product product) throws RebateNotFoundException {
        Set<Rebate> rebates = new HashSet<>();
        for (Rebate rebate : product.getRebates()) {
            rebate = entityManager.find(Rebate.class, rebate.getRebateId());
            if (rebate != null)
                rebates.add(rebate);
            else throw new RebateNotFoundException();
        }
        return rebates;
    }

    Set<Seasoning> findSeasonings(Sauce sauce) throws SeasoningNotFoundException {
        Set<Seasoning> seasonings = new HashSet<>();
        for (Seasoning seasoning : sauce.getSeasonings()) {
            seasoning = entityManager.find(Seasoning.class, seasoning.getSeasoningId());
            if (seasoning != null)
                seasonings.add(seasoning);
            else throw new SeasoningNotFoundException();
        }
        return seasonings;
    }

}

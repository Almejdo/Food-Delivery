package com.fooddelivery.components.items.repository.impl;



import com.fooddelivery.components.items.entity.Item;
import com.fooddelivery.components.items.repository.ItemCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class ItemRepositoryCustomImpl implements ItemCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Item> findItemByCategoryId(Integer categoryId) {
        return entityManager.createNamedQuery("findItemByCategoryId",Item.class)
                .setParameter("category_id",categoryId)
                .getResultList();
    }
}

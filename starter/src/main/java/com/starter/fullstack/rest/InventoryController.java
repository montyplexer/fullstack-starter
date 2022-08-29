package com.starter.fullstack.rest;

import com.starter.fullstack.api.Inventory;
import com.starter.fullstack.api.Product;
import com.starter.fullstack.dao.InventoryDAO;
import java.util.List;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Inventory Controller.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {
  private final InventoryDAO inventoryDAO;

  /**
   * Default Constructor.
   * @param inventoryDAO inventoryDAO.
   */
  public InventoryController(InventoryDAO inventoryDAO) {
    Assert.notNull(inventoryDAO, "Inventory DAO must not be null.");
    this.inventoryDAO = inventoryDAO;
  }

  /**
   * Find Inventories.
   * @return List of Inventories.
   */
  @GetMapping
  public List<Inventory> findInventories() {
    return this.inventoryDAO.findAll();
  }

  /** TODO */

  /**
   * TASK #2
   * Save Inventory.
   * @param inventory inventory.
   * @return Inventory.
   */
  @PostMapping("/inventory")
  public Inventory createInventory(@Valid @RequestBody Inventory inventory) {
    return this.inventoryDAO.create(inventory);
  }





  /* */

  /**
   * Delete Inventory By Id.
   *
   * @param ids ids.
   */
  /**
  @DeleteMapping("/inventory")
  public void deleteInventoryById(@RequestBody List<String> ids) {
    Assert.notEmpty(ids, "Product Ids were not provided");
    this.productDAO.deleteProductsByIdIn(ids);
  }
   */
}


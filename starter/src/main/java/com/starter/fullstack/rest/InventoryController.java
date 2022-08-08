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
   * @return List of Product.
   */
  @GetMapping
  public List<Inventory> findInventories() {
    return this.inventoryDAO.findAll();
  }

  /** TODO */

  /**
   * Save Inventory.
   * @param inventory inventory.
   * @return Inventory.
   */
  @PostMapping("/inventory")
  public Product saveInventory(@Valid @RequestBody Inventory inventory) {
    return this.inventoryDAO.save(inventory);
  }

  /**
   * Delete Product By Id.
   *
   * @param ids ids.
   */
  @DeleteMapping("/inventory")
  public void deleteProductById(@RequestBody List<String> ids) {
    Assert.notEmpty(ids, "Product Ids were not provided");
    this.productDAO.deleteProductsByIdIn(ids);
  }

}


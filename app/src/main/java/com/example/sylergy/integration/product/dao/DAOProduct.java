package com.example.sylergy.integration.product.dao;

import com.example.sylergy.command.Command;
import com.example.sylergy.objects.Product;

public interface DAOProduct {
    void readById(long barcode, Command command);
}

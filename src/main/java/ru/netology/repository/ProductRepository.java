package ru.netology.repository;

import ru.netology.domain.Product;

public class ProductRepository {

    private Product[] products = new Product[0];

    public void save(Product product) {
        int length = products.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(products, 0, tmp, 0, products.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public Product[] deleteById(int id) {
        int index = 0;
        int length = products.length - 1;
        Product[] tmp = new Product[length];
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[index] = product;
                index++;
            }
        }
        products = tmp;
        return products;
    }
}

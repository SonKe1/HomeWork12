package ru.netology.manager;

import org.junit.jupiter.api.Test;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.ProductManager;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    ProductRepository repository = new ProductRepository();
    Product smartphone = new Smartphone(1, "iPhone", 1000, "Apple");
    Product smartphone1 = new Smartphone(2, " samsung a52", 850, "Samsung");
    Product smartphone2 = new Smartphone(3, "sony xperia1", 980, "Sony");
    Product book = new Book(4, "Код да Винчи", 110, "Дэн Браун");
    Product book1 = new Book(5, "Жизнь взаймы", 150, "Ремарк");
    Product book2 = new Book(6, "Война и мир", 120, "Толстой");
    Product smartphone3 = new Smartphone(7, "iPhone", 800, "Apple");


    @Test
    public void shouldSearchByText() {
        ProductManager manager = new ProductManager(repository);
        repository.save(book = new Book(4, "Код да Винчи", 110, "Дэн Браун"));
        repository.save(smartphone1 = new Smartphone(2, " samsung a52", 850, "Samsung"));
        repository.save(book1 = new Book(5, "Жизнь взаймы", 150, "Ремарк"));
        String text = "Жизнь взаймы";
        Product[] expected = {book1};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByIfNotExistText() {
        ProductManager manager = new ProductManager(repository);
        repository.save(book2);
        repository.save(smartphone2);
        repository.save(book1);
        String text = "Колобок";
        Product[] expected = {};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfMoreThanOneProduct() {
        ProductManager manager = new ProductManager(repository);
        repository.save(book = new Book(4, "Код да Винчи", 110, "Дэн Браун"));
        repository.save(smartphone = new Smartphone(1, "iPhone", 1000, "Apple"));
        repository.save(book1 = new Book(5, "Жизнь взаймы", 150, "Ремарк"));
        repository.save(smartphone3 = new Smartphone(7, "iPhone", 800, "Apple"));
        String text = "iPhone";
        Product[] expected = {smartphone,smartphone3};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldMatchesIfNotContainsSearch() {
        ProductManager manager = new ProductManager(repository);
        Product product = new Smartphone(2, " samsung a52", 850, "Samsung");
        repository.save(book);
        repository.save(product);
        repository.save(book2);
        repository.save(smartphone3);
        String search = "Стихи";
        boolean match = manager.matches(product, search);

        assertFalse(match);
    }

    @Test
    void shouldMatchesIfContainsSearch() {
        ProductManager manager = new ProductManager(repository);
        Product product = (smartphone);
        repository.save(book);
        repository.save(product);
        repository.save(book2);
        repository.save(smartphone3);
        String search = "Apple";
        boolean match = manager.matches(product, search);

        assertFalse(match);
    }
}

package ru.netology.repository;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {

    Product smartphone = new Smartphone(1, " iPhone 13", 1000, "Apple");
    Product smartphone1 = new Smartphone(2, " samsung a52", 850, "Samsung");
    Product smartphone2 = new Smartphone(3, "sony xperia1", 980, "Sony");
    Product book = new Book(4, "Код да Винчи", 110, "Дэн Браун");
    Product book1 = new Book(5, "Жизнь взаймы", 150, "Ремарк");
    Product book2 = new Book(6, "Война и мир", 120, "Толстой");
    Product smartphone3 = new Smartphone(7, " iPhone 8", 800, "Apple");

    @Test
    public void shouldSaveFirstProducts() {
        ProductRepository repository = new ProductRepository();
        Product book = new Book(4, "Код да Винчи", 110, "Дэн Браун");
        repository.save(book);
        Product[] expected = {book};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSaveAnyProducts() {
        ProductRepository repository = new ProductRepository();
        Product book = new Book(4, "Код да Винчи", 110, "Дэн Браун");
        Product smartphone1 = new Smartphone(2, " samsung a52", 850, "Samsung");
        repository.save(book);
        repository.save(smartphone1);
        Product[] expected = {book, smartphone1};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        ProductRepository repository = new ProductRepository();
        Product book = new Book(4, "Код да Винчи", 110, "Браун");
        Product smartphone1 = new Smartphone(2, " samsung a52", 850, "Samsung");
        repository.save(book);
        repository.save(smartphone1);
        repository.deleteById(2);
        Product[] expected = {book};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByWrongId() {
        ProductRepository repository = new ProductRepository();
        Product book = new Book(4, "Код да Винчи", 110, "Дэн Браун");
        Product smartphone = new Smartphone(1, " iPhone 13", 1000, "Apple");
        Product book2 = new Book(4, "Война и мир", 120, "Толстой");
        repository.save(book);
        repository.save(smartphone);
        repository.deleteById(4);
        Product[] expected = {smartphone};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

}

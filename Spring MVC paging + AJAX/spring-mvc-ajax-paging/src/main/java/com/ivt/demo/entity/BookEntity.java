package com.ivt.demo.entity;

public class BookEntity {
    private int id;
    private String name;
    private String author;
    private double price;
    private String isbn;

    public BookEntity(int id, String name, String author, double price, String isbn) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }

    public BookEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

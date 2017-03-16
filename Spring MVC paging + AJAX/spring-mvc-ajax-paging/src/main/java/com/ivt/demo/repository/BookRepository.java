package com.ivt.demo.repository;


import com.ivt.demo.entity.BookEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private static final int dataPerPage = 7;
    private List<BookEntity> bookEntityList;

    @PostConstruct
    public void init(){
        bookEntityList = new ArrayList<BookEntity>(){{
            for(int i = 1; i<=150; i++) {
                add(new BookEntity(i, "Java A-Z, chapter " + i, "Author_" + i, 12 + i, "3-12-675495-" + i));
            }
        }};
    }

    public List<BookEntity> getBooks(){
        return bookEntityList;
    }

    public List<BookEntity> getBooks(int page){
        // simulate paging, you can replace by MySQL data retrieving for example.
        List<BookEntity> result = new ArrayList<>();
        int start = (page - 1) * dataPerPage;
        for (int i = start; i < start + dataPerPage; i++) {
            result.add(bookEntityList.get(i));
        }
        return result;
    }
}

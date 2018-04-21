package com.carmanager.carmanager.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResponse<T> extends Response {
    private List<T> objects;
    private int currentPage;
    private int totalElements;

    public PageResponse(Page<T> objects) {
        this.currentPage = objects.getNumber();
        this.totalElements = objects.getNumberOfElements();

        this.objects = objects.getContent();
    }
}
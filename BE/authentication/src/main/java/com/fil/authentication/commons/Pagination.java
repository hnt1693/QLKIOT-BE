package com.fil.authentication.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Pagination {
    private int page;
    private int pageSize;
    private int total;

    public Pagination() {
    }

    public Pagination(int page, int pageSize, int total) {
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
    }
}

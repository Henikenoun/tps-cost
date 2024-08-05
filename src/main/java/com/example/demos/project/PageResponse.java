package com.example.demos.project;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> content;
    private int number;
    private int size;
    private long totalElements;
    private boolean first;
    private boolean last;

    public PageResponse(List<ProjectResponse> projectResponse, int number, int size, long totalElements, int totalPages, boolean first, boolean last) {
    }
}
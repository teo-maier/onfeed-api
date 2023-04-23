package com.project.onfeedapi.utils;

import com.project.onfeedapi.dto.PaginatedRequestDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PaginationUtils {
    public static Pageable createPageable(final PaginatedRequestDTO request) {
        return request == null || request.getPageNumber() == null || request.getPageSize() == null ? null : PageRequest.of(request.getPageNumber(), request.getPageSize());
    }

    public static Pageable createPageableWithSort(final PaginatedRequestDTO request) {
        if (request == null) {
            return null;
        }
        if (request.getSortField() == null || request.getSortDirection() == null) {
            return createPageable(request);
        }
        return request.getPageNumber() == null || request.getPageSize() == null || request.getSortDirection() == null || request.getSortField() == null ? null : PageRequest.of(request.getPageNumber(), request.getPageSize(), request.getSortDirection(), request.getSortField());
    }
}


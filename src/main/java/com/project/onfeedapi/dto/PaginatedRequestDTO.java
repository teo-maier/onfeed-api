package com.project.onfeedapi.dto;

import lombok.*;
import org.springframework.data.domain.Sort;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaginatedRequestDTO {
    private String searchTerm;

    private Integer pageNumber;

    private Integer pageSize;

    private String sortField;

    private Sort.Direction sortDirection;
}



package com.project.onfeedapi.specification;

import com.project.onfeedapi.model.Employee;
import com.project.onfeedapi.specification.search.EmployeeSearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class EmployeeSpecification implements Specification<Employee> {

    private final EmployeeSearchCriteria criteria;

    public EmployeeSpecification(EmployeeSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (Objects.isNull(criteria.getValue()) || criteria.getValue().equals("")) {
            return null;
        }

        List<String> keys = criteria.getKeys();
        String value = criteria.getValue().trim().toLowerCase();

        Stream<Predicate> predicateStream = keys.stream().map(key -> {
            if (root.get(key).getJavaType() == String.class) {
                return builder.like(builder.lower(root.get(key)), "%" + value + "%");
            } else {
                return builder.equal(root.get(key), value);
            }
        });
        return predicateStream.reduce(builder::or).orElse(null);
    }
}

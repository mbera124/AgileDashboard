package com.react.reactspring.outlet.domain.specification;

import com.react.reactspring.outlet.domain.Outlet;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class OutletSpecification {
    public static Specification<Outlet> createSpecification(String search) {
        return (root, query, cb) -> {
            final ArrayList<Predicate> predicates = new ArrayList<>();
            if (search != null) {
                final String likeExpression = "%" + search + "%";
                predicates.add(
                        cb.or(
                                cb.like(root.get("outletName"), likeExpression),
                                cb.like(root.get("outletLocation"), likeExpression)
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}

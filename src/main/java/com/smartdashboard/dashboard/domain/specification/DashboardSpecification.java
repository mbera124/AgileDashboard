package com.smartdashboard.dashboard.domain.specification;

import com.smartdashboard.tasks.domain.DeveloperTasks;
import com.smartdashboard.tasks.domain.Enumeration.Developers;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;

public class DashboardSpecification {
    private DashboardSpecification(){
        super();
    }
    public static Specification<DeveloperTasks> createSpecification(Developers developers, LocalDate date){
        return (root, query, cb) -> {

            final ArrayList<Predicate> predicates = new ArrayList<>();


            if (developers != null) {
                predicates.add(cb.equal(root.get("developers"), developers));
            }
            if (date != null) {
                predicates.add(cb.equal(root.get("tasks").get("endDate"), date));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }

}



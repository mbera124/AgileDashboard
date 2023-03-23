package com.react.reactspring.outlet.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OutletRepository extends JpaRepository<Outlet, Long>, JpaSpecificationExecutor <Outlet> {
}

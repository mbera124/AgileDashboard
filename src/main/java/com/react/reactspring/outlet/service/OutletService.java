package com.react.reactspring.outlet.service;

import com.react.reactspring.outlet.data.OutletData;
import com.react.reactspring.outlet.domain.Outlet;
import com.react.reactspring.outlet.domain.OutletRepository;
import com.react.reactspring.outlet.domain.specification.OutletSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutletService {
    public final OutletRepository outletRepository;

    public List<Outlet> getAllOutlets (String search) {
        Specification<Outlet> spec = OutletSpecification.createSpecification(search);
        return outletRepository.findAll(spec);
    }

    public OutletData createOutlet (OutletData data) {
        Outlet outlet = map(data);

        return outletRepository.save(outlet).toData();
    }

    public Outlet map (OutletData data) {
        Outlet outlet = new Outlet();
        outlet.setOutletName(data.getOutletName());
        outlet.setOutletLocation(data.getOutletLocation());

        return outlet;
    }

    public void deleteOutlet (Long id) {
        Outlet outlet = findOutletById (id);

        outletRepository.delete(outlet);
    }

    public OutletData updateOutlet (Long id, OutletData data) {
        Outlet outlet = findOutletById (id);

        outlet.setOutletName(data.getOutletName());
        outlet.setOutletLocation(data.getOutletLocation());

        return outletRepository.save(outlet).toData();
    }

    public Outlet findOutletById (Long id) {
        Optional<Outlet> optionalOutlet = outletRepository.findById(id);

        if (!optionalOutlet.isPresent()) {
            System.out.println("Missing Outlet with Id "+id);
        }

        return optionalOutlet.get();
    }
}

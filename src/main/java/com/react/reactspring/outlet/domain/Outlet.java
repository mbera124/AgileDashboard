package com.react.reactspring.outlet.domain;

import com.react.reactspring.outlet.data.OutletData;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="outlets")
public class Outlet {
    @Id
    @GeneratedValue
    private Long outletId;
    private String outletName;
    private String outletLocation;

    public OutletData toData () {
        OutletData data = new OutletData();
        data.setOutletName(this.outletName);
        data.setOutletLocation(this.outletLocation);

        return data;
    }
}


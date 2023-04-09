package com.smartdashboard.modules.domain;

import com.smartdashboard.modules.domain.Enumeration.ModuleNames;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String taskcount;
    @Enumerated(EnumType.STRING)
    private ModuleNames module;

}

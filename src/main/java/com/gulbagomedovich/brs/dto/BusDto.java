package com.gulbagomedovich.brs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gulbagomedovich.brs.model.Bus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusDto {

    private String code;
    private String model;

    private int capacity;

    public static BusDto toBusDto(Bus bus) {
        return new BusDto()
                .setCode(bus.getCode())
                .setModel(bus.getModel())
                .setCapacity(bus.getCapacity());
    }

}

package com.gulbagomedovich.brs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gulbagomedovich.brs.model.Stop;
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
public class StopDto implements Comparable {

    private String code;
    private String name;
    private String details;

    @Override
    public int compareTo(Object o) {
        return this.getName().compareTo(((StopDto) o).getName());
    }

    public static StopDto toStopDto(Stop stop) {
        return new StopDto()
                .setCode(stop.getCode())
                .setName(stop.getName())
                .setDetails(stop.getDetails());
    }

}

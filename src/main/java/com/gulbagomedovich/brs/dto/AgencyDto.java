package com.gulbagomedovich.brs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gulbagomedovich.brs.model.Agency;
import com.gulbagomedovich.brs.model.Bus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyDto {

    private String code;
    private String name;
    private String details;

    private UserDto owner;

    public static AgencyDto toAgencyDto(Agency agency) {
        return new AgencyDto()
                .setCode(agency.getCode())
                .setName(agency.getName())
                .setDetails(agency.getDetails())
                .setOwner(UserDto.toUserDto(agency.getOwner()));
    }

}

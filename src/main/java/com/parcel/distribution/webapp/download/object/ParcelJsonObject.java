package com.parcel.distribution.webapp.download.object;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ParcelJsonObject {

    private String id;
    private String code;
}

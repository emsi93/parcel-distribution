package com.parcel.distribution.utils;

import com.parcel.distribution.db.entity.Courier;

import java.util.List;

public class FinderCourierUtil {

    public Courier findTheNearestCourier(List<Courier> courierList, Coordinates coordinates) {
        int indexCourier = 0;
        double lenght = Math.sqrt(Math.pow(coordinates.getLat() - courierList.get(indexCourier).getLat(), 2) + Math.pow(coordinates.getLng() - courierList.get(indexCourier).getLng(), 2));
        for (int i = 1; i < courierList.size(); i++) {
            double lenghtTmp = Math.sqrt(Math.pow(coordinates.getLat() - courierList.get(i).getLat(), 2) + Math.pow(coordinates.getLng() - courierList.get(i).getLng(), 2));
            if (lenghtTmp < lenght) {
                lenght = lenghtTmp;
                indexCourier = i;
            }
        }
        return courierList.get(indexCourier);
    }
}

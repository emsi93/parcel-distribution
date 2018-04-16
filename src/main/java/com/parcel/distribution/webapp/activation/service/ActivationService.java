package com.parcel.distribution.webapp.activation.service;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActivationService {

    ModelAndView activeUser(HttpServletRequest request, HttpServletResponse response);
}

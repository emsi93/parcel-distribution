package com.parcel.distribution.webapp.parcel.service.impl;

import com.parcel.distribution.db.entity.Parcel;
import com.parcel.distribution.db.entity.ParcelInfo;
import com.parcel.distribution.db.entity.Recipient;
import com.parcel.distribution.db.entity.User;
import com.parcel.distribution.db.repository.ParcelRepository;
import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.utils.Code;
import com.parcel.distribution.webapp.email.service.EmailService;
import com.parcel.distribution.webapp.parcel.form.ParcelForm;
import com.parcel.distribution.webapp.parcel.service.ParcelService;
import com.parcel.distribution.webapp.parcel.validator.ParcelFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.security.Principal;

@Slf4j
@Service
@Transactional
@ConfigurationProperties(prefix = "application")
public class ParcelServiceImpl implements ParcelService {

    private final static String NEW_PARCEL_VIEW_JSP = "parcel/newparcel";

    @Autowired
    private ParcelFormValidator parcelFormValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public ModelAndView newParcel(ParcelForm parcelForm) {
        ModelAndView modelAndView = new ModelAndView(NEW_PARCEL_VIEW_JSP);
        modelAndView.addObject("parcelForm", parcelForm);
        return modelAndView;
    }

    @Override
    public ModelAndView newParcel(Principal principal, ParcelForm parcelForm, BindingResult bindingResult) {
        parcelFormValidator.validate(parcelForm, bindingResult);
        if (bindingResult.hasErrors()){
            return newParcel(parcelForm);
        }else{
            Recipient recipient = createRecipient(parcelForm);
            ParcelInfo parcelInfo = createParcelInfo(parcelForm);
            User user = userRepository.findByLogin(principal.getName());

            Parcel parcel = new Parcel();
            parcel.setUser(user);
            parcel.setStatus(true);
            parcel.setParcelInfo(parcelInfo);
            parcel.setRecipient(recipient);
            parcel.setCode(Code.generate());
            parcel.setDescription(parcelForm.getDescription());

            parcelRepository.save(parcel);
            return newParcel(parcelForm);
        }
    }

    @Override
    public ModelAndView getParcelList(Principal principal) {
        return null;
    }

    private ParcelInfo createParcelInfo(ParcelForm parcelForm) {
        ParcelInfo parcelInfo = new ParcelInfo();
        parcelInfo.setX(parcelForm.getX());
        parcelInfo.setY(parcelForm.getY());
        parcelInfo.setZ(parcelForm.getZ());
        parcelInfo.setWeight(parcelForm.getWeight());
        parcelInfo.setPrice(10.0);
        return parcelInfo;
    }

    private Recipient createRecipient(ParcelForm parcelForm){
        Recipient recipient = new Recipient();
        recipient.setName(parcelForm.getName());
        recipient.setSurname(parcelForm.getSurname());
        recipient.setPhoneNumber(parcelForm.getPhoneNumber());
        recipient.setEmail(parcelForm.getEmail());
        recipient.setStreet(parcelForm.getStreet());
        recipient.setStreetNumber(parcelForm.getStreetNumber());
        recipient.setFlatNumber(parcelForm.getFlatNumber());
        recipient.setPostCode(parcelForm.getPostCode());
        recipient.setCity(parcelForm.getCity());
        return recipient;
    }
}

package com.parcel.distribution.webapp.parcel.service.impl;

import com.parcel.distribution.db.entity.*;
import com.parcel.distribution.db.repository.CourierRepository;
import com.parcel.distribution.db.repository.ParcelRepository;
import com.parcel.distribution.db.repository.RecipientRepository;
import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.utils.Code;
import com.parcel.distribution.utils.Coordinates;
import com.parcel.distribution.utils.FinderCourierUtil;
import com.parcel.distribution.utils.OpenStreetMapUtils;
import com.parcel.distribution.webapp.parcel.form.DescriptionForm;
import com.parcel.distribution.webapp.parcel.form.ParcelForm;
import com.parcel.distribution.webapp.parcel.service.ParcelService;
import com.parcel.distribution.webapp.parcel.validator.DescriptionFormValidator;
import com.parcel.distribution.webapp.parcel.validator.ParcelFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Slf4j
@Service
@Transactional
@ConfigurationProperties(prefix = "application")
public class ParcelServiceImpl implements ParcelService {

    private static final String NEW_PARCEL_VIEW_JSP = "parcel/newparcel";
    private static final String NEW_PARCEL_VIEW_JSP2 = "parcel/newparcel2";
    private static final String NEW_PARCEL_VIEW_SUCCESS_JSP = "parcel/newparcel_success";
    private static final String PARCEL_LIST_VIEW_SUCCESS_JSP = "parcel/list";
    private static final String PARCEL_DETAILS_VIEW_SUCCESS_JSP = "parcel/details";

    @Autowired
    private ParcelFormValidator parcelFormValidator;

    @Autowired
    private DescriptionFormValidator descriptionFormValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private CourierRepository courierRepository;

//    @Autowired
//    private EmailService emailService;

    @Override
    public ModelAndView newParcel(Principal principal, ParcelForm parcelForm) {
        ModelAndView modelAndView = new ModelAndView(NEW_PARCEL_VIEW_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        modelAndView.addObject("parcelForm", parcelForm);
        return modelAndView;
    }

    @Override
    public ModelAndView newParcel(Principal principal, ParcelForm parcelForm, BindingResult bindingResult) throws JSONException {
        parcelFormValidator.validate(parcelForm, bindingResult);
        if (bindingResult.hasErrors()){
            return newParcel(principal, parcelForm);
        }else{
            Recipient recipient = createRecipient(parcelForm);
            ParcelInfo parcelInfo = createParcelInfo(parcelForm);
            User user = userRepository.findByLogin(principal.getName());

            if("yes".equals(parcelForm.getSaveOrNo())){
                recipient.setUser(user);
               // recipientRepository.save(recipient);
            }

            Parcel parcel = new Parcel();
            parcel.setUser(user);
            parcel.setStatus("DO ODEBRANIA");
            parcel.setParcelInfo(parcelInfo);
            parcel.setRecipient(recipient);
            parcel.setCode(Code.generate());
            parcel.setDescription(parcelForm.getDescription());

            StringBuilder address = new StringBuilder();
            address.append(parcel.getRecipient().getStreet() + " " + parcel.getRecipient().getStreetNumber() + ",");
            address.append(parcel.getRecipient().getPostCode() + " " + parcel.getRecipient().getCity());
            Coordinates coordinates = OpenStreetMapUtils.getInstance().getCoordinates(address.toString());

            List<Courier> courierList = courierRepository.findByActive(true);
            FinderCourierUtil finder = new FinderCourierUtil();
            Courier courier = finder.findTheNearestCourier(courierList, coordinates);
            parcel.setCourier(courier);
            parcelRepository.save(parcel);
            return newParcelSuccess(principal);
        }
    }

    @Override
    public ModelAndView newParcelWithContact(int idRecipient, Principal principal, DescriptionForm descriptionForm) {
        ModelAndView modelAndView = new ModelAndView(NEW_PARCEL_VIEW_JSP2);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        modelAndView.addObject("parcelForm", descriptionForm);
        modelAndView.addObject("idRecipient",idRecipient);
        return modelAndView;
    }

    @Override
    public ModelAndView newParcelWithContact(int idRecipient, Principal principal, DescriptionForm descriptionForm, BindingResult bindingResult) throws JSONException {
        descriptionFormValidator.validate(descriptionForm, bindingResult);
        if(bindingResult.hasErrors())
            return newParcelWithContact(idRecipient, principal, descriptionForm);
        else{
            Recipient recipient = recipientRepository.findById(idRecipient);
            ParcelInfo parcelInfo = createParcelInfo(descriptionForm);
            User user = userRepository.findByLogin(principal.getName());

            Parcel parcel = new Parcel();
            parcel.setUser(user);
            parcel.setStatus("DO ODEBRANIA");
            parcel.setParcelInfo(parcelInfo);
            parcel.setRecipient(recipient);
            parcel.setCode(Code.generate());
            parcel.setDescription(descriptionForm.getDescription());

            StringBuilder address = new StringBuilder();
            address.append(parcel.getRecipient().getStreet() + " " + parcel.getRecipient().getStreetNumber() + ",");
            address.append(parcel.getRecipient().getPostCode() + " " + parcel.getRecipient().getCity());
            Coordinates coordinates = OpenStreetMapUtils.getInstance().getCoordinates(address.toString());

            List<Courier> courierList = courierRepository.findByActive(true);
            FinderCourierUtil finder = new FinderCourierUtil();
            Courier courier = finder.findTheNearestCourier(courierList, coordinates);

            parcel.setCourier(courier);
            parcelRepository.save(parcel);
            //emailService.sendToCourier(courierList.get(courier), parcel).

            return newParcelSuccess(principal);
        }
    }

    @Override
    public ModelAndView getParcelList(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(PARCEL_LIST_VIEW_SUCCESS_JSP);
        User user = userRepository.findByLogin(principal.getName());
        modelAndView.addObject("role", user.getRole());
        modelAndView.addObject("username", user.getLogin());
        List<Parcel> parcelList = parcelRepository.findAllByUser(user);
        modelAndView.addObject("parcelList", parcelList);
        return modelAndView;
    }

    @Override
    public ModelAndView getDetails(Integer id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView(PARCEL_DETAILS_VIEW_SUCCESS_JSP);
        User user = userRepository.findByLogin(principal.getName());
        modelAndView.addObject("role", user.getRole());
        modelAndView.addObject("username", user.getLogin());
        Parcel parcel = parcelRepository.findById(id);
        modelAndView.addObject("parcel", parcel);
        return modelAndView;
    }

    private ModelAndView newParcelSuccess(Principal principal){
        ModelAndView modelAndView = new ModelAndView(NEW_PARCEL_VIEW_SUCCESS_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        return modelAndView;
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

    private ParcelInfo createParcelInfo(DescriptionForm descriptionForm) {
        ParcelInfo parcelInfo = new ParcelInfo();
        parcelInfo.setX(descriptionForm.getX());
        parcelInfo.setY(descriptionForm.getY());
        parcelInfo.setZ(descriptionForm.getZ());
        parcelInfo.setWeight(descriptionForm.getWeight());
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

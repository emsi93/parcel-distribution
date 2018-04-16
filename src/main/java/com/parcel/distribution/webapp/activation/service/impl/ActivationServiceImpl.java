package com.parcel.distribution.webapp.activation.service.impl;

import com.parcel.distribution.db.entity.Link;
import com.parcel.distribution.db.entity.User;
import com.parcel.distribution.db.repository.LinkRepository;
import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.webapp.activation.service.ActivationService;
import com.parcel.distribution.webapp.error.controller.ErrorController;
import com.parcel.distribution.webapp.security.controller.SecurityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class ActivationServiceImpl implements ActivationService {

    private static final String TYPE_LINK = "ACTIVATION";

    @Autowired
    private ErrorController errorController;

    @Autowired
    private SecurityController securityController;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${hours}")
    private String numberOfHours;

    @Override
    public ModelAndView activeUser(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        String email = getEmailFromToken(request, token);
        if (email == null) {
            return errorController.errorLink();
        } else {
            activeUser(email);
            linkRepository.deleteByEmailAndType(email, TYPE_LINK);
            return securityController.login();
        }
    }

    private void activeUser(String email) {
        User user = userRepository.findByEmail(email);
        user.setActive(true);
        userRepository.save(user);
    }

    private String getEmailFromToken(HttpServletRequest request, String token) {
        if (token != null) {
            String url = request.getRequestURL().toString() + "?" + "token" + "=" + token;
            long countByLink = linkRepository.countByLink(url);
            Link link = Optional.ofNullable(linkRepository.findByLinkAndType(url, TYPE_LINK)).orElse(new Link(null, null, null, null));
            if (validateLink(link, countByLink)) {
                return link.getEmail();
            } else
                return null;

        }
        return null;
    }

    private boolean validateLink(Link link, long countByLink) {
        String email = link.getEmail();
        if (email == null)
            return false;
        if (link.getData().plusHours(Long.parseLong(numberOfHours)).isBefore(LocalDateTime.now()))
            return false;
        long countByEmailAndType = linkRepository.countByEmailAndType(email, TYPE_LINK);
        boolean isValidLink = true;
        if (countByEmailAndType != 1 || countByLink != 1)
            isValidLink = false;
        return isValidLink;
    }

}

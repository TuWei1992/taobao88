package org.taobao88.taobao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.taobao88.taobao.enterprise.entity.*;
import org.taobao88.taobao.enterprise.service.*;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class LoginController extends  MainController{

    @Autowired
    private UserService userDAO;
    @Autowired
    private UserRoleService userRoleDAO;
    @Autowired
    private CountryRegCityService countryRegCityDAO;
    @Autowired
    private BalanceService balanceService;

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public String saveUser(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setAttribute("badRegistration","false");


        UserT userT = getParams(request);

        System.out.println(userT);
        System.out.println("dadasd = "+request.getParameter("step"));

        if(request.getParameter("step").equals(null) || request.getParameter("step").equals("")){
            List<UserT> userTList = userDAO.getAll();

            String bool = "false";

            for(int i=0;i<userTList.size();i++) {
                if(userT.getGmail().equals(userTList.get(i).getGmail()) || userT.getNameUser().equals(userTList.get(i).getNameUser()) && !"admin".equals(userTList.get(i).getNameUser())) {
                    request.setAttribute("badRegistration","true");
                    request.setAttribute("countryChoose","false");
                    request.setAttribute("user",userT);
                    bool = "true";

                    break;
                }
            }

            if(!"true".equals(bool)) {
            	userDAO.saveNewUser(userT);
            	userT.setIdUser(userDAO.getId(userT.getNameUser()).get(0).getIdUser());
            	
            	BalanceOperation bo = new BalanceOperation();
            	bo.setAmount(0);
            	bo.setCreatedAt(new Timestamp(new Date().getTime()));
            	bo.setUpdatedAt(new Timestamp(new Date().getTime()));
            	bo.setUserT(userT);
            	bo.setReason("Setting '0' balance after creating new user");
            	balanceService.adjustBalance(bo);

                int userId = userDAO.getId(request.getParameter("NAME")).get(0).getIdUser();

                UserRole userRole = new UserRole();
                userRole.setIdUser(userId);
                userRole.setAuthority("ROLE_USER");

                userRoleDAO.save(userRole);
            }

            request.setAttribute("countries",countryRegCityDAO.getAllCountry());
            request.setAttribute("regions",countryRegCityDAO.getAllRegion());
            request.setAttribute("cities", countryRegCityDAO.getAllCity());
        }
        if(request.getParameter("step").equals("stepRegion")) {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("badRegistration","false");
            request.setAttribute("countries",countryRegCityDAO.getAllCountry());

            request.setAttribute("user",userT);
            request.setAttribute("regions",countryRegCityDAO.getRegionsByID(Integer.parseInt(userT.getCountryUser())));
            request.setAttribute("badRegistration","true");
            request.setAttribute("countryChoose","true");
        }
        if(request.getParameter("step").equals("stepCity")) {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("badRegistration","false");
            request.setAttribute("countries",countryRegCityDAO.getAllCountry());

            request.setAttribute("user",userT);
            request.setAttribute("regions",countryRegCityDAO.getRegionsByID(Integer.parseInt(userT.getCountryUser())));
            request.setAttribute("cities",countryRegCityDAO.getCitiessByID(Integer.parseInt(userT.getRegionUser())));
            request.setAttribute("badRegistration","true");
            request.setAttribute("countryChoose","true");
        }
        return "login";

    }

    public UserT getParams(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        UserT user = new UserT();

        user.setNameUser(request.getParameter("NAME"));
        user.setGmail(request.getParameter("EMAIL"));
        user.setPassword(request.getParameter("PASSWORD"));
        user.setFullNameUser(request.getParameter("NAMEUSER"));
        user.setFemailUser(request.getParameter("FEMAIL"));
        user.setPatronymicUser(request.getParameter("PATRONYMIC"));
        user.setIndexUserT(request.getParameter("INDEX"));

       // System.out.println(Integer.parseInt(request.getParameter("idCountry")));
        user.setCountryUser(request.getParameter("idCountry"));
        user.setRegionUser(request.getParameter("idRegion"));
        user.setCityUser(request.getParameter("idCity"));
        user.setStreetUser(request.getParameter("STREET"));
        user.setHouseUser(request.getParameter("HOUSE"));
        user.setRoomUser(request.getParameter("ROOM"));
        user.setEnabled(1);

        return user;

    }
 
     @RequestMapping(value="/login", method = RequestMethod.GET)
     public String login(HttpServletRequest request) throws UnsupportedEncodingException {
         request.setCharacterEncoding("UTF-8");
         request.setAttribute("badRegistration","false");
         request.setAttribute("countries",countryRegCityDAO.getAllCountry());

      return "login";

     }

     @RequestMapping(value="/loginfailed", method = RequestMethod.GET)
     public String loginerror(ModelMap model,HttpServletRequest request) throws UnsupportedEncodingException {
         request.setCharacterEncoding("UTF-8");
      model.addAttribute("error", "true");
         request.setAttribute("badRegistration","false");
         request.setAttribute("countries",countryRegCityDAO.getAllCountry());
      return "login";

     }

     @RequestMapping(value="/logout", method = RequestMethod.GET)
     public String logout(HttpServletRequest request) throws UnsupportedEncodingException {
         request.setAttribute("badRegistration","false");
         request.setAttribute("countries",countryRegCityDAO.getAllCountry());
         request.setCharacterEncoding("UTF-8");
      return "login";

     }

    @RequestMapping(value="/newPassword", method = RequestMethod.GET)
    public String newPassword(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("goodMail","false");
        return "loginRemain";
    }

    @RequestMapping(value="/remainPassword", method = RequestMethod.POST)
    public String remainPassword(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String email = (String) request.getParameter("username");
        request.setAttribute("goodMail","false");
        request.setAttribute("badMail","true");

        List<UserT> userTList = userDAO.getAll();


        for(int i=0;i<userTList.size();i++) {
            System.out.println(userTList.get(i));
            if(email.equals(userTList.get(i).getGmail()) && !"admin".equals(userTList.get(i).getNameUser())) {
                sendNewPassword(userTList.get(i));
                request.setAttribute("goodMail","true");
                request.setAttribute("badMail","false");
                break;
            }
        }

        return "loginRemain";
    }
 
}

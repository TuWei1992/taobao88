package org.taobao88.taobao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.taobao88.taobao.beans.OrderBEAN;
import org.taobao88.taobao.enterprise.entity.*;
import org.taobao88.taobao.enterprise.service.*;
import org.taobao88.taobao.mail.MailMail;
import org.taobao88.taobao.mail.Templates;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by User on 12.06.14.
 */
public class MainController {

    @Autowired
    private OrderService orderDAO;
    @Autowired private UserService userDAO;
    @Autowired private GoodsService goodsDAO;
    @Autowired private OrderStatusService orderStatusDAO;
    @Autowired private PackageService packageDAO;
    @Autowired private PackageStatusService packageStatusDAO;
    @Autowired private MailService mailService;
    
    private final int WEIGHT_LIMIT = 20000;

    public Goods getObjectGoods(HttpServletRequest request) throws UnsupportedEncodingException {
        //HttpSession sesion = request.getSession(true);

        Goods good = new Goods();
        System.out.println(request.getParameter("HREFGOODS"));
        good.setHrefGoods(request.getParameter("HREFGOODS"));
        if(request.getParameter("AMOUNTGOODS") != null) {
            good.setAmountGoods(Integer.parseInt(request.getParameter("AMOUNTGOODS")));
            good.setWeightGoods(Double.parseDouble(request.getParameter("WEIGHTGOODS")));
            good.setPriceGoods(getPriceOfOrder(Integer.parseInt(request.getParameter("AMOUNTGOODS")),Double.parseDouble(request.getParameter("PRICEGOODS"))));
        }


        good.setColorGoods(request.getParameter("COLORGOODS"));
        good.setSizeGoods(request.getParameter("SIZEGOODS"));
        good.setNameGoods(request.getParameter("NAMEGOODS"));
        good.setChinaGoods(request.getParameter("CHINAGOODS"));
        good.setPhotoGoods(request.getParameter("PHOTOGOODS"));
        good.setComplexGoods(request.getParameter("COMPLEXGOODS"));

        return good;

    }

    public Goods getObjectGoodsForUpdate(HttpServletRequest request,Goods good) throws UnsupportedEncodingException {
        good.setHrefGoods(request.getParameter("HREFGOODS"));
        
            good.setAmountGoods(Integer.parseInt(request.getParameter("AMOUNTGOODS")));
            good.setWeightGoods(Double.parseDouble(request.getParameter("WEIGHTGOODS")));
            good.setPriceGoods(getPriceOfOrder(Integer.parseInt(request.getParameter("AMOUNTGOODS")),Double.parseDouble(request.getParameter("PRICEGOODS"))));
     

        good.setColorGoods(request.getParameter("COLORGOODS"));
        good.setSizeGoods(request.getParameter("SIZEGOODS"));
        good.setNameGoods(request.getParameter("NAMEGOODS"));
        good.setChinaGoods(request.getParameter("CHINAGOODS"));
        good.setPhotoGoods(request.getParameter("PHOTOGOODS"));
        good.setComplexGoods(request.getParameter("COMPLEXGOODS"));

        return good;

    }

    private double getPriceOfOrder(int amount, double priceForOne){
        double price = 0;

        price = (priceForOne*10)*1.1*0.19;

        price *= amount;

        price = new BigDecimal(price).setScale(2, RoundingMode.UP).doubleValue();

        return price;
    }

    private int getFullPriceOfOrder(int idUser,double priceOrder,double weight, int amount){
        double price = 0;

        UserT userT = userDAO.findUserById(idUser);

        if(Integer.parseInt(userT.getCountryUser()) == 248) {
            price = priceOrder + getValueForBelarus((weight / 1000) * amount);
        } if(Integer.parseInt(userT.getCountryUser()) == 3159) {
            price = priceOrder + getValueForRussia((weight / 1000) * amount);
        } if(Integer.parseInt(userT.getCountryUser()) == 9908) {
            price = priceOrder + getValueForUkraine((weight / 1000) * amount);
        }

        return new BigDecimal(price).setScale(0, RoundingMode.UP).intValue();
    }

    public OrderT getObjectOrder(int idGoods,int idUser, int idOrderStatus, double price,double weight, int amount) throws UnsupportedEncodingException {
        //HttpSession sesion = request.getSession(true);
        OrderT orderT = null;
        Goods goods = goodsDAO.findEmployeeById(idGoods);
        if (goods != null) {
        	orderT = orderDAO.findByGoods(goods);
        } 
        if (orderT == null) {
        	orderT = new OrderT();
        }

        orderT.setApprove("false");
        orderT.setIdGoods(idGoods);
        orderT.setIdUser(idUser);
        orderT.setFullPrice(getFullPriceOfOrder(idUser,price,weight,amount));
        orderT.setIdOrderStatus(idOrderStatus);

        return orderT;

    }

    public List<OrderBEAN> getOrders(List<OrderT> orderTs, List<Goods> goodses, List<OrderStatus> ordersStatuses){
        List<OrderBEAN> listOrderBEAN = new ArrayList<OrderBEAN>();

        for(int i = 0; i < orderTs.size(); i++){

            System.out.println("STAT - " + ordersStatuses.get(i));

            OrderBEAN orderBEAN = new OrderBEAN();
            orderBEAN.setFullPrice(orderTs.get(i).getFullPrice());
            orderBEAN.setIdUser(orderTs.get(i).getIdUser());
            orderBEAN.setIdGoods(orderTs.get(i).getIdGoods());
            orderBEAN.setApprove(orderTs.get(i).getApprove());
            orderBEAN.setAmountGoods(goodses.get(i).getAmountGoods());
            orderBEAN.setHrefGoods(goodses.get(i).getHrefGoods());
            orderBEAN.setIdOrder(orderTs.get(i).getIdOrder());
            orderBEAN.setPriceGoods(goodses.get(i).getPriceGoods());
            orderBEAN.setWeightGoods(goodses.get(i).getWeightGoods());
            orderBEAN.setIdOrderStatus(orderTs.get(i).getIdOrderStatus());

            orderBEAN.setPhotoGoods(goodses.get(i).getPhotoGoods());
            orderBEAN.setNameGoods(goodses.get(i).getNameGoods());
            orderBEAN.setChinaGoods(goodses.get(i).getChinaGoods());
            orderBEAN.setSizeGoods(goodses.get(i).getSizeGoods());
            orderBEAN.setColorGoods(goodses.get(i).getColorGoods());
            orderBEAN.setComplexGoods(goodses.get(i).getComplexGoods());

            orderBEAN.setApproveStatus(ordersStatuses.get(i).getApprove());
            orderBEAN.setPayStatus(ordersStatuses.get(i).getPay());
            orderBEAN.setDoneStatus(ordersStatuses.get(i).getDone());
            orderBEAN.setReadyStatus(ordersStatuses.get(i).getReady());
            orderBEAN.setRansomStatus(ordersStatuses.get(i).getRansom());

            System.out.println("orderBEAN - " + orderBEAN);

            listOrderBEAN.add(orderBEAN);
        }
        return listOrderBEAN;
    }

    public OrderStatus getOrderStatus(){
        OrderStatus orderStatus = new OrderStatus();

        orderStatus.setApprove("false");
        orderStatus.setDone("false");
        orderStatus.setPay("false");
        orderStatus.setRansom("false");
        orderStatus.setReady("false");

        return orderStatus;
    }

    public PackageStatus getPackageStatus(){
        PackageStatus packageStatus = new PackageStatus();

        packageStatus.setApprovePackage("false");
        packageStatus.setEndPackage("false");
        packageStatus.setPayPackage("false");
        packageStatus.setRansomPackage("false");
        packageStatus.setReadyPackage("false");
        packageStatus.setImportPackage("false");

        return packageStatus;
    }

    public PackageStatus checkOnReady(List<OrderT> orderTs, PackageStatus packageStatus){
        ArrayList<OrderStatus> orderStatuses = new ArrayList<OrderStatus>();

        for(int i=0;i<orderTs.size();i++){
            OrderStatus orderStatus = orderStatusDAO.findStatusById(orderTs.get(i).getIdOrderStatus());
            orderStatuses.add(orderStatus);
        }

        for(int i=0;i<orderStatuses.size();i++){
            if(orderStatuses.get(i).getDone().equals("true")){
                packageStatus.setImportPackage("true");
            }else{
                packageStatus.setImportPackage("false");
                break;
            }
        }
        for(int i=0;i<orderStatuses.size();i++){
            if(orderStatuses.get(i).getApprove().equals("true")){
                packageStatus.setApprovePackage("true");
            }else{
                packageStatus.setApprovePackage("false");
                break;
            }
        }
        for(int i=0;i<orderStatuses.size();i++){

            if(orderStatuses.get(i).getPay().equals("true")){
                packageStatus.setPayPackage("true");
            }else{
                packageStatus.setPayPackage("false");
                break;
            }
        }
        for(int i=0;i<orderStatuses.size();i++){

            if(orderStatuses.get(i).getRansom().equals("true")){
                packageStatus.setRansomPackage("true");
            }else{
                packageStatus.setRansomPackage("false");
                break;
            }
        }
        for(int i=0;i<orderStatuses.size();i++){

            if(orderStatuses.get(i).getReady().equals("true")){
                packageStatus.setReadyPackage("true");
            }else{
                packageStatus.setReadyPackage("false");
                break;
            }
        }

        if(packageStatus.getReadyPackage() == "true") {
            PackageT packageT = packageDAO.findPackageById(orderTs.get(0).getPackageT().getIdPackage());
            packageT.setApprove("true");
            packageDAO.updatePackage(packageT);
        }

        return packageStatus;
    }


    public void sendMessage(UserT userT, PackageT packageT, int idPackage) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
//        MailMail m = (MailMail) context.getBean("mailMail");
        //String sender="gosha.egor.513@gmail.com";//write here sender gmail id
        ResourceBundle getPath = ResourceBundle.getBundle("mail");
        String from = getPath.getString("mailAdmin");
        String to = getPath.getString("mailReceiver");
        String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ru\" lang=\"ru\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>Заголовок страницы</title>\n" +
                "</head><body><img src='http://cs5818.vk.me/g31878503/a_7e51a447.jpg'>" +
                "<p><h3>ORDER FROM "+userT.getNameUser()+"</h3></p><table rules='all'>\n" +
                "        <tr><th>Number of package</th>\n" +
                "                <td>"+idPackage+"</td></tr>\n" +
                "        <tr><th>Gmail of user</th>\n" +
                "                <td>"+userT.getGmail()+"</td></tr>\n" +
                "        <tr><th>Full price of package</th>\n" +
                "                <td>"+packageT.getFullPrice()+"</td></tr>\n" +
                "        <tr><th>Date of order</th>\n" +
                "                <td>"+packageT.getDatePackage()+"</td></tr>\n" +
                "        <tr><th>Post service</th>\n" + 
                "				 <td>"+packageT.getPostService().getServiceName()+"</td></tr>\n" + 
                "</table></body></html>";
        mailService.sendSimpleMessage(from,to,"PACKAGE № "+idPackage,html);
    }

    public void sendNewPassword(UserT userT) {

        String newPassword = genRandom();
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail m = (MailMail) context.getBean("mailMail");
        //String sender="gosha.egor.513@gmail.com";//write here sender gmail id
        ResourceBundle getPath = ResourceBundle.getBundle("mail");
        String from = getPath.getString("mailAdmin");
        String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ru\" lang=\"ru\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>Заголовок страницы</title>\n" +
                "</head><body><img src='http://cs5818.vk.me/g31878503/a_7e51a447.jpg'>" +
                "<p>Hi, user <h3 style='color: blue;'>"+userT.getNameUser()+"</h3>, your new password : '"+newPassword+"'</p></body></html>";
        m.sendMail(from,userT.getGmail(),"Смена пароля",html);

        userT.setPassword(newPassword);
        userDAO.updateUser(userT);
    }

    private String genRandom() {
        String letters = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String newPassword = "";

        for(int i=0;i<6;i++) {
            Random rand = new Random();
            int randomNumber = rand.nextInt(letters.length()) + 1;

            newPassword += letters.charAt(randomNumber);
        }

        return newPassword;
    }

    public UserT getParamsUserSettings(HttpServletRequest request, UserT user) throws UnsupportedEncodingException {

        user.setNameUser(request.getParameter("NAME"));
        user.setGmail(request.getParameter("EMAIL"));
        user.setFullNameUser(request.getParameter("NAMEUSER"));
        user.setFemailUser(request.getParameter("FEMAIL"));
        user.setPatronymicUser(request.getParameter("PATRONYMIC"));
        // user.setIndexUser(222);

        // System.out.println(Integer.parseInt(request.getParameter("idCountry")));
        user.setCountryUser(request.getParameter("idCountry"));
        user.setRegionUser(request.getParameter("idRegion"));
        user.setCityUser(request.getParameter("idCity"));
        user.setStreetUser(request.getParameter("STREET"));
        user.setHouseUser(request.getParameter("HOUSE"));
        user.setRoomUser(request.getParameter("ROOM"));
        return user;

    }

    public List<PackageT> allPackagesForOneRequest(List<OrderT> orderTList, double price) {
        List<PackageT> rez = new ArrayList<PackageT>();
        List<Integer> rezInt = new ArrayList<Integer>();
        double weightOfGoodsInOneOrder = 0;
        double pricePackage = 0;
        double fullWeight = 0;
        for(OrderT o : orderTList) {
            Goods goods = goodsDAO.findEmployeeById(o.getIdGoods());
            fullWeight += goods.getWeightGoods() * goods.getAmountGoods();
        }
        if(fullWeight > WEIGHT_LIMIT) {
            for(int i=0;i<orderTList.size();i++) {
                orderTList.get(i).setApprove("true");
                Goods goods = goodsDAO.findEmployeeById(orderTList.get(i).getIdGoods());
                if((1*goods.getWeightGoods()*goods.getAmountGoods()+weightOfGoodsInOneOrder) <= WEIGHT_LIMIT) {
                    weightOfGoodsInOneOrder += goods.getWeightGoods()*goods.getAmountGoods();
                    rezInt.add(i);
                    pricePackage+=orderTList.get(i).getFullPrice();
                    if(orderTList.size()-1 == i) {
                        int idPackageStatus = packageStatusDAO.savePackageStatus(getPackageStatus());

                        PackageT packageT = new PackageT();
                        packageT.setApprove("false");
                        packageT.setIdPackageStatus(idPackageStatus);
                        packageT.setFullPrice((int) price);
                        packageT.setDatePackage(getCurrentDate());
                        packageT.setWeight(fullWeight);

                        pricePackage = 0;

                        int idPackage = packageDAO.addPackage(packageT);
                        rez.add(packageDAO.findPackageById(idPackage));
                        sendMessage(userDAO.findUserById(orderTList.get(0).getIdUser()), packageT, idPackage);

                        for(int j=0;j<rezInt.size();j++) {
                            orderTList.get(rezInt.get(j)).setPackageT(packageDAO.findPackageById(idPackage));
                            orderDAO.updateOrder(orderTList.get(rezInt.get(j)));
                        }
                        break;
                    }
                } else {
                    int idPackageStatus = packageStatusDAO.savePackageStatus(getPackageStatus());

                    PackageT packageT = new PackageT();
                    packageT.setApprove("false");
                    packageT.setIdPackageStatus(idPackageStatus);
                    packageT.setFullPrice((int) price);
                    packageT.setDatePackage(getCurrentDate());
                    packageT.setWeight(fullWeight);

                    pricePackage = 0;

                    int idPackage = packageDAO.addPackage(packageT);
                    rez.add(packageDAO.findPackageById(idPackage));
                    sendMessage(userDAO.findUserById(orderTList.get(0).getIdUser()), packageT, idPackage);

                    for(int j=0;j<rezInt.size();j++) {
                        orderTList.get(rezInt.get(j)).setPackageT(packageDAO.findPackageById(idPackage));
                        orderDAO.updateOrder(orderTList.get(rezInt.get(j)));
                    }
                    rezInt.clear();
                    weightOfGoodsInOneOrder = 0;
                    i=i-1;
               }
            }
        } else {
            int idPackageStatus = packageStatusDAO.savePackageStatus(getPackageStatus());

            PackageT packageT = new PackageT();
            packageT.setApprove("false");
            packageT.setIdPackageStatus(idPackageStatus);
            packageT.setWeight(fullWeight);
            int idPackage = packageDAO.addPackage(packageT);
            rez.add(packageDAO.findPackageById(idPackage));

            for(int i=0;i<orderTList.size();i++) {
                orderTList.get(i).setApprove("true");
                Goods goods = goodsDAO.findEmployeeById(orderTList.get(i).getIdGoods());
                pricePackage+=orderTList.get(i).getFullPrice();

                orderTList.get(i).setPackageT(packageDAO.findPackageById(idPackage));
                orderDAO.updateOrder(orderTList.get(i));
            }
            packageT.setFullPrice((int) price);
            packageT.setDatePackage(getCurrentDate());

            sendMessage(userDAO.findUserById(orderTList.get(0).getIdUser()),packageT,idPackage);
            packageDAO.updatePackage(packageT);
        }

        return  rez;
    }

    public List<OrderT> prepareOrdersFromGoods(Goods goods, UserT user) throws UnsupportedEncodingException {
    	List<OrderT> orders = new ArrayList<OrderT>();
    	double weight = 0;
        int amount = 0;
        double realWeight = goods.getWeightGoods() * goods.getAmountGoods();
        if (realWeight < WEIGHT_LIMIT) {
        	int idOrderStatus = orderStatusDAO.saveStatus(getOrderStatus());
        	OrderT ordert = orderDAO.findByGoods(goods);
        	if (ordert == null) {
        		ordert = new OrderT();
        		ordert.setIdOrder(orderDAO.addOrder(getObjectOrder(goods.getIdGoods(), user.getIdUser(), idOrderStatus, goods.getPriceGoods(), goods.getWeightGoods(), goods.getAmountGoods())));
        	}
            orders.add(orderDAO.findOrderById(ordert.getIdOrder()));
        } else {
        	for(int i = 1; i <= goods.getAmountGoods(); i++) {
                amount++;
                if((1 * goods.getWeightGoods() + weight) <= WEIGHT_LIMIT) {
                    weight += goods.getWeightGoods();
                    if(goods.getAmountGoods() == i) {
                        int idOrderStatus = orderStatusDAO.saveStatus(getOrderStatus());
                        int idNewOrder = orderDAO.addOrder(getObjectOrder(goods.getIdGoods(), user.getIdUser(), idOrderStatus, goods.getPriceGoods(), goods.getWeightGoods(), goods.getAmountGoods()));
                        orders.add(orderDAO.findOrderById(idNewOrder));
                        break;
                    }
                } else {
                    amount--;
                    if(amount == 0) {
                        amount++;
                    }
                    int idOrderStatus = orderStatusDAO.saveStatus(getOrderStatus());
                    int idNewOrder = orderDAO.addOrder(getObjectOrder(goods.getAmountGoods(), user.getIdUser(), idOrderStatus, goods.getPriceGoods(), goods.getWeightGoods(), goods.getAmountGoods()));
                    orders.add(orderDAO.findOrderById(idNewOrder));

                    weight = 0;
                    amount = 0;

                    i=i-1;
               }
            }
        }
        return orders;
    }
    
    public List<OrderT> allOrdersForOneRequest(int amountConst,Goods goods, int idUser) throws UnsupportedEncodingException {
        List<OrderT> rezOrders = new ArrayList<OrderT>();
        double weight = 0;
        int amount = amountConst;
        double realWeight = goods.getWeightGoods() * amountConst;
        if (realWeight > WEIGHT_LIMIT) {
            for(int i = 1; i <= amountConst; i++) {
                amount++;
                if((1 * goods.getWeightGoods() + weight) <= WEIGHT_LIMIT) {
                    weight += goods.getWeightGoods();
                    if(amountConst == i) {
                        Goods goodsNew = goods;
                        goodsNew.setAmountGoods(amount);

                        int idGoods = 0;
                        if (goodsNew.getIdGoods() != 0) {
                        	idGoods = goodsNew.getIdGoods();
                        	goodsDAO.updateEmployee(goodsNew);
                        } else {
                        	idGoods = goodsDAO.saveGoods(goodsNew);
                        }
                        int idOrderStatus = orderStatusDAO.saveStatus(getOrderStatus());

                        int idNewOrder = 0;
                        OrderT order = orderDAO.findByGoods(goodsNew);
                        if (order != null) {
                        	idNewOrder = order.getIdOrder();
                        	orderDAO.updateOrder(getObjectOrder(idGoods,idUser,idOrderStatus,goodsNew.getPriceGoods(),goodsNew.getWeightGoods(),goodsNew.getAmountGoods()));
                        } else {
                        	idNewOrder = orderDAO.addOrder(getObjectOrder(idGoods,idUser,idOrderStatus,goodsNew.getPriceGoods(),goodsNew.getWeightGoods(),goodsNew.getAmountGoods()));
                        }
                        rezOrders.add(orderDAO.findOrderById(idNewOrder));
                        break;
                    }
                } else {
                    amount--;
                    if(amount == 0) {
                        amount++;
                    }
                    Goods goodsNew = goods;
                    goodsNew.setAmountGoods(amount);

                    int idGoods = 0;
                    if (goodsNew.getIdGoods() != 0) {
                    	idGoods = goodsNew.getIdGoods();
                    	goodsDAO.updateEmployee(goodsNew);
                    } else {
                    	idGoods = goodsDAO.saveGoods(goodsNew);
                    }
                    int idOrderStatus = orderStatusDAO.saveStatus(getOrderStatus());

                    int idNewOrder = 0;
                    OrderT order = orderDAO.findByGoods(goodsNew);
                    if (order != null) {
                    	idNewOrder = order.getIdOrder();
                    	orderDAO.updateOrder(getObjectOrder(idGoods,idUser,idOrderStatus,goodsNew.getPriceGoods(),goodsNew.getWeightGoods(),goodsNew.getAmountGoods()));
                    } else {
                    	idNewOrder = orderDAO.addOrder(getObjectOrder(idGoods,idUser,idOrderStatus,goodsNew.getPriceGoods(),goodsNew.getWeightGoods(),goodsNew.getAmountGoods()));
                    }
                    rezOrders.add(orderDAO.findOrderById(idNewOrder));

                    weight = 0;
                    amount = 0;

                    i=i-1;
               }
            }
        } else {
        	Goods goodsNew = goods;
            goodsNew.setAmountGoods(amount);
        	int idGoods = 0;
            if (goodsNew.getIdGoods() != 0) {
            	idGoods = goodsNew.getIdGoods();
            	goodsDAO.updateEmployee(goodsNew);
            } else {
            	idGoods = goodsDAO.saveGoods(goodsNew);
            }
            int idOrderStatus = orderStatusDAO.saveStatus(getOrderStatus());

            int idNewOrder = 0;
            OrderT order = orderDAO.findByGoods(goodsNew);
            if (order != null) {
            	idNewOrder = order.getIdOrder();
            	orderDAO.updateOrder(getObjectOrder(idGoods,idUser,idOrderStatus,goodsNew.getPriceGoods(),goodsNew.getWeightGoods(),goodsNew.getAmountGoods()));
            } else {
            	idNewOrder = orderDAO.addOrder(getObjectOrder(idGoods,idUser,idOrderStatus,goodsNew.getPriceGoods(),goodsNew.getWeightGoods(),goodsNew.getAmountGoods()));
            }
            rezOrders.add(orderDAO.findOrderById(idNewOrder));
        }
        return  rezOrders;
    }

    private double getValueForBelarus(double weight){
        double D = 0;
        double Weight = 0;

        if(weight < 1.0){
            Weight = new BigDecimal(weight).setScale(1, RoundingMode.UP).doubleValue();
        }else{
            Weight = new BigDecimal(weight).setScale(0, RoundingMode.UP).doubleValue();
        }

        Map<Double,Integer> belarus = new HashMap<Double,Integer>();

        belarus.put(0.1,18);
        belarus.put(0.2,33);
        belarus.put(0.3,48);
        belarus.put(0.4,63);
        belarus.put(0.5,78);
        belarus.put(0.6,93);
        belarus.put(0.7,108);
        belarus.put(0.8,123);
        belarus.put(1.0,155);
        belarus.put(2.0,211);
        belarus.put(3.0,267);
        belarus.put(4.0,324);
        belarus.put(5.0,380);
        belarus.put(6.0,436);
        belarus.put(7.0,493);
        belarus.put(8.0,549);
        belarus.put(9.0,605);
        belarus.put(10.0,662);
        belarus.put(11.0,718);
        belarus.put(12.0,774);
        belarus.put(13.0,830);
        belarus.put(14.0,887);
        belarus.put(15.0,943);
        belarus.put(16.0,1003);
        belarus.put(17.0,1063);
        belarus.put(18.0,1123);
        belarus.put(19.0,1183);
        belarus.put(20.0,1243);

        for(Map.Entry<Double,Integer> entry : belarus.entrySet()){
            Double key = entry.getKey();
            if(key == Weight){
                D = entry.getValue();
                break;
            }
        }

        return D*0.19;
    }

    private double getValueForRussia(double weight){
        double D = 0;
        double Weight = 0;

        if(weight < 1.0){
            Weight = new BigDecimal(weight).setScale(1, RoundingMode.UP).doubleValue();
        }else{
            Weight = new BigDecimal(weight).setScale(0, RoundingMode.UP).doubleValue();
        }

        Map<Double,Integer> belarus = new HashMap<Double,Integer>();

        belarus.put(0.1,38);
        belarus.put(0.2,53);
        belarus.put(0.3,68);
        belarus.put(0.4,83);
        belarus.put(0.5,98);
        belarus.put(0.6,113);
        belarus.put(0.7,128);
        belarus.put(0.8,143);
        belarus.put(1.0,191);
        belarus.put(2.0,250);
        belarus.put(3.0,309);
        belarus.put(4.0,368);
        belarus.put(5.0,427);
        belarus.put(6.0,487);
        belarus.put(7.0,546);
        belarus.put(8.0,606);
        belarus.put(9.0,665);
        belarus.put(10.0,723);
        belarus.put(11.0,783);
        belarus.put(12.0,843);
        belarus.put(13.0,902);
        belarus.put(14.0,961);
        belarus.put(15.0,1021);
        belarus.put(16.0,1079);
        belarus.put(17.0,1139);
        belarus.put(18.0,1199);
        belarus.put(19.0,1258);
        belarus.put(20.0,1317);

        for(Map.Entry<Double,Integer> entry : belarus.entrySet()){
            Double key = entry.getKey();
            if(key == Weight){
                D = entry.getValue();
                break;
            }
        }

        return D*0.19;
    }

    private double getValueForUkraine(double weight){
        double D = 0;
        double Weight = 0;

        if(weight < 1.0){
            Weight = new BigDecimal(weight).setScale(1, RoundingMode.UP).doubleValue();
        }else{
            Weight = new BigDecimal(weight).setScale(0, RoundingMode.UP).doubleValue();
        }

        Map<Double,Integer> belarus = new HashMap<Double,Integer>();

        belarus.put(0.1,38);
        belarus.put(0.2,53);
        belarus.put(0.3,68);
        belarus.put(0.4,83);
        belarus.put(0.5,98);
        belarus.put(0.6,113);
        belarus.put(0.7,128);
        belarus.put(0.8,143);
        belarus.put(1.0,181);
        belarus.put(2.0,235);
        belarus.put(3.0,289);
        belarus.put(4.0,343);
        belarus.put(5.0,397);
        belarus.put(6.0,451);
        belarus.put(7.0,505);
        belarus.put(8.0,559);
        belarus.put(9.0,614);
        belarus.put(10.0,668);
        belarus.put(11.0,722);
        belarus.put(12.0,776);
        belarus.put(13.0,830);
        belarus.put(14.0,885);
        belarus.put(15.0,939);
        belarus.put(16.0,993);
        belarus.put(17.0,1047);
        belarus.put(18.0,1101);
        belarus.put(19.0,1155);
        belarus.put(20.0,1209);

        for(Map.Entry<Double,Integer> entry : belarus.entrySet()){
            Double key = entry.getKey();
            if(key == Weight){
                D = entry.getValue();
                break;
            }
        }

        return D*0.19;
    }

    public String getCurrentDate() {
        Date now = new Date();

        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String time = formatter.format(now);

        return time;
    }

    public List<PackageT> getListForFirstPage (List<PackageT> packageTList,HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        List<PackageT> rezList = new ArrayList<PackageT>();
        List<Integer> countOfPages = new ArrayList<Integer>();
        int size=0;

        if(session.getAttribute("sizeOfPages") == null) {
            session.setAttribute("sizeOfPages",1);
        }
        System.out.println(session.getAttribute("currentPage"));
        if(null == session.getAttribute("currentPage")) {
            session.setAttribute("currentPage", 1);
        } else {
            if(null == request.getParameter("currentPageFromPage")){
                session.setAttribute("currentPage", 1);
            }  else {
                session.setAttribute("currentPage",Integer.parseInt(request.getParameter("currentPageFromPage")));
            }
        }

        if(packageTList.size() <= 10) {
            countOfPages.add(1);
            session.setAttribute("countOfPages",countOfPages);
            return packageTList;
        } else {
            if(packageTList.size()%10 == 0){
                size=packageTList.size()/10;
            } else{
                size = packageTList.size()/10+1;
            }
            session.setAttribute("sizeOfPages",size);
            for(int i=1;i<=size;i++) {
                countOfPages.add(i);
            }
            session.setAttribute("countOfPages",countOfPages);

            int currentPage = (int) session.getAttribute("currentPage");
            
            if(currentPage == countOfPages.size()) {
                int start = ((int)session.getAttribute("currentPage") - 1)*10+1;
                for(int i = start; i<packageTList.size();i++) {
                    rezList.add(packageTList.get(i));
                }
            } else {
                int start = ((int)session.getAttribute("currentPage") - 1)*10;
                int end = (int)session.getAttribute("currentPage")*10;
                for(int i = start; i<end;i++) {
                    rezList.add(packageTList.get(i));
                }
            }
        }

        return rezList;
    }

    public List<OrderBEAN> getListForFirstPageOrder (List<OrderBEAN> packageTList,HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        List<OrderBEAN> rezList = new ArrayList<OrderBEAN>();
        List<Integer> countOfPages = new ArrayList<Integer>();
        int size=0;

        if(session.getAttribute("sizeOfPages") == null) {
            session.setAttribute("sizeOfPages",1);
        }
        System.out.println(session.getAttribute("currentPage"));
        if(null == session.getAttribute("currentPage")) {
            session.setAttribute("currentPage", 1);
        } else {
            if(null == request.getParameter("currentPageFromPage")){
                session.setAttribute("currentPage", 1);
            }  else {
                session.setAttribute("currentPage",Integer.parseInt(request.getParameter("currentPageFromPage")));
            }
        }

        if(packageTList.size() <= 10) {
            countOfPages.add(1);
            session.setAttribute("countOfPages",countOfPages);
            return packageTList;
        } else {
            if(packageTList.size()%10 == 0){
                size=packageTList.size()/10;
            } else{
                size = packageTList.size()/10+1;
            }
            session.setAttribute("sizeOfPages",size);
            for(int i=1;i<=size;i++) {
                countOfPages.add(i);
            }
            session.setAttribute("countOfPages",countOfPages);

            int currentPage = (int) session.getAttribute("currentPage");
            
            if(currentPage == countOfPages.size()) {
                int start = ((int)session.getAttribute("currentPage") - 1)*10+1;
                for(int i = start; i<packageTList.size();i++) {
                    rezList.add(packageTList.get(i));
                }
            } else {
                int start = ((int)session.getAttribute("currentPage") - 1)*10;
                int end = (int)session.getAttribute("currentPage")*10;
                for(int i = start; i<end;i++) {
                    rezList.add(packageTList.get(i));
                }
            }
        }

        return rezList;
    }
    
    public void sendToAdmin(String subject, String message) {
    	ResourceBundle getPath = ResourceBundle.getBundle("mail");
        String from = getPath.getString("mailAdmin");
        String to = getPath.getString("mailReceiver");
    	mailService.sendSimpleMessage(from, to, subject, message);
    }
}

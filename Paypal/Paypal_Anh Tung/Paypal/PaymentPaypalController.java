package vn.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.entity.*;
import vn.repository.*;
import vn.utils.Constants;
import vn.utils.MailMail;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by TungNguyen on 11/12/16.
 */
@Controller
public class PaymentPaypalController {

    @Autowired
    OnlineOrderRepository onlineOrderRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RoomOrderRepository roomOrderRepository;
    @Autowired
    AdminRepository userRepository;

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String payment() {
        return "/paypal/index";

    }


    @RequestMapping(value = "/paypal/success", method = RequestMethod.GET)
    public String success(@RequestParam(name = "tx") String transId, Model model, HttpSession session) throws ParseException, IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail sendMail = (MailMail) context.getBean("mailMail");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        //post du lieu len paypal de kiem tra giao dich -- bat dau
        //cau hinh du lieu goi di -- bat dau
        String str = "cmd=_notify-synch";
        String at = "at=4--rRE7EMD9vPPloPceJ6rkeUqaupg1A-jpEAGR5Q8f2rsiAUQxCHQ72Uda"; //token id
        String tx = "tx=" + transId;
        str = str + "&" + tx + "&" + at;
        //cau hinh du lieu goi di -- ket thuc

        URL u = new URL("https://www.sandbox.paypal.com/cgi-bin/webscr");
        URLConnection uc = u.openConnection();
        uc.setDoOutput(true);
        uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        PrintWriter pw = new PrintWriter(uc.getOutputStream());
        pw.println(str);
        pw.close();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(uc.getInputStream()));
        String res = in.readLine(); // success hay khong
        in.close();
        //post du lieu len paypal de kiem tra giao dich -- ket thuc

        if (res.equals("SUCCESS")) {

        /* ------------ SAVE ONLINE ORDER TO DB BEGINS -------------*/
            OnlineOrderEntity onlineOrder = new OnlineOrderEntity();
            String name = session.getAttribute("name").toString();
            String email = session.getAttribute("email").toString();
            String beginDate = session.getAttribute("beginDate").toString();
            String endDate = session.getAttribute("endDate").toString();
            int noDays = Integer.parseInt(session.getAttribute("noDays").toString());
            Long total = Long.parseLong(session.getAttribute("total").toString());
//        onlineOrder.setId(123);
            String[] listKindRoomBook = session.getAttribute("roomOrder").toString().split(",");
            int noRoomOrder = 0;
            for (int i = 0; i < listKindRoomBook.length; i++) {
                String[] singleKindRoomOrder = listKindRoomBook[i].split("-");
                noRoomOrder += Integer.parseInt(singleKindRoomOrder[1]);
            }
            onlineOrder.setMail(email);
            onlineOrder.setName(name);
            onlineOrder.setNumberOfRooms(noRoomOrder);
            onlineOrder.setCreateDate(new Date());
            onlineOrder.setBeginDate(df.parse(beginDate));
            onlineOrder.setEndDate(df.parse(endDate));
            //onlineOrder.setNumberOfGuests(4);
            onlineOrder.setDepositMoney(total);
            String bookCode = RandomStringUtils.randomNumeric(8);
            onlineOrder.setBookCode(bookCode);
            onlineOrderRepository.save(onlineOrder);
        /* ------------ SAVE ORDER TO DB ENDS -------------*/

            for (int i = 0; i < listKindRoomBook.length; i++) {
                String[] singleKindRoomOrder = listKindRoomBook[i].split("-");
                createSingleRoomOrder(beginDate, endDate, singleKindRoomOrder, onlineOrder, session);
            }
            sendMail.sendMail(onlineOrder);
            model.addAttribute("bookCode", bookCode);
            model.addAttribute("email", session.getAttribute("email").toString());
            return "home/bookdone";
        } else {
            return "error/500";
        }
    }

    private void createSingleRoomOrder(String beginDate, String endDate, String[] singleRoomOrder, OnlineOrderEntity onlineOrder, HttpSession session) throws ParseException {
        //Lấy ra danh sách phòng còn trống của 1 loại
        String beginDateReplace = beginDate.replace("-","");
        String endDateReplace = endDate.replace("-", "");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Long kindRoomId = Long.parseLong(singleRoomOrder[0]);
        int noRoom = Integer.parseInt(singleRoomOrder[1]);
        Long price = Long.parseLong(singleRoomOrder[2]);
        Long noDays = Long.parseLong(session.getAttribute("noDays").toString());
        Long deposit = price * noDays;
        List<String> roomNameNotAvailable = roomRepository.findRoomNameUnavailable(beginDateReplace, endDateReplace);
        List<RoomEntity> roomAvailable = new ArrayList<>();
        if (roomNameNotAvailable.size() != 0) {
            roomAvailable = roomRepository.findByNameNotInAndIsDeleted(roomNameNotAvailable, 0);
        } else {
            roomAvailable = roomRepository.findByIsDeleted(0);
        }
        for (int i = 0; i < roomAvailable.size(); i++) {
            if (roomAvailable.get(i).getKindRoom().getId() != kindRoomId) {
                roomAvailable.remove(roomAvailable.get(i));
            }
        }
        //Sau khi lấy ra list phòng thuộc loại đó avail thì tiến hành đặt theo số lượng
        List<UserEntity> listUser = userRepository.findById(1000);
        UserEntity user = listUser.get(0);
        CustomerEntity cust = new CustomerEntity();
        String name = session.getAttribute("name").toString();
        String email = session.getAttribute("email").toString();
        String telephone = session.getAttribute("telephone").toString();
        cust.setName(name);
        cust.setSoCMND("111111111");
        cust.setTelephone(telephone);
        cust.setEmail(email);
        customerRepository.save(cust);

        for (int i = 0; i < noRoom; i++) {
            RoomEntity room = roomAvailable.get(i);
            RoomOrderEntity roomOrderEntity = new RoomOrderEntity();
            roomOrderEntity.setIsDeleted(0);
            roomOrderEntity.setPrice(room.getKindRoom().getPrice());
            roomOrderEntity.setStatus(Constants.TRANGTHAI_HOADON_DANGDAT);
            Date ngayBatDau = df.parse(beginDate);
            Date ngayKetThuc = df.parse(endDate);
            roomOrderEntity.setBeginDate(ngayBatDau);
            roomOrderEntity.setEndDate(ngayKetThuc);
            roomOrderEntity.setDepositMoney(deposit);
            roomOrderEntity.setQuantityCust(1);
            roomOrderEntity.setRoom(room);
            roomOrderEntity.setCustomer(cust);
            roomOrderEntity.setUserCheckIn(user);
            roomOrderEntity.setUserCheckOut(user);
            roomOrderEntity.setOnlineOrder(onlineOrder);
            roomOrderRepository.save(roomOrderEntity);
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}

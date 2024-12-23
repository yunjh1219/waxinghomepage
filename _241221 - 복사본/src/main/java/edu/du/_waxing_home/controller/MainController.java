package edu.du._waxing_home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() { return "pages/main";}


    //People Menu
    @GetMapping("/peoplewaxing")
    public String peoplewaxing() { return "pages/people/peoplewaxing";}
    @GetMapping("/directions")
    public String directions() { return "pages/people/directions";}

    //Service Menu
    @GetMapping("/bodywaxing")
    public String bodywaxing() { return "pages/service/bodywaxing";}
    @GetMapping("/brazlianwaxing")
    public String brazlian() { return "pages/service/brazlianwaxing";}
    @GetMapping("/facewaxing")
    public String facewaxing() { return "pages/service/facewaxing";}
    @GetMapping("/pregnantwaxing")
    public String pregnant() { return "pages/service/pregnantwaxing";}

    //Community Menu
    @GetMapping("/event")
    public String event() { return "pages/community/event";}
    @GetMapping("/news")
    public String news() { return "pages/community/news";}
    @GetMapping("/review")
    public String review() { return "pages/community/review";}

    //Reservation Menu
    @GetMapping("/reservaion")
    public String reservation() { return "pages/reservation/reservation";}
    @GetMapping("/counseling")
    public String counseling() { return "pages/reservation/counseling";}

    //Cost Menu
    @GetMapping("/cost")
    public String cost() { return "pages/cost/cost";}

    //BestReview Menu
    @GetMapping("/bestreview")
    public String bestreview() { return "pages/bestreview/bestreview";}

    //Manager Menu
    @GetMapping("/guestlist")
    public String manger() { return "pages/manager/guestlist";}
    @GetMapping("/reservationlist")
    public String reservationlist() { return "pages/manager/reservationlist";}

    //Write form
    @GetMapping("/writenews")
    public String writenews() { return "pages/write/writenews";}
    @GetMapping("/writereview")
    public String writereview() { return "pages/write/writereview";}

}

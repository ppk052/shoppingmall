package mongnam.spring.shoppingmall.controller;

import lombok.extern.slf4j.Slf4j;
import mongnam.spring.shoppingmall.dto.ItemDto;
import mongnam.spring.shoppingmall.dto.ParamDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.*;
import java.util.ArrayList;

@Controller
@Slf4j
public class ThymeleafController {

    @GetMapping("/thymeleaf/ex1")
    public String ex1(Model model, ItemDto Dto) {

        ItemDto itemDto = ItemDto.builder()
                .itemName("최신 스프링")
                .itemDetail("스프링 부트 3.3.5")
                .itemSellStatus("SELL")
                .price(20000)
                .build();

        model.addAttribute("itemDto",itemDto);
        return "thymeleaf/ex1";
    }

    @GetMapping("/thymeleaf/ex2")
    public String ex2() {

        return "thymeleaf/ex2";
    }

    @GetMapping("/thymeleaf/ex3")
    public String ex3(ParamDto Dto, Model model) {
        log.info("paramDto: " + Dto);
        model.addAttribute("paramDto", Dto);
        return "/thymeleaf/ex3";
    }

    @GetMapping("/thymeleaf/ex4")
    public String ex4() {

        return "/thymeleaf/ex4";
    }

}

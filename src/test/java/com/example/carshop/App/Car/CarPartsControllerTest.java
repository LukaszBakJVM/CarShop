package com.example.carshop.App.Car;



import com.example.carshop.CarShopApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest(classes = CarShopApplication.class)
@AutoConfigureMockMvc
class CarPartsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private final String END_POINT = "/car";


    @Test
    void save() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT+"/newPart")
                        .content(objectMapper.writeValueAsString(carDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());

    }

    @Test
    void deleteBySerialNumber() {
    }

    @Test
    void findAllBySerialNumber() throws Exception {
        String serialNumber= "11";
        int page = 1;
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                .param("serialnumber",serialNumber)
                .param("page",String.valueOf(page))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void sellPart() throws Exception {
        String serialNumber = "1";
        int quantity = 2;

        mockMvc.perform(MockMvcRequestBuilders.patch(END_POINT+"/sell")
                .param("serialNumber",serialNumber)
                .param("quantity",String.valueOf(quantity))
                .content(objectMapper.writeValueAsString(carDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    CarDto carDto(){
        CarDto car = new CarDto();

        car.setMark("Opel");
        car.setModel("Corsa");
        car.setSerialNumber("111");
        car.setPartsBrand("Febi");
        car.setPrice(BigDecimal.valueOf(10));
        car.setQuantity(2);
        car.setCategory("Opony");
        return car;




    }
}
package com.example.carshop.App.Moto;


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
class MotoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private final String END_POINT = "/moto";

    @Test
    void saveIfExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT + "/newPart")
                        .content(objectMapper.writeValueAsString(exist()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    void save() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT + "/newPart")
                        .content(objectMapper.writeValueAsString(motoDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    void deleteBySerialNumber() throws Exception {
        String serialNumber = "1";
        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT+"/{serialNumber}",serialNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void deleteBySerialNumberNotFound() throws Exception {
        String serialNumber = "1111111";
        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT + "/{serialNumber}", serialNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    @Test
    void findAllBySerialNumber() throws Exception {
        String serialNumber = "11";
        int page = 0;
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                        .param("serialnumber", serialNumber)
                        .param("page", String.valueOf(page))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
    @Test
    void findAllAll() throws Exception {

        int page = 0;
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                        .param("page", String.valueOf(page))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void sellPart() throws Exception {
        String serialNumber = "111";
        int quantity = 2;

        mockMvc.perform(MockMvcRequestBuilders.patch(END_POINT + "/sell")
                        .param("serialNumber", serialNumber)
                        .param("quantity", String.valueOf(quantity))
                        .content(objectMapper.writeValueAsString(motoDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    private MotoDto exist() {
       MotoDto motoDto = new MotoDto();

        motoDto.setMark("Opel");
        motoDto.setModel("Corsa");
        motoDto.setSerialNumber("111");
        motoDto.setPartsBrand("Febi");
        motoDto.setPrice(BigDecimal.valueOf(10));
        motoDto.setQuantity(3);
        motoDto.setCategory("Opony");
        return motoDto;
    }

    private MotoDto motoDto() {
        MotoDto motoDto = new MotoDto();

        motoDto.setMark("Opel");
        motoDto.setModel("Corsa");
        motoDto.setSerialNumber("1111");
        motoDto.setPartsBrand("Febi");
        motoDto.setPrice(BigDecimal.valueOf(10));
        motoDto.setQuantity(2);
        motoDto.setCategory("Opony");
        return motoDto;
    }

}
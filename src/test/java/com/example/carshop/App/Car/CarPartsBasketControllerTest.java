package com.example.carshop.App.Car;



import com.example.carshop.CarShopApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;



import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = CarShopApplication.class)
@AutoConfigureMockMvc
class CarPartsBasketControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private final String END_POINT = "/car";
    private final String filePatch= "F:/Projekty/CarShop/src/main/resources/static/img/samochod.jpg";
    
    


    @Test
    void saveIfExist() throws Exception {
        byte[] bytes = Files.readAllBytes(Path.of(filePatch));

        String mark = "mark";
        String model = "model";
        String serialNumber = "serialNumber";
        String partBrands = "partBrands";
        BigDecimal price = new BigDecimal("100");
        int quantity = 10;
        String category = "Opony";
        MockMultipartFile file = new MockMultipartFile("file", bytes);

        mockMvc.perform(MockMvcRequestBuilders.multipart(END_POINT + "/newPart")
                        .file(file)
                        .param("mark",mark)
                        .param("model",model)
                        .param("serialNumber",serialNumber)
                        .param("partBrands",partBrands)
                        .param("price",price.toString())
                        .param("quantity",String.valueOf(quantity))
                        .param("category",category)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(file.getBytes())
                        .characterEncoding("utf-8"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    void save() throws Exception {
        byte[] bytes = Files.readAllBytes(Path.of(filePatch));

        String mark = "mark";
        String model = "model";
        String serialNumber = "serialNumberNotExist";
        String partBrands = "partBrands";
        BigDecimal price = new BigDecimal("100");
        int quantity = 10;
        String category = "Opony";
        MockMultipartFile file = new MockMultipartFile("file", bytes);

        mockMvc.perform(MockMvcRequestBuilders.multipart(END_POINT + "/newPart")
                        .file(file)
                        .param("mark",mark)
                        .param("model",model)
                        .param("serialNumber",serialNumber)
                        .param("partBrands",partBrands)
                        .param("price",price.toString())
                        .param("quantity",String.valueOf(quantity))
                        .param("category",category)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(file.getBytes())
                        .characterEncoding("utf-8"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    void deleteBySerialNumber() throws Exception {
        String serialNumber = "serialNumberNotExist";
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
        String serialNumber = "1111";
        int quantity = 2;

        mockMvc.perform(MockMvcRequestBuilders.patch(END_POINT + "/sell")
                        .param("serialNumber", serialNumber)
                        .param("quantity", String.valueOf(quantity))
                        .content(objectMapper.writeValueAsString(carDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }




    private CarDto carDto() {
        CarDto carDto = new CarDto();

        carDto.setMark("Opel");
        carDto.setModel("Corsa");
        carDto.setSerialNumber("1111155");
        carDto.setPartsBrand("Febi");
        carDto.setPrice(BigDecimal.valueOf(10));
        carDto.setQuantity(2);
        carDto.setCategory("Opony");
        return carDto;
    }
}
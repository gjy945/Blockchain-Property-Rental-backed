package com.example.house_backed.controller;


import com.example.house_backed.pojo.House;
import com.example.house_backed.service.IHouseService;
import com.example.house_backed.utils.BlockchainUtils;
import com.example.house_backed.utils.R;
import com.example.house_backed.utils.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@RestController
@CrossOrigin
@RequestMapping("/house")
public class HouseController {

    @Resource
    private BlockchainUtils blockchainUtils;

    @Resource
    private ResourceLoader resourceLoader;

    @Resource
    private IHouseService houseService;


    @PostMapping("/publishHouse/{rent}/{deposit}")
    public R publishHouse(@PathVariable String rent, @PathVariable String deposit) throws Exception {

        String pemFilePath = getPemFilePath(UserType.LANDLORD);

        TransactionResponse transactionResponse = blockchainUtils
                .sendTransaction("House", "publishHouse", List.of(rent, deposit), pemFilePath);

        return houseService.publishHouse(transactionResponse);
    }

    @GetMapping("/getAllHouse")
    public List<Object> getAllHouse() throws Exception {

        String pemFilePath = getPemFilePath(UserType.TENANT);
        List<Object> list = blockchainUtils.callContract("House", "getAllHouses", List.of(), pemFilePath);
        return list;
    }

    @PostMapping("/sign/{id}")
    public R sign(@PathVariable int id) throws Exception {
        String pemFilePath = getPemFilePath(UserType.LANDLORD);

        TransactionResponse transactionResponse = blockchainUtils
                .sendTransaction("House", "signContract", List.of(id), pemFilePath);

        return houseService.sign(transactionResponse);
    }


    private String getPemFilePath(UserType userType) throws IOException {

        if (userType == UserType.TENANT){
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:pemFile/TenantA_key_0xe1aeb23a71396a6f666614710b789b0f483ca040.pem");
            return resource.getFile().getAbsolutePath();
        }
        org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:pemFile/LandlordA_key_0x153be3982a086289cc3576a80a3a35e1e58504eb.pem");
        System.out.println(resource.getFile().getAbsolutePath());
        return resource.getFile().getAbsolutePath();
    }
}

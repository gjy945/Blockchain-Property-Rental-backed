package com.example.house_backed;

import com.example.house_backed.utils.BlockchainUtils;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HouseBackedApplicationTests {

    @Resource
    private BlockchainUtils blockchainUtils;

    @Resource
    private ResourceLoader resourceLoader;

    @Test
    void publishHouse() throws Exception {

        org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:pemFile/LandlordA_key_0x153be3982a086289cc3576a80a3a35e1e58504eb.pem");
        String landlordPemFile = resource.getFile().getAbsolutePath();

        List<Object> params = List.of("3000", "3000");
        TransactionResponse transactionResponse = blockchainUtils.sendTransaction("House", "publishHouse", params, landlordPemFile);
        System.out.println(transactionResponse);
    }

}

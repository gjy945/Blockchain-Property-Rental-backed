package com.example.house_backed.service.impl;

import com.example.house_backed.mapper.HouseMapper;
import com.example.house_backed.service.IHouseService;
import com.example.house_backed.utils.R;
import com.example.house_backed.utils.RUtils;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HouseServiceImpl implements IHouseService {


    @Resource
    private HouseMapper houseMapper;

    @Override
    public R publishHouse(TransactionResponse transactionResponse) {

        TransactionReceipt transactionReceipt = transactionResponse.getTransactionReceipt();
        Map<String, List<List<Object>>> eventResultMap = transactionResponse.getEventResultMap();

        var ref = new Object() {
            Object id;
        };

        eventResultMap.forEach((k, v) -> {
            v.forEach(vv -> {
                ref.id = vv.get(0);
                System.out.println(vv.get(0));
            });
        });

        return RUtils.success(ref.id);


//        houseMapper.publishHouse();
    }

    @Override
    public R sign(TransactionResponse transactionResponse) {
        String statusMsg = transactionResponse.getTransactionReceipt().getStatusMsg();


        return RUtils.success(statusMsg);
    }
}

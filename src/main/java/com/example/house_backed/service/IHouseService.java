package com.example.house_backed.service;

import com.example.house_backed.utils.R;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;

public interface IHouseService {

    // 发布房屋信息
    R publishHouse(TransactionResponse transactionResponse);

    // 签署房屋合同
    R sign(TransactionResponse transactionResponse);
}

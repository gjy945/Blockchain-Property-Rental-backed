package com.example.house_backed.utils;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.crypto.keystore.KeyTool;
import org.fisco.bcos.sdk.crypto.keystore.PEMKeyStore;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.KeyPair;
import java.util.Base64;
import java.util.List;

@Component
public class BlockchainUtils {

    private final String configFilePath = "classpath:config-example.toml";
    private final String abiPath = "classpath:/abi/";
    private final String binPath = "classpath:/bin/";
    private final int groupId = 1;

    private final String contractAddress = "0x2f6edbcb15fbe0ca10400311ce58eab816505483";

    private BcosSDK sdk;
    private Client client;
    private CryptoKeyPair keyPair;
    private AssembleTransactionProcessor transactionProcessor;

    // 初始化 SDK 和 TransactionProcessor
    private void initialize(String landlordPemFile) throws Exception {
        if (sdk == null || client == null || keyPair == null || transactionProcessor == null) {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            org.springframework.core.io.Resource resource = resolver.getResource(configFilePath);
            String configFile = resource.getFile().getAbsolutePath();

            // 初始化 BcosSDK 对象
            sdk = BcosSDK.build(configFile);
            // 获取 Client 对象，传入群组 ID
            client = sdk.getClient(groupId);

            // 从 PEM 文件中读取私钥
            PEMKeyStore pemKeyStore = new PEMKeyStore(landlordPemFile);
            KeyPair pair = pemKeyStore.getKeyPair();

            // 创建 CryptoKeyPair 对象
            keyPair = client.getCryptoSuite().createKeyPair(pair);

            // 获取 abi 和 bin 文件路径
            ApplicationContext context = new AnnotationConfigApplicationContext();
            resource = context.getResource(abiPath);
            String abiFilePath = resource.getFile().getAbsolutePath();

            resource = context.getResource(binPath);
            String binFilePath = resource.getFile().getAbsolutePath();

            // 初始化 AssembleTransactionProcessor
            transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(client, keyPair, abiFilePath, binFilePath);
        }
    }

    // 发送交易并获取响应
    public TransactionResponse sendTransaction(String contractName, String functionName, List<Object> params, String landlordPemFile) throws Exception {
        initialize(landlordPemFile);
        try {
            // 调用合约函数并获取交易响应
            return transactionProcessor.sendTransactionAndGetResponseByContractLoader(contractName, contractAddress, functionName, params);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 调用合约查询函数并获取响应
    public List<Object> callContract(String contractName, String functionName, List<Object> params, String landlordPemFile) throws Exception {
        initialize(landlordPemFile);
        try {
            // 调用合约查询函数并获取响应
            CallResponse callResponse = transactionProcessor.sendCallByContractLoader(contractName, contractAddress, functionName, params);
            return callResponse.getReturnObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
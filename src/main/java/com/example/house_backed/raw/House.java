package com.example.house_backed.raw;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.DynamicArray;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple10;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple5;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class House extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50611e3b806100206000396000f3fe608060405234801561001057600080fd5b50600436106101165760003560e01c8063aeda852f116100a2578063d87deea811610071578063d87deea8146106ce578063d883620f1461084d578063d9e8843f1461086b578063db8c092814610899578063fd9153a3146108d157610116565b8063aeda852f146105c6578063b9aa1b5b14610654578063d5ce658314610672578063d83c39d4146106a057610116565b80638aa3e011116100e95780638aa3e011146103a55780639537e8d11461048e5780639967fbcb146104bc578063a6f958c8146104fe578063ad50f56d1461053857610116565b806333aeeca71461011b578063474da79a146101a6578063483cb1de1461028f578063739d8e5d1461031a575b600080fd5b61015d6004803603602081101561013157600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061095c565b604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390f35b6101d2600480360360208110156101bc57600080fd5b81019080803590602001909291905050506109d6565b604051808b81526020018a81526020018973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001878152602001861515151581526020018515151515815260200184151515158152602001831515151581526020018281526020019a505050505050505050505060405180910390f35b6102d1600480360360208110156102a557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610a9e565b604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390f35b61035c6004803603602081101561033057600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610ae2565b604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390f35b6103d1600480360360208110156103bb57600080fd5b8101908080359060200190929190505050610b5b565b604051808b81526020018a81526020018973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001878152602001861515151581526020018515151515815260200184151515158152602001831515151581526020018281526020019a505050505050505050505060405180910390f35b6104ba600480360360208110156104a457600080fd5b8101908080359060200190929190505050610c49565b005b6104e8600480360360208110156104d257600080fd5b8101908080359060200190929190505050610f8b565b6040518082815260200191505060405180910390f35b6105366004803603604081101561051457600080fd5b8101908080359060200190929190803515159060200190929190505050610fac565b005b6105646004803603602081101561054e57600080fd5b81019080803590602001909291905050506111fa565b604051808681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001841515151581526020018381526020018281526020019550505050505060405180910390f35b6105f2600480360360208110156105dc57600080fd5b8101908080359060200190929190505050611272565b604051808681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001841515151581526020018381526020018281526020019550505050505060405180910390f35b61065c6112d5565b6040518082815260200191505060405180910390f35b61069e6004803603602081101561068857600080fd5b81019080803590602001909291905050506112db565b005b6106cc600480360360208110156106b657600080fd5b81019080803590602001909291905050506114c2565b005b6106d661170e565b60405180806020018060200180602001806020018060200186810386528b818151815260200191508051906020019060200280838360005b8381101561072957808201518184015260208101905061070e565b5050505090500186810385528a818151815260200191508051906020019060200280838360005b8381101561076b578082015181840152602081019050610750565b50505050905001868103845289818151815260200191508051906020019060200280838360005b838110156107ad578082015181840152602081019050610792565b50505050905001868103835288818151815260200191508051906020019060200280838360005b838110156107ef5780820151818401526020810190506107d4565b50505050905001868103825287818151815260200191508051906020019060200280838360005b83811015610831578082015181840152602081019050610816565b505050509050019a505050505050505050505060405180910390f35b6108556119e1565b6040518082815260200191505060405180910390f35b6108976004803603602081101561088157600080fd5b81019080803590602001909291905050506119e7565b005b6108cf600480360360408110156108af57600080fd5b810190808035906020019092919080359060200190929190505050611c51565b005b610913600480360360208110156108e757600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611dc1565b604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390f35b6000806000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681600101549250925050915091565b60036020528060005260406000206000915090508060000154908060010154908060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060030160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060040154908060050160009054906101000a900460ff16908060050160019054906101000a900460ff16908060050160029054906101000a900460ff16908060050160039054906101000a900460ff1690806006015490508a565b60006020528060005260406000206000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060010154905082565b60008060008060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681600101549250925050915091565b6000806000806000806000806000806000600360008d81526020019081526020016000209050806000015481600101548260020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168360030160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1684600401548560050160009054906101000a900460ff168660050160019054906101000a900460ff168760050160029054906101000a900460ff168860050160039054906101000a900460ff1689600601549a509a509a509a509a509a509a509a509a509a50509193959799509193959799565b600015156002600083815260200190815260200160002060010160149054906101000a900460ff16151514610ce6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260128152602001807fe688bfe5b18be5b7b2e69c89e59088e5908c000000000000000000000000000081525060200191505060405180910390fd5b600560008154809291906001019190505550600060055490506040518061014001604052808281526020018381526020016002600085815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020013373ffffffffffffffffffffffffffffffffffffffff168152602001428152602001600015158152602001600015158152602001600015158152602001600015158152602001600081525060036000838152602001908152602001600020600082015181600001556020820151816001015560408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160030160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506080820151816004015560a08201518160050160006101000a81548160ff02191690831515021790555060c08201518160050160016101000a81548160ff02191690831515021790555060e08201518160050160026101000a81548160ff0219169083151502179055506101008201518160050160036101000a81548160ff021916908315150217905550610120820151816006015590505060016002600084815260200190815260200160002060010160146101000a81548160ff0219169083151502179055503373ffffffffffffffffffffffffffffffffffffffff167f1b888c118c05ed34cb3df67b48ee7e2e0adb68e6b851122fd9ea804b8a3daa098284604051808381526020018281526020019250505060405180910390a25050565b60068181548110610f9857fe5b906000526020600020016000915090505481565b600060036000848152602001908152602001600020905081156110e1573373ffffffffffffffffffffffffffffffffffffffff","168160020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161461108e576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807fe4bb85e688bfe4b89ce58fafe4bba5e7bb88e6ada2000000000000000000000081525060200191505060405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff167f8c15c458fcb82df792f280682093b4246dc8f0e9f818399f8966e86bfe947be5846040518082815260200191505060405180910390a26111f5565b3373ffffffffffffffffffffffffffffffffffffffff168160030160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16146111a6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807fe4bb85e7a79fe5aea2e58fafe4bba5e7bb88e6ada2000000000000000000000081525060200191505060405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff167f8c15c458fcb82df792f280682093b4246dc8f0e9f818399f8966e86bfe947be5846040518082815260200191505060405180910390a25b505050565b60008060008060008060026000888152602001908152602001600020905080600001548160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168260010160149054906101000a900460ff1683600201548460030154955095509550955095505091939590929450565b60026020528060005260406000206000915090508060000154908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060010160149054906101000a900460ff16908060020154908060030154905085565b60055481565b60006003600083815260200190815260200160002090503373ffffffffffffffffffffffffffffffffffffffff168160030160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16146113b7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807fe4bb85e7a79fe5aea2e58fafe4bba5e9a284e5ad98000000000000000000000081525060200191505060405180910390fd5b62015180816004015442031115611436576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601a8152602001807fe9a284e5ad98e697b6e997b4e8b685e8bf873234e5b08fe697b600000000000081525060200191505060405180910390fd5b60018160050160006101000a81548160ff02191690831515021790555060018160050160016101000a81548160ff0219169083151502179055503373ffffffffffffffffffffffffffffffffffffffff167fb965d4c0ef13338bf813335a1c74d1ef406ca6b1be39a3957c7735b759511c44836040518082815260200191505060405180910390a25050565b60006003600083815260200190815260200160002090503373ffffffffffffffffffffffffffffffffffffffff168160020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161461159e576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807fe4bb85e688bfe4b89ce58fafe4bba5e9a284e5ad98000000000000000000000081525060200191505060405180910390fd5b6201518081600401544203111561161d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601a8152602001807fe9a284e5ad98e697b6e997b4e8b685e8bf873234e5b08fe697b600000000000081525060200191505060405180910390fd5b60018160050160026101000a81548160ff0219169083151502179055508060050160009054906101000a900460ff16801561166657508060050160019054906101000a900460ff165b1561170a5760018160050160036101000a81548160ff0219169083151502179055506001600260008360010154815260200190815260200160002060010160146101000a81548160ff0219169083151502179055503373ffffffffffffffffffffffffffffffffffffffff167fb965d4c0ef13338bf813335a1c74d1ef406ca6b1be39a3957c7735b759511c44836040518082815260200191505060405180910390a25b5050565b6060806060806060600060068054905090508067ffffffffffffffff8111801561173757600080fd5b506040519080825280602002602001820160405280156117665781602001602082028036833780820191505090505b5095508067ffffffffffffffff8111801561178057600080fd5b506040519080825280602002602001820160405280156117af5781602001602082028036833780820191505090505b5094508067ffffffffffffffff811180156117c957600080fd5b506040519080825280602002602001820160405280156117f85781602001602082028036833780820191505090505b5093508067ffffffffffffffff8111801561181257600080fd5b506040519080825280602002602001820160405280156118415781602001602082028036833780820191505090505b5092508067ffffffffffffffff8111801561185b57600080fd5b5060405190808252806020026020018201604052801561188a5781602001602082028036833780820191505090505b50915060008090505b818110156119c957600060026000600684815481106118ae57fe5b90600052602060002001548152602001908152602001600020905080600001548883815181106118da57fe5b6020026020010181815250508060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1687838151811061191757fe5b602002602001019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250508060010160149054906101000a900460ff1686838151811061196f57fe5b602002602001019015159081151581525050806002015485838151811061199257fe5b60200260200101818152505080600301548483815181106119af57fe5b602002602001018181525050508080600101915050611893565b50858585858595509550955095509550509091929394565b60045481565b60006003600083815260200190815260200160002090503373ffffffffffffffffffffffffffffffffffffffff168160030160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614611ac3576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807fe4bb85e7a79fe5aea2e58fafe4bba5e7bcb4e7bab3e7a79fe98791000000000081525060200191505060405180910390fd5b8060050160039054906101000a900460ff16611b47576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807fe59088e5908ce69caae7949fe69588000000000000000000000000000000000081525060200191505060405180910390fd5b62278d00816006015401421015611bc6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260128152602001807fe7a79fe98791e5b09ae69caae588b0e69c9f000000000000000000000000000081525060200191505060405180910390fd5b600060026000836001015481526020019081526020016000206002015490504282600601819055503373ffffffffffffffffffffffffffffffffffffffff167fe7b793c2d375aa5934e16873f97e647671990418e9fead32b65a4d41e8fb4d1d84834260405180848152602001838152602001828152602001935050505060405180910390a2505050565b600460008154809291906001019190505550600060045490506040518060a001604052808281526020013373ffffffffffffffffffffffffffffffffffffffff16815260200160001515815260200184815260200183815250600260008381526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160010160146101000a81548160ff021916908315150217905550606082015181600201556080820151816003015590505060068190806001815401808255809150506001900390600052602060002001600090919091909150553373ffffffffffffffffffffffffffffffffffffffff167ffd051352ee2f3615e0f0355c65bd14f75c15be77845c186b2c2d23cb7ee016c5826040518082815260200191505060405180910390a2505050565b60016020528060005260406000206000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806001015490508256fea26469706673582212206f29af4c35311e657c7312258a65a436ad8c6a2e7366277de4b352ecae7b285c64736f6c634300060a0033"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"contractID\",\"type\":\"uint256\"}],\"name\":\"ContractExpired\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"contractID\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"houseID\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"tenant\",\"type\":\"address\"}],\"name\":\"ContractSigned\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"contractID\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"initiator\",\"type\":\"address\"}],\"name\":\"ContractTerminated\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"houseID\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"landlord\",\"type\":\"address\"}],\"name\":\"HousePublished\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"contractID\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"participant\",\"type\":\"address\"}],\"name\":\"PrepaymentDone\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"contractID\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"tenant\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"paymentTime\",\"type\":\"uint256\"}],\"name\":\"RentPaid\",\"type\":\"event\"},{\"inputs\":[],\"name\":\"contractIDCounter\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"contracts\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"contractID\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"houseID\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"landlord\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"tenant\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"signTime\",\"type\":\"uint256\"},{\"internalType\":\"bool\",\"name\":\"depositPrepaid\",\"type\":\"bool\"},{\"internalType\":\"bool\",\"name\":\"rentPrepaid\",\"type\":\"bool\"},{\"internalType\":\"bool\",\"name\":\"landlordDepositPrepaid\",\"type\":\"bool\"},{\"internalType\":\"bool\",\"name\":\"contractActive\",\"type\":\"bool\"},{\"internalType\":\"uint256\",\"name\":\"lastRentPaidTime\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getAllHouses\",\"outputs\":[{\"internalType\":\"uint256[]\",\"name\":\"ids\",\"type\":\"uint256[]\"},{\"internalType\":\"address[]\",\"name\":\"landlords\",\"type\":\"address[]\"},{\"internalType\":\"bool[]\",\"name\":\"isActives\",\"type\":\"bool[]\"},{\"internalType\":\"uint256[]\",\"name\":\"rents\",\"type\":\"uint256[]\"},{\"internalType\":\"uint256[]\",\"name\":\"deposits\",\"type\":\"uint256[]\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_contractID\",\"type\":\"uint256\"}],\"name\":\"getContractInfo\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"contractID\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"houseID\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"landlord\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"tenant\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"signTime\",\"type\":\"uint256\"},{\"internalType\":\"bool\",\"name\":\"depositPrepaid\",\"type\":\"bool\"},{\"internalType\":\"bool\",\"name\":\"rentPrepaid\",\"type\":\"bool\"},{\"internalType\":\"bool\",\"name\":\"landlordDepositPrepaid\",\"type\":\"bool\"},{\"internalType\":\"bool\",\"name\":\"contractActive\",\"type\":\"bool\"},{\"internalType\":\"uint256\",\"name\":\"lastRentPaidTime\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_houseID\",\"type\":\"uint256\"}],\"name\":\"getHouseInfo\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"houseID\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"landlord\",\"type\":\"address\"},{\"internalType\":\"bool\",\"name\":\"isActive\",\"type\":\"bool\"},{\"internalType\":\"uint256\",\"name\":\"rent\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"deposit\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_landlordAddress\",\"type\":\"address\"}],\"name\":\"getLandlordInfo\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"landlordAddress\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"depositBalance\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_tenantAddress\",\"type\":\"address\"}],\"name\":\"getTenantInfo\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"tenantAddress\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"depositBalance\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"houseIDCounter\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"houseIDs\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"houses\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"houseID\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"landlord\",\"type\":\"address\"},{\"internalType\":\"bool\",\"name\":\"isActive\",\"type\":\"bool\"},{\"internalType\":\"uint256\",\"name\":\"rent\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"deposit\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_contractID\",\"type\":\"uint256\"}],\"name\":\"landlordPrepayment\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"landlords\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"landlordAddress\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"depositBalance\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_contractID\",\"type\":\"uint256\"}],\"name\":\"payRent\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_rent\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"_deposit\",\"type\":\"uint256\"}],\"name\":\"publishHouse\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_houseID\",\"type\":\"uint256\"}],\"name\":\"signContract\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_contractID\",\"type\":\"uint256\"}],\"name\":\"tenantPrepayment\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"tenants\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"tenantAddress\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"depositBalance\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_contractID\",\"type\":\"uint256\"},{\"internalType\":\"bool\",\"name\":\"isLandlord\",\"type\":\"bool\"}],\"name\":\"terminateContract\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_CONTRACTIDCOUNTER = "contractIDCounter";

    public static final String FUNC_CONTRACTS = "contracts";

    public static final String FUNC_GETALLHOUSES = "getAllHouses";

    public static final String FUNC_GETCONTRACTINFO = "getContractInfo";

    public static final String FUNC_GETHOUSEINFO = "getHouseInfo";

    public static final String FUNC_GETLANDLORDINFO = "getLandlordInfo";

    public static final String FUNC_GETTENANTINFO = "getTenantInfo";

    public static final String FUNC_HOUSEIDCOUNTER = "houseIDCounter";

    public static final String FUNC_HOUSEIDS = "houseIDs";

    public static final String FUNC_HOUSES = "houses";

    public static final String FUNC_LANDLORDPREPAYMENT = "landlordPrepayment";

    public static final String FUNC_LANDLORDS = "landlords";

    public static final String FUNC_PAYRENT = "payRent";

    public static final String FUNC_PUBLISHHOUSE = "publishHouse";

    public static final String FUNC_SIGNCONTRACT = "signContract";

    public static final String FUNC_TENANTPREPAYMENT = "tenantPrepayment";

    public static final String FUNC_TENANTS = "tenants";

    public static final String FUNC_TERMINATECONTRACT = "terminateContract";

    public static final Event CONTRACTEXPIRED_EVENT = new Event("ContractExpired", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event CONTRACTSIGNED_EVENT = new Event("ContractSigned", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event CONTRACTTERMINATED_EVENT = new Event("ContractTerminated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event HOUSEPUBLISHED_EVENT = new Event("HousePublished", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PREPAYMENTDONE_EVENT = new Event("PrepaymentDone", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event RENTPAID_EVENT = new Event("RentPaid", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected House(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public List<ContractExpiredEventResponse> getContractExpiredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CONTRACTEXPIRED_EVENT, transactionReceipt);
        ArrayList<ContractExpiredEventResponse> responses = new ArrayList<ContractExpiredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ContractExpiredEventResponse typedResponse = new ContractExpiredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.contractID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeContractExpiredEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(CONTRACTEXPIRED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeContractExpiredEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(CONTRACTEXPIRED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<ContractSignedEventResponse> getContractSignedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CONTRACTSIGNED_EVENT, transactionReceipt);
        ArrayList<ContractSignedEventResponse> responses = new ArrayList<ContractSignedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ContractSignedEventResponse typedResponse = new ContractSignedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tenant = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.contractID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.houseID = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeContractSignedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(CONTRACTSIGNED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeContractSignedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(CONTRACTSIGNED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<ContractTerminatedEventResponse> getContractTerminatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CONTRACTTERMINATED_EVENT, transactionReceipt);
        ArrayList<ContractTerminatedEventResponse> responses = new ArrayList<ContractTerminatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ContractTerminatedEventResponse typedResponse = new ContractTerminatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.initiator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.contractID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeContractTerminatedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(CONTRACTTERMINATED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeContractTerminatedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(CONTRACTTERMINATED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<HousePublishedEventResponse> getHousePublishedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(HOUSEPUBLISHED_EVENT, transactionReceipt);
        ArrayList<HousePublishedEventResponse> responses = new ArrayList<HousePublishedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            HousePublishedEventResponse typedResponse = new HousePublishedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.landlord = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.houseID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeHousePublishedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(HOUSEPUBLISHED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeHousePublishedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(HOUSEPUBLISHED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<PrepaymentDoneEventResponse> getPrepaymentDoneEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PREPAYMENTDONE_EVENT, transactionReceipt);
        ArrayList<PrepaymentDoneEventResponse> responses = new ArrayList<PrepaymentDoneEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PrepaymentDoneEventResponse typedResponse = new PrepaymentDoneEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.participant = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.contractID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribePrepaymentDoneEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(PREPAYMENTDONE_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribePrepaymentDoneEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(PREPAYMENTDONE_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<RentPaidEventResponse> getRentPaidEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(RENTPAID_EVENT, transactionReceipt);
        ArrayList<RentPaidEventResponse> responses = new ArrayList<RentPaidEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RentPaidEventResponse typedResponse = new RentPaidEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tenant = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.contractID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.paymentTime = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeRentPaidEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(RENTPAID_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeRentPaidEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(RENTPAID_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public BigInteger contractIDCounter() throws ContractException {
        final Function function = new Function(FUNC_CONTRACTIDCOUNTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple10<BigInteger, BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean, Boolean, BigInteger> contracts(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_CONTRACTS, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple10<BigInteger, BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean, Boolean, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue(), 
                (Boolean) results.get(5).getValue(), 
                (Boolean) results.get(6).getValue(), 
                (Boolean) results.get(7).getValue(), 
                (Boolean) results.get(8).getValue(), 
                (BigInteger) results.get(9).getValue());
    }

    public Tuple5<List<BigInteger>, List<String>, List<Boolean>, List<BigInteger>, List<BigInteger>> getAllHouses() throws ContractException {
        final Function function = new Function(FUNC_GETALLHOUSES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Bool>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple5<List<BigInteger>, List<String>, List<Boolean>, List<BigInteger>, List<BigInteger>>(
                convertToNative((List<Uint256>) results.get(0).getValue()), 
                convertToNative((List<Address>) results.get(1).getValue()), 
                convertToNative((List<Bool>) results.get(2).getValue()), 
                convertToNative((List<Uint256>) results.get(3).getValue()), 
                convertToNative((List<Uint256>) results.get(4).getValue()));
    }

    public Tuple10<BigInteger, BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean, Boolean, BigInteger> getContractInfo(BigInteger _contractID) throws ContractException {
        final Function function = new Function(FUNC_GETCONTRACTINFO, 
                Arrays.<Type>asList(new Uint256(_contractID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple10<BigInteger, BigInteger, String, String, BigInteger, Boolean, Boolean, Boolean, Boolean, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue(), 
                (Boolean) results.get(5).getValue(), 
                (Boolean) results.get(6).getValue(), 
                (Boolean) results.get(7).getValue(), 
                (Boolean) results.get(8).getValue(), 
                (BigInteger) results.get(9).getValue());
    }

    public Tuple5<BigInteger, String, Boolean, BigInteger, BigInteger> getHouseInfo(BigInteger _houseID) throws ContractException {
        final Function function = new Function(FUNC_GETHOUSEINFO, 
                Arrays.<Type>asList(new Uint256(_houseID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple5<BigInteger, String, Boolean, BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (Boolean) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue());
    }

    public Tuple2<String, BigInteger> getLandlordInfo(String _landlordAddress) throws ContractException {
        final Function function = new Function(FUNC_GETLANDLORDINFO, 
                Arrays.<Type>asList(new Address(_landlordAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<String, BigInteger>(
                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue());
    }

    public Tuple2<String, BigInteger> getTenantInfo(String _tenantAddress) throws ContractException {
        final Function function = new Function(FUNC_GETTENANTINFO, 
                Arrays.<Type>asList(new Address(_tenantAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<String, BigInteger>(
                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue());
    }

    public BigInteger houseIDCounter() throws ContractException {
        final Function function = new Function(FUNC_HOUSEIDCOUNTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger houseIDs(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_HOUSEIDS, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple5<BigInteger, String, Boolean, BigInteger, BigInteger> houses(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_HOUSES, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple5<BigInteger, String, Boolean, BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (Boolean) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue());
    }

    public TransactionReceipt landlordPrepayment(BigInteger _contractID) {
        final Function function = new Function(
                FUNC_LANDLORDPREPAYMENT, 
                Arrays.<Type>asList(new Uint256(_contractID)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void landlordPrepayment(BigInteger _contractID, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_LANDLORDPREPAYMENT, 
                Arrays.<Type>asList(new Uint256(_contractID)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForLandlordPrepayment(BigInteger _contractID) {
        final Function function = new Function(
                FUNC_LANDLORDPREPAYMENT, 
                Arrays.<Type>asList(new Uint256(_contractID)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getLandlordPrepaymentInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_LANDLORDPREPAYMENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple2<String, BigInteger> landlords(String param0) throws ContractException {
        final Function function = new Function(FUNC_LANDLORDS, 
                Arrays.<Type>asList(new Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<String, BigInteger>(
                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue());
    }

    public TransactionReceipt payRent(BigInteger _contractID) {
        final Function function = new Function(
                FUNC_PAYRENT, 
                Arrays.<Type>asList(new Uint256(_contractID)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void payRent(BigInteger _contractID, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_PAYRENT, 
                Arrays.<Type>asList(new Uint256(_contractID)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForPayRent(BigInteger _contractID) {
        final Function function = new Function(
                FUNC_PAYRENT, 
                Arrays.<Type>asList(new Uint256(_contractID)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getPayRentInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_PAYRENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt publishHouse(BigInteger _rent, BigInteger _deposit) {
        final Function function = new Function(
                FUNC_PUBLISHHOUSE, 
                Arrays.<Type>asList(new Uint256(_rent),
                new Uint256(_deposit)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void publishHouse(BigInteger _rent, BigInteger _deposit, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_PUBLISHHOUSE, 
                Arrays.<Type>asList(new Uint256(_rent),
                new Uint256(_deposit)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForPublishHouse(BigInteger _rent, BigInteger _deposit) {
        final Function function = new Function(
                FUNC_PUBLISHHOUSE, 
                Arrays.<Type>asList(new Uint256(_rent),
                new Uint256(_deposit)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, BigInteger> getPublishHouseInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_PUBLISHHOUSE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public TransactionReceipt signContract(BigInteger _houseID) {
        final Function function = new Function(
                FUNC_SIGNCONTRACT, 
                Arrays.<Type>asList(new Uint256(_houseID)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void signContract(BigInteger _houseID, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SIGNCONTRACT, 
                Arrays.<Type>asList(new Uint256(_houseID)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSignContract(BigInteger _houseID) {
        final Function function = new Function(
                FUNC_SIGNCONTRACT, 
                Arrays.<Type>asList(new Uint256(_houseID)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getSignContractInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SIGNCONTRACT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt tenantPrepayment(BigInteger _contractID) {
        final Function function = new Function(
                FUNC_TENANTPREPAYMENT, 
                Arrays.<Type>asList(new Uint256(_contractID)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void tenantPrepayment(BigInteger _contractID, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_TENANTPREPAYMENT, 
                Arrays.<Type>asList(new Uint256(_contractID)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForTenantPrepayment(BigInteger _contractID) {
        final Function function = new Function(
                FUNC_TENANTPREPAYMENT, 
                Arrays.<Type>asList(new Uint256(_contractID)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getTenantPrepaymentInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TENANTPREPAYMENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple2<String, BigInteger> tenants(String param0) throws ContractException {
        final Function function = new Function(FUNC_TENANTS, 
                Arrays.<Type>asList(new Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<String, BigInteger>(
                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue());
    }

    public TransactionReceipt terminateContract(BigInteger _contractID, Boolean isLandlord) {
        final Function function = new Function(
                FUNC_TERMINATECONTRACT, 
                Arrays.<Type>asList(new Uint256(_contractID),
                new Bool(isLandlord)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void terminateContract(BigInteger _contractID, Boolean isLandlord, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_TERMINATECONTRACT, 
                Arrays.<Type>asList(new Uint256(_contractID),
                new Bool(isLandlord)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForTerminateContract(BigInteger _contractID, Boolean isLandlord) {
        final Function function = new Function(
                FUNC_TERMINATECONTRACT, 
                Arrays.<Type>asList(new Uint256(_contractID),
                new Bool(isLandlord)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, Boolean> getTerminateContractInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TERMINATECONTRACT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, Boolean>(

                (BigInteger) results.get(0).getValue(), 
                (Boolean) results.get(1).getValue()
                );
    }

    public static House load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new House(contractAddress, client, credential);
    }

    public static House deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(House.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class ContractExpiredEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger contractID;
    }

    public static class ContractSignedEventResponse {
        public TransactionReceipt.Logs log;

        public String tenant;

        public BigInteger contractID;

        public BigInteger houseID;
    }

    public static class ContractTerminatedEventResponse {
        public TransactionReceipt.Logs log;

        public String initiator;

        public BigInteger contractID;
    }

    public static class HousePublishedEventResponse {
        public TransactionReceipt.Logs log;

        public String landlord;

        public BigInteger houseID;
    }

    public static class PrepaymentDoneEventResponse {
        public TransactionReceipt.Logs log;

        public String participant;

        public BigInteger contractID;
    }

    public static class RentPaidEventResponse {
        public TransactionReceipt.Logs log;

        public String tenant;

        public BigInteger contractID;

        public BigInteger amount;

        public BigInteger paymentTime;
    }
}

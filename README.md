# FISCO BCOS 区块链房屋租赁 Demo - 后端

  ![Java](https://img.shields.io/badge/Java-17-orange)
  ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-green)

## 项目简介

本项目是基于 **FISCO BCOS** 区块链的房屋租赁 Demo 的后端部分，采用 **Spring Boot** 框架开发，通过 **FISCO BCOS Java SDK** 与区块链进行交互。项目实现了房东发布房屋、租客查询房屋以及签署租赁合同的核心功能。

---


  ### 相关资料

  [智药守护APP - 前端uniapp](https://github.com/gjy945/SmartMedicineGuardia_fronted.git)

  [智药守护APP - 前端管理端vue3+ts](https://github.com/gjy945/SmartMedicineGuardia_fronted_admin.git)

  [智药守护APP - 后端springboot](https://github.com/gjy945/SmartMedicineGuardia_banked.git)

---

## 功能特性

- **房东发布房屋**：房东可以通过接口发布房屋信息，信息包括房屋租金、押金，数据将上链保存。
- **租客查询房屋**：租客可以通过接口查询当前可租赁的房屋信息。
- **签署租赁合同**：租客可以选择房屋并签署租赁合同，合同信息将上链保存，确保不可篡改。

---

## 技术栈

- **后端框架**：Spring Boot
- **区块链平台**：FISCO BCOS
- **区块链 SDK**：FISCO BCOS Java SDK
- **依赖管理**：Maven

---

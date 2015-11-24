module Datasource.Warehouse (
creator,
Datasource.Warehouse.insert,
Datasource.Warehouse.find
) where


import Database.HDBC

import Datasource.Database
import Domain.Warehouse


creator = "create table Warehouses (id, warehouse_name, location, item_name, quantity, pending_number, manpower)"

insert :: WarehouseName -> Location -> ItemName -> Quantity -> PendingNumber -> Manpower -> IO ()
insert warehouseName location itemName quantity pendingNumber manpower = Datasource.Database.insert "insert into Warehouses values (1, ?, ?, ?, ?, ?, ?)" [toSql warehouseName, toSql location, toSql itemName, toSql quantity, toSql pendingNumber, toSql manpower]

find = Datasource.Database.find "select * from Warehouses"

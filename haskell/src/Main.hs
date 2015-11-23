import Domain.Warehouse
import Domain.Order

import Datasource.Database

import Database.HDBC

main = do
--     print $ mkWarehouse "tokyo" 0 [("book", 10), ("game", 15)] (5, 5)
--     let ordered = order 5 [("book", 10), ("game", 15)]
--     print ordered


    create "create table Persons (id, name, type)"

    insert "insert into Persons values (?, ?, ?)" $ map id ["1", "Solid Snake", "Hero"]
    insert "insert into Persons values (?, ?, ?)" ["2", "Solidus Snake", "President"]

    print =<< find "select * from Persons"
    print =<< findOne "select * from Persons"

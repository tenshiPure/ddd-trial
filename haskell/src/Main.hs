import Domain.Warehouse
import Domain.Order

import Datasource.Fixture
import Datasource.Warehouse

main = do
--     print $ mkWarehouse "tokyo" 0 [("book", 10), ("game", 15)] (5, 5)
--     let ordered = order 5 [("book", 10), ("game", 15)]
--     print ordered

    dbInit
    some
    print =<< find

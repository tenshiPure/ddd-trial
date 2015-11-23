module Domain.Warehouse (
mkWarehouse
) where

import Domain.Point

type WarehouseName = String
type Location = Point
type Stock = (ItemName, Quantity)
type ItemName = String
type Quantity = Int
type PackingSchedule = (PendingNumber, Manpower)
type PendingNumber = Int
type Manpower = Int

data Warehouse = Warehouse {
    warehouseName :: WarehouseName,
    location :: Location,
    stocks :: [Stock],
    packingSchedule :: PackingSchedule
} deriving Show

mkWarehouse :: WarehouseName -> Location -> [Stock] -> PackingSchedule -> Warehouse
mkWarehouse warehouseName location stocks packingSchedule = Warehouse warehouseName location stocks packingSchedule

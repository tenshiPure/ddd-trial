module Domain.Warehouse (
WarehouseName,
Location,
Stock,
ItemName,
Quantity,
PackingSchedule,
PendingNumber,
Manpower,
_warehouseName,
_quantity,
mkWarehouse
) where


import Domain.Point
import Domain.Validator


type WarehouseName = String
type Location = Point
type Stock = (ItemName, Quantity)
type ItemName = String
type Quantity = Int
type PackingSchedule = (PendingNumber, Manpower)
type PendingNumber = Int
type Manpower = Int


_warehouseName = verify [minLength 3, maxLength 8, alphabet]

_quantity = verify [nonNegative]


data Warehouse = Warehouse {
    warehouseName :: WarehouseName,
    location :: Location,
    stocks :: [Stock],
    packingSchedule :: PackingSchedule
} deriving Show


mkWarehouse :: WarehouseName -> Location -> [Stock] -> PackingSchedule -> Warehouse
mkWarehouse = Warehouse

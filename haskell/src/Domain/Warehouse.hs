module Domain.Warehouse (
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


_warehouseName value = verify [minLength 3, maxLength 8, alphabet] value

_quantity value = verify [nonNegative] value


data Warehouse = Warehouse {
    warehouseName :: WarehouseName,
    location :: Location,
    stocks :: [Stock],
    packingSchedule :: PackingSchedule
} deriving Show


mkWarehouse :: WarehouseName -> Location -> [Stock] -> PackingSchedule -> Warehouse
mkWarehouse warehouseName location stocks packingSchedule = Warehouse warehouseName location stocks packingSchedule

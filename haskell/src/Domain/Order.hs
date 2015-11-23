module Domain.Order (
order
) where

import Domain.Point

type OrderItem = (ItemName, OrderCount)
type ItemName = String
type OrderCount = Int

type Destination = Point

data PurchaseOrder = PurchaseOrder {
    destination :: Destination,
    orderItems :: [OrderItem]
} deriving Show

type LeadTime = Int
type Shipping = String

data OrderDetail = OrderDetail {
    leadTime :: LeadTime,
    purchaseOrder :: PurchaseOrder,
    shipping :: Shipping
} deriving Show

order :: Destination -> [OrderItem] -> OrderDetail
order destination orderItems = OrderDetail leadTime purchaseOrder shipping
    where
        leadTime = 5
        purchaseOrder = PurchaseOrder destination orderItems
        shipping = ""

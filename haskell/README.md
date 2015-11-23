### 倉庫 (Warehouse)
```
* 名前 (WarehouseName)
* 座標 (Location)
* 在庫[] (Stock)
 * 品物名 (ItemName)
 * 在庫数 (Quantity)
* 梱包予定 (PackingSchedule)
 * 待ち件数 (PendingNumber)
 * 人数 (Manpower)
```
+ 倉庫の保持している品物名と在庫数を参照する
+ 取り扱う品物を管理する
+ 在庫ゼロと扱っていないは別
+ 注文があれば発送を行う
+ 梱包予定から注文を受けてから発送するまでの時間を計算する

### 注文 (PurchaseOrder)
```
* 配送先座標 (Destination)
* 注文品 (OrderItem)
 * 品物名 (ItemName)
 * 個数 (OrderCount)
```
+ 品物マスターは省略して名前で扱う

### 明細 (OrderDetail)
```
* 必要時間 (LeadTime)
* 注文 (PurchaseOrder)
* 配送[] (Shipping)
```
+ 注文が確定したら出力する

### 配送 (Shipping)
```
* 配送元座標 (Source)
* 配送先座標 (Destination)
* 必要時間 (LeadTime)
```

### 座標 (Point)
```
* x
```
+ 距離は単純にX軸の差分で計算する

### 注文
```Java
public OrderDetail order(Destination destination, List<OrderItem> orderItems);
```

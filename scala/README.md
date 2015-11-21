### 契約
```
* 契約番号 (EngagementNumber)
* 氏名 (Fullname)
* 主回線 (Line)
 * Sim (SimCard)
  * 転入 (Mnp)
   * MSISDN (Msisdn)
* シェア回線[] (ShareLine)
 * Sim (SimCard)
  * 転入 (Mnp)
   * MSISDN (Msisdn)
```

### 参照
```Java
public Engagement find(EngagementNumber n);
```

### 挿入
```Java
public void engage(Engagement e);
```

#### 備考
+ publicリポジトリのため、一部のクラスの名称はマスクの意味で変更している
+ 実際の仕様とは異なる
+ 書いてみたいことが書ける様な仕様を思いつきで追加していく

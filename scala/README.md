### 契約
```
* 契約番号 (EngagementNumber)
* 氏名 (Fullname)
* プラン (Plan: NORMAL, SPECIAL)
* 主回線 (Line)
 * Sim (SimCard)
  * Opt[転入] (MnpIn)
   * MSISDN (Msisdn)
* シェア回線[] (ShareLine)
 * Sim (SimCard)
  * Opt[転入] (MnpIn)
   * MSISDN (Msisdn)
```

### 参照
```Java
public Optional<Engagement> find(EngagementNumber n);
```

### 挿入
```Java
public void engage(Engagement e);
```

#### DB
+ [slick](http://slick.typesafe.com/) + sqlite3
+ DB設計やカラムとエンティティのマッピングは適当
+ 参考（非公式）
 + [install](http://kaiyori.net/wordpress/?p=137)
 + [usage](http://www.mwsoft.jp/programming/scala/slick_query.html)

#### その他
+ publicリポジトリのため、一部のクラスの名称はマスクの意味で変更している
+ 実際の仕様とは異なる
+ 書いてみたいことが書ける様な仕様を思いつきで追加していく

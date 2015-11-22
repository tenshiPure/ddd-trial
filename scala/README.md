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
+ 主回線1つと、任意の数のシェア回線を契約する
+ 面倒なので回線とカードは1:1（そう簡略化したらそもそも回線が不要だが）
+ カード単位で転入するか否かを管理する
+ 転入は主/シェアで同一ドメインだが、外部キーが違うのでテーブルは別
+ 他は全部簡略化してエンティティとテーブルは1:1

### 参照
```Java
public Optional<Engagement> find(EngagementNumber n);
```

### 挿入
```Java
public void engage(Engagement e);
```

### クラス・オブジェクト
+ datasource
 + object
 + テーブル単位で存在する
 + 業務処理を持つ
 + Mapperを持ち、DBアクセスはMapperを介して行う
+ domain
 + class
 + datasourceには関与しない
+ service
 + object
 + domainを扱い、datasourceに命令を行う
+ adapter
 + apiパラメータの様な物をserviceに渡す
+ main
 + 目視テスト用

### 例外
+ 不正なプランコードで契約しようとすると例外発生
 + 本来なら入力値の検証があるはず
+ 参照時に契約番号から回線が引けなかったりする場合は例外発生

### DB
+ [slick](http://slick.typesafe.com/) + sqlite3
+ 参考（非公式）
 + [install](http://kaiyori.net/wordpress/?p=137)
 + [usage](http://www.mwsoft.jp/programming/scala/slick_query.html)
+ DB設計やカラムとエンティティのマッピングは適当
+ 制約は一切無し
+ イベントテーブルも無し
 + 更新処理はupdateで行う

### テスト
+ 目視テスト
+ Fixtureを用意

### 試行
+ packageを切らずに同一ファイルに複数のValueObjectを定義する → [member](#)
 + ファイル多過ぎ、ValueObjectの定義に行数が必要過ぎって思ってた
 + ValueObjectが1行で書けるので同一ファイルに全部入れてみた（雑）
 + 実際はもう少しは分割ファイルにしてパッケージ構成を表すのかな
 + でもパッケージ構成とドメイン設計は別に完全に一致する物でも無いしなぁ
 + コンフリクトしやすいかも
 + **@AllArgsConstructor**, **@EqualsHashCode**, **@ToString**, **@Getter**, **private**, **final** あたりは満たしているはず

### その他
+ publicリポジトリのため、一部のクラスの名称はマスクの意味で変更している
+ 実際の仕様とは色々異なる
+ 書いてみたいことが書ける様な仕様を思いつきで追加していく

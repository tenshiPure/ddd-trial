### 申込書 (Order)
```
* 会員種別 (MemberKind: Private/Corporate)
* 入会コード (EntryCode)
* 入会経路 (EntryRoute)
* 契約プラン (Plan: Normal/Special/Share)
* 特典[] (Privilege)
 * 割引額 (Discount)
 * 理由 (Cause: Corporate/Special/EntryCode)
```

### 契約 (Engagement)
```
* 会員ID (MemberId)
* 契約番号 (EngagementNumber)
* Opt<法人ID> (CorporateId)
* ステータス (Status)
* 契約プラン
* プラン料金 (PlanAmount)
* 特典[]
* 合計金額 (TotalAmount)
```

### 会員カード (MembersCard)
```
* 会員ID
* プラン
```

### 配送 (Shipping)
```
* 会員カード
```

### 概要
+ 通信サービスの申込を受け付けるサービス
+ 申し込むと契約が出来る
+ 法人の場合のみShareプランが選択可能
+ 会員IDを払い出す
 + MemberKind(PR/CO) + EntryCode + 数値
+ 契約番号を払い出す
 + MemberKind(PR/CO) + Plan(NOR/SPC/SHR) + 数値
+ 法人の場合は法人IDを払い出す
 + CO + 数値
+ プラン料金は以下
 + ノーマルは8000
 + スペシャルは12000
 + シェアは15000
+ いくつかの理由により特典がつく
 + 法人の場合は割引1000
 + シェアプランの場合は割引500
 + 入会コードが003000の場合は3000割引
 + 入会コードが002000の場合は2000割引
+ 送料はプラン料金と割引額合計で算出する
+ 会員カードを発行し、何らかの方法で申込者に渡す
+ 店頭購入
 + 利用中ステータスになる
 + カードは生成後、その場で渡す
+ Web購入
 + 配送中ステータスになる
 + カードは生成後、配送する
 + 住所は省略

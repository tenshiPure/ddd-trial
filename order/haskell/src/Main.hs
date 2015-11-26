import Service

main = do
    print $ private_normal_shop    (EntryCode "000000") Shop
    print $ private_share_shop     (EntryCode "000000") Shop
    print $ corporate_special_shop (EntryCode "000000") Shop

    print $ private_normal_shop    (EntryCode "002000") Shop
    print $ private_share_shop     (EntryCode "002000") Shop
    print $ corporate_special_shop (EntryCode "003000") Shop


    print $ private_normal_web    (EntryCode "000000") Web
    print $ private_share_web     (EntryCode "000000") Web
    print $ corporate_special_web (EntryCode "000000") Web

    print $ private_normal_web    (EntryCode "002000") Web
    print $ private_share_web     (EntryCode "002000") Web
    print $ corporate_special_web (EntryCode "003000") Web

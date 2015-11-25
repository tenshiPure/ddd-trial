import Service

main = do
    print $ private_normal    (EntryCode "000000")
    print $ private_share     (EntryCode "000000")
    print $ corporate_special (EntryCode "000000")

    print $ private_normal    (EntryCode "002000")
    print $ private_share     (EntryCode "002000")
    print $ corporate_special (EntryCode "003000")

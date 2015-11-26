module Engagement (
 module Domain,
 mkEngagement
) where

import Domain

mkEngagement :: MemberKind -> EntryCode -> Plan -> Engagement
mkEngagement memberKind entryCode plan = Engagement memberId engagementNumber corporateId plan privileges totalAmount
    where
        allocated = "001" -- todo
        memberId = case memberKind of
            Private   -> MemberId ("PR-" ++ entryCodeValue entryCode ++ "-" ++ allocated)
            Corporate -> MemberId ("CO-" ++ entryCodeValue entryCode ++ "-" ++ allocated)

        engagementNumber = case memberKind of
            Private -> case plan of
                Normal  -> EngagementNumber ("PR-" ++ "NOR-" ++ allocated)
                Special -> EngagementNumber ("PR-" ++ "SPC-" ++ allocated)
                Share   -> EngagementNumber ("PR-" ++ "SHR-" ++ allocated)
            Corporate -> case plan of
                Normal  -> EngagementNumber ("CO-" ++ "NOR-" ++ allocated)
                Special -> EngagementNumber ("CO-" ++ "SPC-" ++ allocated)
                Share   -> EngagementNumber ("CO-" ++ "SHR-" ++ allocated)

        corporateId = case memberKind of
            Private   -> Nothing
            Corporate -> Just $ CorporateId ("CO" ++ allocated)

        privileges = case memberKind of
            Private -> case plan of
                Normal -> case entryCode of
                    (EntryCode "002000") -> [entryCode2000Privilege]
                    (EntryCode "003000") -> [entryCode3000Privilege]
                    _                    -> []
                Special -> case entryCode of
                    (EntryCode "002000") -> [entryCode2000Privilege]
                    (EntryCode "003000") -> [entryCode3000Privilege]
                    _                    -> []
                Share -> case entryCode of
                    (EntryCode "002000") -> [sharePrivilege, entryCode2000Privilege]
                    (EntryCode "003000") -> [sharePrivilege, entryCode3000Privilege]
                    _                    -> [sharePrivilege]
            Corporate -> case plan of
                Normal -> case entryCode of
                    (EntryCode "002000") -> [corporatePrivilege, entryCode2000Privilege]
                    (EntryCode "003000") -> [corporatePrivilege, entryCode3000Privilege]
                    _                    -> [corporatePrivilege]
                Special -> case entryCode of
                    (EntryCode "002000") -> [corporatePrivilege, entryCode2000Privilege]
                    (EntryCode "003000") -> [corporatePrivilege, entryCode3000Privilege]
                    _                    -> [corporatePrivilege]
                Share -> case entryCode of
                    (EntryCode "002000") -> [corporatePrivilege, sharePrivilege, entryCode2000Privilege]
                    (EntryCode "003000") -> [corporatePrivilege, sharePrivilege, entryCode3000Privilege]
                    _                    -> [corporatePrivilege, sharePrivilege]

        totalAmount = TotalAmount (planAmount - discounts)
            where
                planAmount = case plan of
                    Normal   -> 8000
                    Special -> 12000
                    Share   -> 15000

                discounts = sum $ map (discountValue . privilegeDiscount) privileges


corporatePrivilege     = Privilege (Discount 1000) ByCorporate
sharePrivilege         = Privilege (Discount 500) ByShare
entryCode2000Privilege = Privilege (Discount 3000) ByEntryCode
entryCode3000Privilege = Privilege (Discount 2000) ByEntryCode

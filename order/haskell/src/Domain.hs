module Domain (
 MemberKind(..),
 EntryCode(..),
 Plan(..),
 Privilege(..),
 Discount(..),
 Cause(..),
 Engagement(..),
 MemberId(..),
 EngagementNumber(..),
 CorporateId(..)
) where

data MemberKind = Private | Corporate deriving Show
data EntryCode = EntryCode { value :: String } deriving Show
data Plan = Normal | Special | Share deriving Show
data Privilege = Privilege Discount Cause deriving Show
data Discount = Discount Int deriving Show
data Cause = ByCorporate | ByShare | ByEntryCode deriving Show
data Engagement = Engagement MemberId EngagementNumber (Maybe CorporateId) Plan [Privilege] deriving Show
data MemberId = MemberId String deriving Show
data EngagementNumber = EngagementNumber String deriving Show
data CorporateId = CorporateId String deriving Show

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
 CorporateId(..),
 PlanAmount(..),
 TotalAmount(..)
) where

data MemberKind = Private | Corporate deriving Show
data EntryCode = EntryCode { entryCodeValue :: String } deriving Show
data Plan = Normal | Special | Share deriving Show
data Privilege = Privilege { privilegeDiscount :: Discount, privilegeCause :: Cause } deriving Show
data Discount = Discount { discountValue :: Int } deriving Show
data Cause = ByCorporate | ByShare | ByEntryCode deriving Show
data Engagement = Engagement MemberId EngagementNumber (Maybe CorporateId) Plan [Privilege] TotalAmount deriving Show
data MemberId = MemberId String deriving Show
data EngagementNumber = EngagementNumber String deriving Show
data CorporateId = CorporateId String deriving Show
data PlanAmount = PlanAmount Int deriving Show
data TotalAmount = TotalAmount Int deriving Show

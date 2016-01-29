module Domain (
 MemberKind(..),
 EntryCode(..),
 EntryRoute(..),
 Plan(..),
 Privilege(..),
 Discount(..),
 Cause(..),
 Engagement(..),
 MemberId(..),
 EngagementNumber(..),
 CorporateId(..),
 Status(..),
 PlanAmount(..),
 TotalAmount(..),
 MembersCard(..),
 Shipping(..)
) where

data MemberKind = Private | Corporate deriving Show
data EntryCode = EntryCode { entryCodeValue :: String } deriving Show
data EntryRoute = Shop | Web deriving Show
data Plan = Normal | Special | Share deriving Show
data Privilege = Privilege { privilegeDiscount :: Discount, privilegeCause :: Cause } deriving Show
data Discount = Discount { discountValue :: Int } deriving Show
data Cause = ByCorporate | ByShare | ByEntryCode deriving Show
data Engagement = Engagement MemberId EngagementNumber (Maybe CorporateId) Plan Status [Privilege] TotalAmount deriving Show
data MemberId = MemberId String deriving Show
data EngagementNumber = EngagementNumber String deriving Show
data CorporateId = CorporateId String deriving Show
data Status = Using | InShipping deriving Show
data PlanAmount = PlanAmount Int deriving Show
data TotalAmount = TotalAmount Int deriving Show
data MembersCard = MembersCard MemberId Plan deriving Show
data Shipping = Shipping MembersCard deriving Show

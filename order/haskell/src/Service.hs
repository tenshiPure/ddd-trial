module Service (
 module Order,
 module Engagement,
 private_normal,
 private_share,
 corporate_special
) where

import Order
import Engagement

private_normal :: EntryCode -> Engagement
private_normal entryCode = mkEngagement memberKind entryCode plan
    where
        memberKind = Private 
        plan = Normal

private_share :: EntryCode -> Engagement
private_share entryCode = mkEngagement memberKind entryCode plan
    where
        memberKind = Private 
        plan = Share

corporate_special :: EntryCode -> Engagement
corporate_special entryCode = mkEngagement memberKind entryCode plan
    where
        memberKind = Corporate 
        plan = Special

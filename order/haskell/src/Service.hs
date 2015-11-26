module Service (
 module Order,
 module Engagement,
 private_normal_shop,
 private_share_shop,
 corporate_special_shop,
 private_normal_web,
 private_share_web,
 corporate_special_web
) where

import Order
import Engagement

private_normal_shop :: EntryCode -> EntryRoute -> (Engagement, MembersCard, Maybe Shipping)
private_normal_shop entryCode entryRoute = mkEngagement memberKind entryCode entryRoute plan
    where
        memberKind = Private 
        plan = Normal

private_share_shop :: EntryCode -> EntryRoute -> (Engagement, MembersCard, Maybe Shipping)
private_share_shop entryCode entryRoute = mkEngagement memberKind entryCode entryRoute plan
    where
        memberKind = Private 
        plan = Share

corporate_special_shop :: EntryCode -> EntryRoute -> (Engagement, MembersCard, Maybe Shipping)
corporate_special_shop entryCode entryRoute = mkEngagement memberKind entryCode entryRoute plan
    where
        memberKind = Corporate 
        plan = Special

private_normal_web :: EntryCode -> EntryRoute -> (Engagement, MembersCard, Maybe Shipping)
private_normal_web entryCode entryRoute = mkEngagement memberKind entryCode entryRoute plan
    where
        memberKind = Private 
        plan = Normal

private_share_web :: EntryCode -> EntryRoute -> (Engagement, MembersCard, Maybe Shipping)
private_share_web entryCode entryRoute = mkEngagement memberKind entryCode entryRoute plan
    where
        memberKind = Private 
        plan = Share

corporate_special_web :: EntryCode -> EntryRoute -> (Engagement, MembersCard, Maybe Shipping)
corporate_special_web entryCode entryRoute = mkEngagement memberKind entryCode entryRoute plan
    where
        memberKind = Corporate 
        plan = Special

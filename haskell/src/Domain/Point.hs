module Domain.Point (
 Point,
 sub
) where

type Point = Int

sub :: Point -> Point -> Point
sub p1 p2 = p1 - p2

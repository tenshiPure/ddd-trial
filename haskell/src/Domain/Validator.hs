module Domain.Validator (
verify,
Error,
minLength,
maxLength,
alphabet,
nonNegative
) where


import Data.Char
import Data.Maybe


type Error = String


minLength :: Int -> String -> Maybe Error
minLength i s 
    | length s < i = Just "too short"
    | otherwise    = Nothing


maxLength :: Int -> String -> Maybe Error
maxLength i s
    | i < length s = Just "too long"
    | otherwise    = Nothing


alphabet :: String -> Maybe Error
alphabet s = case filter isAlpha s of
    [] -> Just "not only alphabet"
    _  -> Nothing


nonNegative :: Int -> Maybe Error
nonNegative i
    | i < 0     = Just "negative"
    | otherwise = Nothing


verify :: [a -> Maybe Error] -> a -> Either [Error] a
verify rules value = if null errors then Right value else Left $ map fromJust errors
    where
        errors = filter isJust $ map (\rule -> rule value) rules

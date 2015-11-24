module Datasource.Database(
dbInit,
find,
findOne,
insert
) where


import System.Directory
import Control.Monad

import Database.HDBC
import Database.HDBC.Sqlite3


path = "test.sqlite3"

connect = connectSqlite3 path


dbInit creators = do
    b <- doesFileExist path
    when b (removeFile path)

    mapM_ create creators


find :: String -> IO [[Maybe String]]
find query = _find query sFetchAllRows

findOne :: String -> IO (Maybe [Maybe String])
findOne query = _find query sFetchRow

_find :: String -> (Statement -> IO a) -> IO a
_find query fetcher = do
    connection <- connect
    stmt <- prepare connection query
    execute stmt []
    fetcher stmt


create :: String -> IO ()
create query = exec query []

insert :: String -> [SqlValue] -> IO ()
insert = exec

exec :: String -> [SqlValue] -> IO ()
exec query args = do
    connection <- connect
    run connection query args
    commit connection

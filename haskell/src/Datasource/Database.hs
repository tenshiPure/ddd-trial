module Datasource.Database(
find,
findOne,
insert,
create
) where


import Database.HDBC
import Database.HDBC.Sqlite3


connect = connectSqlite3 "test.sqlite3"


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

insert :: String -> [String] -> IO ()
insert = exec

exec :: String -> [String] -> IO ()
exec query args = do
    connection <- connect
    run connection query (map toSql args)
    commit connection

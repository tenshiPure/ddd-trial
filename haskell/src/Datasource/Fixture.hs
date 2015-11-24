module Datasource.Fixture (
Datasource.Fixture.dbInit,
some
) where


import Datasource.Database
import Datasource.Warehouse


dbInit = do
    Datasource.Database.dbInit [Datasource.Warehouse.creator]


some = do
    Datasource.Warehouse.insert "tokyo" 0 "book" 10 5 5

@echo off
mkdir app
jar --create --file=app/WholeWorthsGrocery.jar --main-class=wholeworthsgrocery.WholeWorthsGrocery -C classes .
pause

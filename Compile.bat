@echo off
javac --module-path %PATH_TO_FX% --add-modules=javafx.controls,javafx.graphics,javafx.fxml src\wholeworthsgrocery\*.java -d classes
copy src\wholeworthsgrocery\*.fxml classes\wholeworthsgrocery\*.fxml
copy src\*.jpg classes\wholeworthsgrocery\*.jpg
pause

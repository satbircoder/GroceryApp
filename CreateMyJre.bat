@echo off
echo ...
echo **********
echo ** Dependency Check in Progress **
jdeps -s --module-path %PATH_TO_FX% app\WholeWorthsGrocery.jar
echo ...
echo **********
echo ** Creating Custom Java Run Time Environment **
echo **********
jlink --module-path ../jmods;%PATH_TO_FX_JMOD% --add-modules=java.base,javafx.base,javafx.controls,javafx.graphics,javafx.fxml --output MyJavaEnvironment
pause
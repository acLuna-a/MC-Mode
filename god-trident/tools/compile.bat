@echo off
REM ------------------------------------------------------------------
REM  编译 god-trident 源码（1.20.1 / Forge，源码使用 SRG 中间名）
REM  依赖：Forge 1.20.1 的 SRG 名 MC/Forge jar + 运行库，全部放在一个目录里。
REM        将该目录路径（以反斜杠结尾）设为环境变量 FORGE_CP，例如：
REM        set FORGE_CP=C:\myforge\libs\
REM        目录中至少要有 forge-1.20.1-*-injected.jar、universal.jar、
REM        fmlcore / javafmllanguage / eventbus / guava / gson / joml 等。
REM  产物：build\classes\com\godtrident\*.class
REM ------------------------------------------------------------------
setlocal
set ROOT=%~dp0..
if "%FORGE_CP%"=="" (
  echo [ERROR] 请先设置 FORGE_CP 指向包含 MC/Forge SRG jar 的目录
  exit /b 1
)
if not exist "%ROOT%\build\classes" mkdir "%ROOT%\build\classes"
javac -encoding UTF-8 --release 17 -proc:none -nowarn -cp "%FORGE_CP%*" -d "%ROOT%\build\classes" "%ROOT%\src\main\java\com\godtrident\*.java"
if errorlevel 1 ( echo [ERROR] 编译失败 & exit /b 1 )
echo [OK] 编译完成，接着运行: python "%ROOT%\tools\build.py"
endlocal

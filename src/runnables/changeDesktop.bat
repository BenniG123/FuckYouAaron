reg add "HKCU\Control Panel\Desktop" /v Wallpaper /f /t REG_SZ /d %1
%SystemRoot%\System32\RUNDLL32.EXE user32.dll, UpdatePerUserSystemParameters

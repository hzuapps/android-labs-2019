:: input commit message
set /p commit_msg=Please input commit message:

cd students

:: add all changing
git add -A
:: local commit
git commit -m "%commit_msg%"
:: push to remote repository
git push
:: make a pause 
pause
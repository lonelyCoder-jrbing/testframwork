git 使用事项 ：
 ****注意：一定得多拉代码，写之前拉取，提交之前拉取
 ****注意：不要覆盖别人的代码
1. git reset 可以使用 git reset --hard commit-id 来将commit之后的代码回退到上一个版本。
   也可以使用git reset  commit-id将add之后的代码移除
   可以使用git rm -r --cached .idea/* 移除已经commit的文件
2. 如果不使用--hard 参数  则之后可以接着使用 git revert 来将本地的修改撤销。
3. git status 只能查看git add 的内容，并不能查看git commit之后的内容
4. 使用git branch -a 查看所有的分支，使用git branch -r 查看远程分支。
5. 使用git branch branchName 来创建本地分支名称。
6. git 创建远程分支 git  push https://github.com/lonelyCoder-jrbing/testframwork dev:dev 
   （第一个参数是本地分支名称，第二个参数是远程分支名称）
   注意：idea提示merge代码时候能点击取消，如果取消，可能发生覆盖别人代码的可能。
   
   
   
   
   
   
   
   
   
   
   
   
   
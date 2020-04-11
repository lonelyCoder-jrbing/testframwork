git 使用事项 ：
 ****注意：一定得多拉代码，写之前拉取，提交之前拉取
 ****注意：不要覆盖别人的代码
1. git reset 可以使用 git reset --hard commit-id 来将代码回退到上一个版本。
2. 如果不使用--hard 参数  则之后可以接着使用 git revert 来将本地的修改撤销。
3. 使用git branch -a 查看所有的分支，使用git branch -r 查看远程分支。
4. 使用git branch branchName 来创建本地分支名称。
5. git 创建远程分支 git  push https://github.com/lonelyCoder-jrbing/testframwork dev:dev 
   （第一个参数是本地分支名称，第二个参数是远程分支名称）
   注意：idea提示merge代码时候能点击取消，如果取消，可能发生覆盖别人代码的可能。
   
   
   
   
   
   
   
   
   
   
   
   
   
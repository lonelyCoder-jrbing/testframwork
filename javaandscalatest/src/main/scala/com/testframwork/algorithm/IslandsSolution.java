package com.testframwork.algorithm;

/**
 * create by sumerian on 2020/5/5
 * <p>
 * desc:  岛屿问题的解决方案   遍历法
 **/
public class IslandsSolution {
    public static void main(String[] args) {
        int[][] grid ={{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        int i = islandPerimeter(grid);
        System.out.println("res="+i);
    }
    public static int islandPerimeter(int[][] grid) {
        //为数字1周围出现的1进行计数，初始值为4，如果数字1周围出现了一个1，则减去2，
        //周长
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res += 4;
                    if ((i + 1) <= grid.length - 1) {
                        System.out.println("i+1:"+(i+1));
                        if (grid[i + 1][j] == 1) {
                            res -= 2;
                        }
                    }

                    if ((j + 1) <= grid[0].length - 1) {
                        if (grid[i][j + 1] == 1) {
                            res -= 2;
                        }
                    }
                }
            }
        }

        return res;
    }
}

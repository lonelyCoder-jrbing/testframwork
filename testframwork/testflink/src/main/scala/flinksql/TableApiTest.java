package flinksql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;

public class TableApiTest {
    public static void main(String[] args) throws Exception {
        //1.获取上下文环境
        ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment tableEnvironment = BatchTableEnvironment.getTableEnvironment(executionEnvironment);
        //读取数据
        DataSet<String> input = executionEnvironment.readTextFile("flinksql/src/resources/score.csv");
        DataSet<PlayerData> topInput = input.map(new MapFunction<String, PlayerData>() {
            @Override
            public PlayerData map(String value) throws Exception {
                String[] split = value.split(",");
                return new PlayerData(String.valueOf(split[0]),
                        String.valueOf(split[1]),
                        String.valueOf(split[2]),
                        Integer.valueOf(split[3]),
                        Double.valueOf(split[4]),
                        Double.valueOf(split[5]),
                        Double.valueOf(split[6]),
                        Double.valueOf(split[7]),
                        Double.valueOf(split[8])
                );


            }
        });
        //3\. 注册成内存表
        Table topScore = tableEnvironment.fromDataSet(topInput);
        tableEnvironment.registerTable("score", topScore);
        //4.核心处理逻辑sql的编写
        Table result = tableEnvironment.sqlQuery("select player, count(season) as num from score group by player order by num desc limit 3");
        DataSet<Result> resultDataSet = tableEnvironment.toDataSet(result, Result.class);
        resultDataSet.print();

    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class PlayerData {
        /**
         * 赛季，球员，出场，首发，时间，助攻，抢断，盖帽，得分
         */
        public String season;
        public String player;
        public String play_num;
        public Integer first_court;
        public Double time;
        public Double assists;
        public Double steals;
        public Double blocks;
        public Double scores;

    }

    @Data
    public static class Result {
        public String player;
        public Long num;
    }
}

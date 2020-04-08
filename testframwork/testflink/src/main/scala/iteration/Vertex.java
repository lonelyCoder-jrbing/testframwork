package iteration;

public class Vertex {
    public static void main(String[] args) throws Exception {
//        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//
//        DataSet<Tuple2<Long, Long>> verticesAsWorkset = generateWorksetWithVertices(env);
//        DataSet<Tuple2<Long, Long>> edges = generateDefaultEdgeDataSet(env);
//
//        int vertexIdIndex = 0;
//        DeltaIteration<Tuple2<Long, Long>, Tuple2<Long, Long>> iteration = verticesAsWorkset
//                .iterateDelta(verticesAsWorkset, MAX_ITERATION_NUM, vertexIdIndex);
//
//        DataSet<Tuple2<Long, Long>> delta = iteration.getWorkset()
//                .join(edges).where(0).equalTo(0)
//                .with(new NeighborWithParentIDJoin())
//                .join(iteration.getSolutionSet()).where(0).equalTo(0)
//                .with(new RootIdFilter());
//        DataSet<Tuple2<Long, Long>> finalDataSet = iteration.closeWith(delta, delta);
//
//        finalDataSet.print();
    }


}

package com.springdemo.scheduletask.quartz;

import java.util.List;
import java.util.Optional;

public interface SchedulerService {
    JobModel createJob(String var1, JobModel var2);

    List<JobModel> findGroupJobs(String var1);

    Optional<JobModel> findJob(String var1, String var2);

    JobModel updateJob(String var1, String var2, JobModel var3);

    void deleteJob(String var1, String var2);

    void pauseJob(String var1, String var2);

    void resumeJob(String var1, String var2);

    boolean checkSchedulerJob(String var1, String var2, JobModel var3);
}

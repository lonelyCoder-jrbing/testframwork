package com.springdemo.scheduletask.quartz;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        rollbackFor = {Exception.class}
)
public class SchedulerServiceImpl implements SchedulerService {
    private static Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);
    @Autowired
    private Scheduler scheduler;

    public SchedulerServiceImpl() {
    }

    @Override
    public JobModel createJob(String group, JobModel jobModel) {
        if (logger.isTraceEnabled()) {
            logger.trace("jobModel = {}.", JSON.toJSONString(jobModel));
        }

        this.buildSchedulerTimer(group, jobModel);
        return jobModel;
    }

    @Override
    public List<JobModel> findGroupJobs(String group) {
        ArrayList models = new ArrayList();

        try {
            Set<JobKey> keys = this.scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group));
            Iterator var4 = keys.iterator();

            while (var4.hasNext()) {
                JobKey key = (JobKey) var4.next();
                JobDetail jobDetail = this.scheduler.getJobDetail(key);
                String state = this.getModelState(key);
                JobModel currModel = JobDetailHelper.buildJobModel(jobDetail.getJobDataMap());
                currModel.setState(state);
                models.add(currModel);
            }

            return models;
        } catch (SchedulerException var9) {
            logger.error("Could not find any jobs due to error - {}", var9.getLocalizedMessage(), var9);
            throw new RuntimeException(var9.getLocalizedMessage());
        }
    }

    @Override
    public Optional<JobModel> findJob(String group, String name) {
        try {
            JobKey jobKey = JobKey.jobKey(name, group);
            JobDetail jobDetail = this.scheduler.getJobDetail(jobKey);
            if (null != jobDetail) {
                JobModel model = JobDetailHelper.buildJobModel(jobDetail.getJobDataMap());
                if (null != model) {
                    String state = this.getModelState(jobKey);
                    model.setState(state);
                }

                return Optional.ofNullable(model);
            } else {
                return Optional.empty();
            }
        } catch (SchedulerException var7) {
            logger.error("Could not find any jobs due to error - {}", var7.getLocalizedMessage(), var7);
            throw new RuntimeException(var7.getLocalizedMessage());
        }
    }

    @Override
    public JobModel updateJob(String group, String name, JobModel jobModel) {
        try {
            logger.info("update Job with Key - {}.{}", group, name);
            if (logger.isTraceEnabled()) {
                logger.trace("jobModel = {}.", JSON.toJSONString(jobModel));
            }

            JobDetail oldJobDetail = this.scheduler.getJobDetail(JobKey.jobKey(name, group));
            if (Objects.nonNull(oldJobDetail)) {
                JobDataMap jobDataMap = oldJobDetail.getJobDataMap();
                JobDetailHelper.buildJobDataMap(jobDataMap, jobModel);
                JobBuilder jb = oldJobDetail.getJobBuilder();
                logger.info("bind new JobDetail.");
                JobDetail newJobDetail = jb.usingJobData(jobDataMap).storeDurably().build();
                this.scheduler.addJob(newJobDetail, true);
                TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
                Trigger currTrigger = this.scheduler.getTrigger(triggerKey);
                Trigger trigger = this.buildTrigger(jobModel, currTrigger);
                if (null != currTrigger) {
                    this.scheduler.rescheduleJob(triggerKey, trigger);
                } else {
                    this.scheduler.scheduleJob(newJobDetail, Sets.newHashSet(new Trigger[]{trigger}), true);
                }

                Date startTime = trigger.getStartTime();
                Date endTime = trigger.getEndTime();
                int equals = startTime.compareTo(endTime);
                if (equals >= 0) {
                    logger.error("Could not save job  endTime- {},startTime-{}", endTime, startTime);
                } else {
                    jobModel.setState(TriggerState.NORMAL.name());
                    logger.info("Updated job with key - {}", newJobDetail.getKey());
                    return jobModel;
                }
            } else {
                logger.warn("Could not find job with key - {}.{} to update", group, name);
                return null;
            }
        } catch (SchedulerException var14) {
            logger.error("Could not find job with key - {}.{} to update due to error - {}", new Object[]{group, name, var14.getLocalizedMessage()});
            throw new RuntimeException(var14.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public void deleteJob(String group, String name) {
        try {
            this.scheduler.deleteJob(JobKey.jobKey(name, group));
            logger.info("Deleted job with key - {}.{}", group, name);
        } catch (SchedulerException var4) {
            logger.error("Could not delete job with key - {}.{} due to error - {}", new Object[]{group, name, var4.getLocalizedMessage()});
            throw new RuntimeException(var4.getLocalizedMessage());
        }
    }

    @Override
    public void pauseJob(String group, String name) {
        try {
            this.scheduler.pauseJob(JobKey.jobKey(name, group));
            logger.info("Paused job with key - {}.{}", group, name);
        } catch (SchedulerException var4) {
            logger.error("Could not pause job with key - {}.{} due to error - {}", new Object[]{group, name, var4.getLocalizedMessage()});
            throw new RuntimeException(var4.getLocalizedMessage());
        }
    }

    @Override
    public void resumeJob(String group, String name) {
        try {
            this.scheduler.resumeJob(JobKey.jobKey(name, group));
            logger.info("Resumed job with key - {}.{}", group, name);
        } catch (SchedulerException var4) {
            logger.error("Could not resume job with key - {}.{} due to error - {}", new Object[]{group, name, var4.getLocalizedMessage()});
            throw new RuntimeException(var4.getLocalizedMessage());
        }
    }

    @Override
    public boolean checkSchedulerJob(String group, String name, JobModel jobModel) {
        LocalDateTime endTime1 = jobModel.getEndTime();
        Date end = Date.from(endTime1.toInstant(ZoneOffset.of("+8")));
        int equals1 = (new Date()).compareTo(end);
        if (equals1 >= 0) {
            return false;
        } else {
            LocalDateTime fireTime = jobModel.getFireTime();
            Date start = Date.from(fireTime.toInstant(ZoneOffset.of("+8")));
            int equals = start.compareTo(end);
            if (equals >= 0) {
                logger.error("Could not save job  endTime- {},startTime-{}", start, end);
                return false;
            } else {
                return true;
            }
        }
    }

    private void buildSchedulerTimer(String group, JobModel jobModel) {
        String name = jobModel.getName();
        logger.info("create Job with Key - {}.{}", group, name);

        try {
            if (this.scheduler.checkExists(JobKey.jobKey(jobModel.getName(), group))) {
                logger.info("Job with Key - {}.{} already exists", group, name);
            } else {
                jobModel.setGroup(group);
                JobDetail jobDetail = this.buildJobDetail(jobModel);
                Trigger trigger = this.buildTrigger(jobModel, (Trigger) null);
                Date startTime = trigger.getStartTime();
                Date endTime = trigger.getEndTime();
                int equals = startTime.compareTo(endTime);
                if (equals >= 0) {
                    logger.error("Could not save job  endTime- {},startTime-{}", endTime, startTime);
                } else {
                    logger.info("About to save job with key - {}", jobDetail.getKey());
                    this.scheduler.scheduleJob(jobDetail, trigger);
                    jobModel.setState(TriggerState.NORMAL.name());
                    logger.info("Job with key - {} saved sucessfully", jobDetail.getKey());
                }
            }
        } catch (SchedulerException var9) {
            logger.error("Could not save job with key - {}.{} due to error - {}", new Object[]{group, name, var9.getLocalizedMessage()});
            throw new RuntimeException(var9.getLocalizedMessage());
        }
    }

    private JobDetail buildJobDetail(JobModel jobModel) {
        Map<String, Object> data = new LinkedHashMap();
        JobDataMap jobDataMap = new JobDataMap(data);
        JobDetailHelper.buildJobDataMap(jobDataMap, jobModel);
        return JobBuilder.newJob(CallbackTimer.class).withIdentity(jobModel.getName(), jobModel.getGroup()).usingJobData(jobDataMap).build();
    }

    private void buildJobDataMap(JobDataMap jobDataMap, JobModel jobModel) {
        jobDataMap.put("name", jobModel.getName());
        jobDataMap.put("group", jobModel.getGroup());
        jobDataMap.put("fireTime", jobModel.getFireTime());
        jobDataMap.put("cron", jobModel.getCron());
        jobDataMap.put("endTime", jobModel.getEndTime());
        jobDataMap.put("callbackUrl", jobModel.getCallbackUrl());
        jobDataMap.put("topic", jobModel.getTopic());
        jobDataMap.put("callbackType", jobModel.getCallbackType());
        jobDataMap.put("connectTimeout", jobModel.getConnectTimeout());
        jobDataMap.put("readTimeout", jobModel.getReadTimeout());
    }

    private Trigger buildTrigger(JobModel jobModel, Trigger currTrigger) throws SchedulerException {
        logger.info("bind trigger.");
        String name = jobModel.getName();
        String group = jobModel.getGroup();
        LocalDateTime fireTime = jobModel.getFireTime();
        LocalDateTime endTime = jobModel.getEndTime();
        TriggerBuilder triggerBuilder;
        if (null == currTrigger) {
            triggerBuilder = TriggerBuilder.newTrigger().withIdentity(name, group);
        } else {
            triggerBuilder = currTrigger.getTriggerBuilder();
        }

        return jobModel.getExecuteType() == SchedulerExecuteType.POLLING ? this.generateTriggerInfo(triggerBuilder, jobModel.getInterval(), fireTime, endTime, currTrigger) : this.generateTriggerInfo(triggerBuilder, jobModel.getCron(), fireTime, endTime, currTrigger);
    }

    private Trigger generateTriggerInfo(TriggerBuilder<Trigger> triggerBuilder, Long interval, LocalDateTime fireTime, LocalDateTime endTime, Trigger currTrigger) {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(interval.intValue()).repeatForever();
        return this.generateTriggerInfo(triggerBuilder, (ScheduleBuilder) scheduleBuilder, fireTime, endTime, currTrigger);
    }

    private Trigger generateTriggerInfo(TriggerBuilder<Trigger> triggerBuilder, String cron, LocalDateTime fireTime, LocalDateTime endTime, Trigger currTrigger) {
        if (StringUtils.isNotBlank(cron)) {
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            currTrigger = this.generateTriggerInfo(triggerBuilder, (ScheduleBuilder) scheduleBuilder, fireTime, endTime, currTrigger);
        } else {
            Instant instant = fireTime.atZone(ZoneId.systemDefault()).toInstant();
            currTrigger = triggerBuilder.startAt(new Date(instant.toEpochMilli())).build();
        }

        return currTrigger;
    }

    private Trigger generateTriggerInfo(TriggerBuilder<Trigger> triggerBuilder, ScheduleBuilder scheduleBuilder, LocalDateTime fireTime, LocalDateTime endTime, Trigger currTrigger) {
        if (null != fireTime) {
            Date now = new Date();
            now.setTime(now.getTime() + 60000L);
            triggerBuilder.startAt(now);
        }

        if (null != endTime) {
            Instant instant = endTime.atZone(ZoneId.systemDefault()).toInstant();
            triggerBuilder.endAt(new Date(instant.toEpochMilli()));
        }

        try {
            currTrigger = triggerBuilder.withSchedule(scheduleBuilder).build();
            return currTrigger;
        } catch (Exception var7) {
            return null;
        }
    }

    private String getModelState(JobKey jobKey) throws SchedulerException {
        List<? extends Trigger> triggerList = this.scheduler.getTriggersOfJob(jobKey);
        String state = TriggerState.NORMAL.name();
        Iterator var5 = triggerList.iterator();

        while (var5.hasNext()) {
            Trigger trigger = (Trigger) var5.next();
            TriggerState tempState = this.scheduler.getTriggerState(trigger.getKey());
            if (TriggerState.PAUSED.equals(tempState)) {
                state = TriggerState.PAUSED.name();
                break;
            }
        }

        return state;
    }
}
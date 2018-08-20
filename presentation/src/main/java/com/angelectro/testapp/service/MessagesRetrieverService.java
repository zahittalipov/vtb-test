package com.angelectro.testapp.service;

import android.content.Context;

import com.angelectro.domain.data.MessageModel;
import com.angelectro.domain.repository.MessageRepository;
import com.angelectro.testapp.MessageApp;
import com.angelectro.testapp.utils.AndroidUtils;
import com.annimon.stream.Stream;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MessagesRetrieverService extends JobService {

    @Inject
    MessageRepository messageRepository;

    private CompositeDisposable compositeSubscription = new CompositeDisposable();

    public static final String JOB_TAG = "job_test";
    public static final int REFRESH_INTERVAL = 2 * 60;

    @Override
    public void onCreate() {
        super.onCreate();

        MessageApp.getAppComponent().inject(this);
    }

    public static int startService(Context context, int interval) {

        FirebaseJobDispatcher jobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));

        jobDispatcher.cancelAll();

        Job jobInfo = jobDispatcher
                .newJobBuilder()
                .setTag(JOB_TAG)
                .setRecurring(true)
                .setReplaceCurrent(true)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setTrigger(Trigger.executionWindow(interval, interval + 60))
                .setLifetime(Lifetime.FOREVER)
                .setService(MessagesRetrieverService.class)
                .build();

        return jobDispatcher.schedule(jobInfo);
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        unsubscribeOnDestroy(attachObservable(messageRepository.getAll())
                .doOnSuccess(n -> setPeriodicallyTask(jobParameters))
                .subscribe(this::handleResponse, Throwable::printStackTrace));

        return true;
    }

    private void handleResponse(List<MessageModel> messageModels) {
        Stream.of(messageModels)
                .filter(this::isFreshMessage)
                .forEach(this::showNotification);
    }

    private boolean isFreshMessage(MessageModel message) {
        Calendar now = GregorianCalendar.getInstance();

        return message.getEndDateTime().after(now.getTime())
                && message.getStartDateTime().before(now.getTime());
    }

    private void showNotification(MessageModel message) {
        AndroidUtils.generateNotification(this, message.getSubject(), message.getText(), message.getId());
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public void unsubscribeOnDestroy(@NonNull Disposable disposable) {
        compositeSubscription.add(disposable);
    }

    public <T> Single<T> attachObservable(Single<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void setPeriodicallyTask(JobParameters jobParameters) {
        startService(this, REFRESH_INTERVAL);
        jobFinished(jobParameters, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
    }

}
